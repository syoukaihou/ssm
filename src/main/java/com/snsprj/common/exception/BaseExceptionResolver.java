package com.snsprj.common.exception;

import com.snsprj.common.PagePath;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * Created by skh on 2017/6/28.
 */
public class BaseExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        if(ex instanceof ConstraintViolationException){

            System.out.println("参数不对！");

            return new ModelAndView(PagePath.index);
        }




        return super.doResolveException(request, response, handler, ex);
    }
}
