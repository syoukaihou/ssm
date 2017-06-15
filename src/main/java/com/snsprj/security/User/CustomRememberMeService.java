package com.snsprj.security.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by John on 2017/6/15.
 */
public class CustomRememberMeService  extends AbstractRememberMeServices{

    private PersistentTokenRepository tokenRepository = new InMemoryTokenRepositoryImpl();

    private SecureRandom random;

    public static final int DEFAULT_SERIES_LENGTH = 16;
    public static final int DEFAULT_TOKEN_LENGTH = 16;

    private int seriesLength = DEFAULT_SERIES_LENGTH;
    private int tokenLength = DEFAULT_TOKEN_LENGTH;

    public CustomRememberMeService(String key, UserDetailsService userDetailsService,PersistentTokenRepository tokenRepository){

        super(key, userDetailsService);
        random = new SecureRandom();
        this.tokenRepository = tokenRepository;

    }

    /**
     * Called from loginSuccess when a remember-me login has been requested. Typically
     * implemented by subclasses to set a remember-me cookie and potentially store a
     * record of it if the implementation requires this.
     *
     * @param request
     * @param response
     * @param successfulAuthentication
     */
    @Override
    protected void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {

        String username = successfulAuthentication.getName();

        logger.debug("Creating new persistent login for user " + username);

        PersistentRememberMeToken persistentToken = new PersistentRememberMeToken(
                username, generateSeriesData(), generateTokenData(), new Date());
        try {
            tokenRepository.createNewToken(persistentToken);
            addCookie(persistentToken, request, response);
        }
        catch (Exception e) {
            logger.error("Failed to save persistent token ", e);
        }
    }

    /**
     * Called from autoLogin to process the submitted persistent login cookie. Subclasses
     * should validate the cookie and perform any additional management required.
     *
     * @param cookieTokens the decoded and tokenized cookie value
     * @param request      the request
     * @param response     the response, to allow the cookie to be modified if required.
     * @return the UserDetails for the corresponding user account if the cookie was
     * validated successfully.
     * @throws RememberMeAuthenticationException if the cookie is invalid or the login is
     *                                           invalid for some other reason.
     * @throws UsernameNotFoundException         if the user account corresponding to the login
     *                                           cookie couldn't be found (for example if the user has been removed from the
     *                                           system).
     */
    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) throws RememberMeAuthenticationException, UsernameNotFoundException {
        if (cookieTokens.length != 2) {
            throw new InvalidCookieException("Cookie token did not contain " + 2
                    + " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }

        final String presentedSeries = cookieTokens[0];
        final String presentedToken = cookieTokens[1];

        PersistentRememberMeToken token = tokenRepository
                .getTokenForSeries(presentedSeries);

        if (token == null) {
            // No series match, so we can't authenticate using this cookie
            throw new RememberMeAuthenticationException(
                    "No persistent token found for series id: " + presentedSeries);
        }

        // We have a match for this user/series combination
        if (!presentedToken.equals(token.getTokenValue())) {
            // Token doesn't match series value. Delete all logins for this user and throw
            // an exception to warn them.
            //tokenRepository.removeUserTokens(token.getUsername());

            throw new CookieTheftException(
                    messages.getMessage(
                            "PersistentTokenBasedRememberMeServices.cookieStolen",
                            "Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."));
        }

        if (token.getDate().getTime() + getTokenValiditySeconds() * 1000L < System
                .currentTimeMillis()) {
            throw new RememberMeAuthenticationException("Remember-me login has expired");
        }

        // Token also matches, so login is valid. Update the token value, keeping the
        // *same* series number.
        if (logger.isDebugEnabled()) {
            logger.debug("Refreshing persistent login token for user '"
                    + token.getUsername() + "', series '" + token.getSeries() + "'");
        }
//PersistentRememberMeToken newToken = new PersistentRememberMeToken(
        //token.getUsername(), token.getSeries(), generateTokenData(), new Date());

        try {
            tokenRepository.updateToken(token.getSeries(), token.getTokenValue(),
                    new Date((new Date().getTime() + 24 * 7 * 3600 * 1000)));
            addCookie(token, request, response);
        }
        catch (Exception e) {
            logger.error("Failed to update token: ", e);
            throw new RememberMeAuthenticationException(
                    "Autologin failed due to data access problem");
        }

        return getUserDetailsService().loadUserByUsername(token.getUsername());
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
                       Authentication authentication) {
        super.logout(request, response, authentication);

        if (authentication != null) {
            tokenRepository.removeUserTokens(authentication.getName());
        }
    }

    protected String generateSeriesData() {
        byte[] newSeries = new byte[seriesLength];
        random.nextBytes(newSeries);
        return new String(Base64.encode(newSeries));
    }

    protected String generateTokenData() {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }

    private void addCookie(PersistentRememberMeToken token, HttpServletRequest request,
                           HttpServletResponse response) {
        setCookie(new String[] { token.getSeries(), token.getTokenValue() },
                getTokenValiditySeconds(), request, response);
    }

    public void setSeriesLength(int seriesLength) {
        this.seriesLength = seriesLength;
    }

    public void setTokenLength(int tokenLength) {
        this.tokenLength = tokenLength;
    }

    @Override
    public void setTokenValiditySeconds(int tokenValiditySeconds) {
        Assert.isTrue(tokenValiditySeconds > 0,
                "tokenValiditySeconds must be positive for this implementation");
        super.setTokenValiditySeconds(tokenValiditySeconds);
    }
}
