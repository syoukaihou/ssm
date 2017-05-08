<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 首次获取验证码图片，也可在此将src设为访问路径/getVerify -->
<img id="imgVerify" src="" alt="点击更换验证码" width="95" height="25" onclick="getVerify(this);">

<input type="test">




<script type="text/javascript" src="${pageContext.request.contextPath}/public/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
$(document.body).ready(function () {
    //首次获取验证码
    $("#imgVerify").attr("src","/ssm/getVerify?"+Math.random());
});

//获取验证码
function getVerify(obj){
    obj.src = "/ssm/getVerify?"+Math.random();
}

</script>
</body>
</html>