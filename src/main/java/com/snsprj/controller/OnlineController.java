package com.snsprj.controller;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OnlineController {

	@RequestMapping(value="online",method={RequestMethod.GET})
	public String online(HttpServletRequest request){
		HttpSession session = request.getSession();
		
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		HashSet<HttpSession> sessions = (HashSet<HttpSession>)application.getAttribute("sessions");
		

		if(sessions != null && sessions.size()>1 && !"yes".equals(session.getAttribute("win"))){
			
			return "jsps/hello";
        }else{
        	session.setAttribute("win", "yes");
        }
		
		return "jsps/online";
	}
}
