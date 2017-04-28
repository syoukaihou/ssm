package com.snsprj.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/hello")
public class HelloController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	// 现代模式,
	// http://localhost:8080/ssm/hello/mvc/123?name=asd
	@RequestMapping(value="/mvc/{id}",method={RequestMethod.GET,RequestMethod.POST})
	public String hello(@RequestParam("name") String name,@PathVariable("id") Integer id){
		
		System.out.println("webapp.root的值为==>" + System.getProperty("webapp.root"));
		logger.info("获取的参数为：{},{}", name,id);
		return "jsps/hello";
	}
	
	// 传统模式
	@RequestMapping(value="/hi",method={RequestMethod.POST})
	public String hi(HttpServletRequest request){
		
		String name = request.getParameter("name");
		
		Integer age = Integer.valueOf(request.getParameter("age"));
		
		logger.debug("获取的参数为：{},{}", name,age);
		return "";
	}
}



