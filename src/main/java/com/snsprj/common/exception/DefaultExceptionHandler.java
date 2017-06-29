package com.snsprj.common.exception;

import com.snsprj.common.ServerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

/**
 * Created by skh on 2017/6/29.
 */
public class DefaultExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();

        // handle ConstraintViolationException
        if(ex instanceof ConstraintViolationException){

            // set status code
            response.setStatus(HttpStatus.OK.value());

            // set content type
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            // set character encoding
            response.setCharacterEncoding("UTF-8");

            response.setHeader("Cache-Control", "no-cache, must-revalidate");

            try {
                // TODO get detail error message

                response.getWriter().write("{\"success\":false,\"msg\":\" invalid parameter!\"}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




        return mv;
    }
}
