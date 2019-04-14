<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
    <meta http-equiv="Content-Language" content="zh-cn">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/css/Style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>

    <script src="${pageContext.request.contextPath}/js/ds.js"></script>
    <script>
        function dsz() {
            var op = {
                type: 'post',
                url: '/Login?method=register',
                dataType: 'json',
                success: function (data) {
                    if (data.urls == '1') {
                        window.location = "${pageContext.request.contextPath}/login/login.jsp";
                    } else {
                        $("#test").text(data.urls);
                    }

                }
            };
            $("form input").trigger("focus");
            alert($(".error").size())
            if ($(".error").size() == 0) {
                $("#Form").ajaxSubmit(op);
            }

        }
    </script>
    <style>

        #back {
            background: url("${pageContext.request.contextPath}/images/2.jpg") no-repeat center top;
            background-size: 600px 500px;
        }

        font {
            font-size: 15px;
        }

    </style>

</head>

<body>

<table width="452" height="400" border="1" cellpadding="0" cellspacing="0" style="margin-top: 130px" align="center">
    <tr style="height: 100%">
        <td align="center" id="back">
            <form id="Form" action="${pageContext.request.contextPath}/login/home.jsp"
                  method="post">
                <input type="hidden" name="method" value="login">
                <table border="0" align="center" cellpadding="2" cellspacing="0">
                    <tr align="center">
                        <td colspan="2">
                            <strong style="font-size: 17px;">请注册</strong>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <font color="000F60"><strong>真实姓名:</strong> </font>
                        </td>
                        <td>
                            <input type="text" name="username" id="name" class="test" style="width: 160px;"/><font id="nameLast">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <font color="000F60"><strong>用户名:</strong> </font>
                        </td>
                        <td>
                            <input type="text" name="loginName" id="loginName" class="test" style="width: 160px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong><font color="000F60">密码: </font> </strong>
                        </td>
                        <td>
                            <input type="password" name="password" id="loginPwd" class="text" style="width: 160px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong><font color="000F60">确认密码: </font> </strong>
                        </td>
                        <td>
                            <input type="password" name="reword" class="text" style="width: 160px;"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong><font color="000F60">选择班级: </font> </strong>
                        </td>
                        <td>
                            <select name="cid">
                                <option value="1">JAVA12</option>
                                <option value="2" selected="selected">JAVA34</option>
                            </select>
                        </td>
                    </tr>
                    <tr>

                    <tr>
                        <td align="center" colspan="2">
                            <input type="button" value="注册" onclick="dsz()" class="buttoninput"/>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>

</table>

</body>
</html>
