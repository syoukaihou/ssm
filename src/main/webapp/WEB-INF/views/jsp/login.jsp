<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2017/6/11
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<!DOCTYPE html>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/css/bootstrap.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-3.3.7-dist/js/bootstrap.js"></script>

</head>
<body>

    <div>
        <label for="username">username:</label>
        <input type="text" id="username" placeholder="输入用户名"/>
    </div>

    <div>
        <label for="password">password:</label>
        <input type="password" id="password" placeholder="输入密码"/>
    </div>

    <div>
        <%-- 首次获取验证码图片，也可在此将src设为访问路径/getVerify --%>
        <img id="imgVerify" class="img-rounded" src="" alt="点击更换验证码" width="100" height="50" onclick="getVerify(this);">
        <input type="button" value="login" id="login" class="btn btn-info">
    </div>



    <script type="text/javascript">
        $(document.body).ready(function() {
            // 首次获取验证码
            $("#imgVerify").attr("src", "/ssm/captcha/img?" + Math.random());
        });

        // 获取验证码
        function getVerify(obj) {
            obj.src = "/ssm/captcha/img?" + Math.random();
        }

        $("#login").click(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url:"/ssm/auth/login",
                data:{"username":username,"password":password},
                type:"post",
                dataType:"json",
                async:false,
                success:function(data){
                    console.log("请求成功返回！");

                    console.log(data);
                    if(data.code == 0){
                        window.location.href="/ssm/user/index";
                    }else if(data.code == 30002){
                        alert("用户名或密码错误！");
                    }

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
