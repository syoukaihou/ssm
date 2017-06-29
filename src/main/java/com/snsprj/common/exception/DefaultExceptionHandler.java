package com.snsprj.common.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snsprj.common.PagePath;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by skh on 2017/6/29.
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();

        // set status code
        response.setStatus(HttpStatus.OK.value());

        // set content type
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        // set character encoding
        response.setCharacterEncoding("UTF-8");

        response.setHeader("Cache-Control", "no-cache, must-revalidate");

        // handle ConstraintViolationException
        if(ex instanceof ConstraintViolationException){
            // 参数校验异常
            try {
                // TODO get detail error message

                Set<ConstraintViolation<?>> constraintViolations =
                        ((ConstraintViolationException) ex).getConstraintViolations();

                Iterator it = constraintViolations.iterator();

                System.out.println("看看有没有东西"+constraintViolations.size());

                while (it.hasNext()){
                    Object o = it.next();
                    System.out.println("看看内容是什么====>"+o);
                }


                response.getWriter().write("{\"success\":false,\"msg\":\" invalid parameter!\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            // 其他异常页面返回
            mv.setViewName(PagePath.error500);
        }

        return mv;
    }
}
