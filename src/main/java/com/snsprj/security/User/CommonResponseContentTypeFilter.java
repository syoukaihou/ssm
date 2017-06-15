package com.snsprj.security.User;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by John on 2017/6/15.
 */
public class CommonResponseContentTypeFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("application/json;charset=UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
