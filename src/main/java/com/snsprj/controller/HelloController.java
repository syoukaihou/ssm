package com.snsprj.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.snsprj.service.UserAuthService;

@Controller
public class HelloController {
	
	private static Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping(value="/mvc/{id}",method={RequestMethod.GET,RequestMethod.POST})
	public String hello(@RequestParam("name") String name,@PathVariable("id") Integer id){
		
		System.out.println("" + System.getProperty("webapp.root"));
		logger.info("{},{}", name,id);
		return "jsps/hello";
	}
	
	// 
	@RequestMapping(value="/hi",method={RequestMethod.POST})
	public String hi(HttpServletRequest request){
		
		String name = request.getParameter("name");
		
		Integer age = Integer.valueOf(request.getParameter("age"));
		
		logger.debug("{},{}", name,age);
		return "";
	}
	
	@Autowired
	UserAuthService userAuthService;
	
//	@RequestMapping(value="/mybatis",produces = "text/json;charset=UTF-8")
	@RequestMapping(value="/mybatis")
	@ResponseBody
	public String testMybatis() {
		
		boolean flag = userAuthService.login("123", "123");
		
		return "登录结果：" + flag;
	}
}



