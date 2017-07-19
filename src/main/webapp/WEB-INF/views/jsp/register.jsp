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
    <title>register</title>
</head>
<body>


    <div><input type="text" id="username"></div>
    <div><input type="password" id="password"/></div>
    <div><input type="button" value="register" class="register"></div>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>

    <script type="text/javascript">

        $(".register").click(function(){
            var username = $("#username").val();
            var password = $("#password").val();
            $.ajax({
                url:"/ssm/auth/register",
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
