<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: DSZ
  Date: 2019/4/11
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


    <table width="100%" border="1px" >
        <thead>
        <tr >
            <th>排名</th>
            <th>班级</th>
            <th>姓名</th>
            <th>所用时间</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="thiz" varStatus="va">
            <tr align="center">
                <td>${va.count}</td>
                <td>${thiz.clazz.cName}</td>
                <td>${thiz.user.loginName}</td>
                <td>   <jsp:useBean id="dateObject" class="java.util.Date" scope="page"></jsp:useBean>
                    <jsp:setProperty property="time" name="dateObject" value="${thiz.rTime}"/>
                    <jsp:setProperty property="hours" name="dateObject" value="0"/>
                    <fmt:formatDate value="${dateObject}" pattern="HH:mm:ss:SS"></fmt:formatDate></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


