package com.snsprj.filter;

import com.snsprj.dto.User;
import com.snsprj.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by skh on 2017/8/23.
 */
@Component
public class MyFilter implements Filter {

    @Autowired
    private IUserService iUserService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        User user =  iUserService.getUserDetailByPrimaryKey(1);

        if(user == null){
            System.out.println("null");
        }else{
            System.out.println("not null" + user.getAccount());
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
