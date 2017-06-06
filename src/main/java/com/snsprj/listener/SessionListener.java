package com.snsprj.listener;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {

		HttpSession session = se.getSession();
		
		
		// 在application范围由一个HashSet集保存所有的session
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		HashSet<HttpSession> sessions = (HashSet<HttpSession>)application.getAttribute("sessions");
		
		
		if (sessions == null) {
            sessions = new HashSet<HttpSession>();
            application.setAttribute("sessions", sessions);
		}
		
		// 新创建的session均添加到HashSet集中
        sessions.add(session);
        // 可以在别处从application范围中取出sessions集合

        // 然后使用sessions.size()获取当前活动的session数，即为“在线人数”
        
		 
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        @SuppressWarnings("unchecked")
		HashSet<HttpSession> sessions = (HashSet<HttpSession>) application.getAttribute("sessions");
        
        // 销毁的session均从HashSet集中移除
        if(sessions != null){
        	sessions.remove(session);
        } 
	} 
}


