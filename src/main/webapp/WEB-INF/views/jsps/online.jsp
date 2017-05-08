<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
HashSet<HttpSession> sessionSet = (HashSet<HttpSession>)application.getAttribute("sessions");

int number = 0;
for(Iterator it = sessionSet.iterator();it.hasNext();){
	HttpSession session0 = (HttpSession)it.next();
	boolean flag = "yes".equals(session0.getAttribute("win"));
	if(flag){
		number ++;
	}
}

out.print("当前在线人数为：" + number);
%>
</body>
</html>