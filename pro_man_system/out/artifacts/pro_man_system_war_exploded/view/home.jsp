<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: DSZ
  Date: 2019/4/11
  Time: 10:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script>
        function add() {
            var pname = prompt("请输入新的项目名", "");
            if (pname!=null){
                $.post("/Multi?method=addPor",{ "pname": pname},
                    function(data){
                        alert("添加成功")
                        window.location = "${pageContext.request.contextPath}/Multi?method=findAllPro";
                    });
            }

        }
    </script>
</head>
<body>

<div style="width: 100%">
    <c:if test="${user.username == '老师'}"><button onclick="add()">添加</button></c:if><div style="float: right;border: solid red 1px">${user.username}</div>
</div>
<div style="width: 100%">
    <table width="100%" border="1px" >
        <thead>
        <tr >
            <th>项目名</th>
            <th>班级</th>
            <th>最快者</th>
            <th>所用时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="thiz">
            <tr align="center">
                <td>${thiz.project.pName}</td>
                <td>${thiz.clazz.cName}</td>
                <td>${thiz.user.loginName}</td>
                <td>
                    <jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>
                    <jsp:setProperty property="time" name="dateObject" value="${thiz.rTime}"/>
                    <jsp:setProperty property="hours" name="dateObject" value="0"/>
                    <fmt:formatDate value="${dateObject}" pattern="HH:mm:ss:SS"></fmt:formatDate>
                  </td>
                <td>
                    <c:if test="${user.username == '老师'}"><a href="/Multi?method=revomePor&pid=${thiz.project.pId}">删除</a></c:if>
                    <a href="${pageContext.request.contextPath}/Multi?method=findAlltime&pid=${thiz.project.pId}">查看</a>
                    <a href="${pageContext.request.contextPath}/view/time.jsp?pid=${thiz.project.pId}">开始完成</a>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>


</body>
</html>
