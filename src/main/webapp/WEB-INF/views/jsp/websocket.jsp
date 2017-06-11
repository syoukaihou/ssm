<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Insert title here</title>
	</head>
	
	<body>
	
		<script type="text/javascript">
			var url = 'ws://' + window.location.host + '/websocket/marco';
			
			// 打开WebSocket
			var sock = new WebSocket(url);
			
			// 处理连接开启事件
			sock.onopen = function(){
				console.log('Opening');
				sayMarco();
				
			}
			
			// 处理连接关闭事件
			sock.onclose = function(){
				console.log('Closing');
			}
			
			function sayMarco(){
				console.log('Sending Marco!');
				// 发送消息
				sock.send('Marco!');
			}
		</script>
	</body>
</html>