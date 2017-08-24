package com.snsprj.fifter;

import com.snsprj.dto.User;
import com.snsprj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by skh on 2017/8/23.
 */
@Component
public class MyFilter implements Filter {

    @Autowired
    private IUserService iUserServiceImpl;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


//        HttpServletRequest req = (HttpServletRequest)servletRequest;
//
//        ServletContext sc = req.getSession().getServletContext();
//        XmlWebApplicationContext cxt = (XmlWebApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
//
//        if(cxt != null && cxt.getBean("iUserServiceImpl") != null && iUserServiceImpl == null)
//            iUserServiceImpl = (IUserService) cxt.getBean("iUserServiceImpl");

        User user =  iUserServiceImpl.getUserDetailByPrimaryKey(1);

        if(user == null){
            System.out.println("null");
        }else{
            System.out.println("not null" + user.getAccount());
        }

    }

    @Override
    public void destroy() {

    }
}
