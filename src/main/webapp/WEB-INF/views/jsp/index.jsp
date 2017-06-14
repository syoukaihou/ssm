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

    <input type="button" class="btn btn-default" id="btn" value="按钮"/>
    <p id="rep_msg"></p>

    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">

            alert("sfasgasg");
            $("#btn").click(function () {
                $.ajax({
                    url: "/ssm/category/add",
                    data: {"categoryName": "家居", "parentId": "0"},
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        $("#rep_msg").html(data.code);
                    },
                    error: function (data) {
                        $("#rep_msg").html(data.code);
                    }

                })
            });




    </script>
</body>
</html>
