<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2017/6/11
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>HomePage</title>
</head>
<body>

    <%-- 首次获取验证码图片，也可在此将src设为访问路径/getVerify --%>
    <img id="imgVerify" src="" alt="点击更换验证码" width="100" height="50" onclick="getVerify(this);">

    <div><input type="text" id="username"></div>
    <div><input type="password" id="password"/></div>
    <div><input type="button" value="login" class="login"></div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        $(document.body).ready(function() {
            // 首次获取验证码
            $("#imgVerify").attr("src", "/ssm/captcha/img?" + Math.random());
        });

        // 获取验证码
        function getVerify(obj) {
            obj.src = "/ssm/captcha/img?" + Math.random();
        }

        $(".login").click(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url:"/ssm/test/post/login",
                data:{"username":username,"password":password},
                type:"post",
                dataType:"json",
                async:false,
                success:function(data){
                    console.log("请求成功返回！");

                    console.log(data);
                },
                error:function(data){
                    alert(data);
                    console.log(data);
                }
            });
        });
    </script>
</body>
</html>
