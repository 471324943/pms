<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%--
  Created by IntelliJ IDEA.
  User: DSZ
  Date: 2019/4/11
  Time: 9:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="../js/jquery-1.8.3.min.js"></script>
    <script>
        $(function () {
            var pid = "${param.pid}";
            function extracted(val) {
                $.post("${pageContext.request.contextPath}/Multi?method=sort", {"val": val, "pid": pid},
                    function (data) {
                        $("#all").html(data)
                    });
            }
            extracted(0);
            $("#pl").change(function () {
                var val = $(this).val();
                extracted(val);
            })
        })

        function px(th) {
            $(th).text($(th).text()=='倒序'?"正序":"倒序");
            $("tr:gt(0)").each(function (i,ar) {
                if (i== $("tr:gt(0)").size()-1){
                    return
                }
                $("tr:last").insertBefore($("tr:eq("+ ++i +")"));
            })
        }
    </script>
</head>
<body>

<div style="width: 100%;border: solid red 1px">
    <a href="${pageContext.request.contextPath}/Multi?method=findAllPro">◀返回</a>
    <select id="pl">
        <option value="0" >总排行</option>
        <c:forEach var="clazz" items="${clazzes}">
            <option value="${clazz.cId}">${clazz.cName}</option>
        </c:forEach>
    </select>
    <button onclick="px(this)">倒序</button>
</div>
<div style="width: 100%" id="all">
</div>

</body>
</html>
