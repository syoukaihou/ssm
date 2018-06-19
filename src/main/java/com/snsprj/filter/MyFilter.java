package com.snsprj.filter;

import javax.servlet.http.Cookie;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Created by skh on 2017/8/23.
 */
@Component
public class MyFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        String origin = request.getHeader("Origin");
//        if (StringUtils.isNotBlank(origin)){
//            response.addHeader("Access-Controller-Allow-Origin", origin);
//        }
//
//        response.addHeader("Access-Controller-Allow-Methods","*");
//
//        // 支持所有自定义头
//        String acrHeader = request.getHeader("Access-Control-Request-Headers");
//        if (StringUtils.isNotBlank(acrHeader)){
//            response.addHeader("Access-Controller-Allow-Headers", acrHeader);
//        }
//
//        response.addHeader("Access-Controller-Max-Age","3600");
//        response.addHeader("Access-Controller-Allow-Credentials","true");


        logger.info("=====> test filter");
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
