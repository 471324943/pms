<%--suppress ALL --%>
<%--
  Created by IntelliJ IDEA.
  User: DSZ
  Date: 2019/4/11
  Time: 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
    <script>
        var pid = "${param.pid}";
        var  times ;
        var n= 0, timer=null;
        $(function () {
            var timess ;
            var s = 0;  //时
            var f = 0;  //分
            var m = 0;  //秒
            var ss = 0; //毫秒
            $("button").toggle(function () {
                $(this).text("提交成绩");
                clearInterval(timer);
                times = new Date().getTime();

                timer=setInterval(function () {
                    n++;
                    m=parseInt(n/60);
                    ss=parseInt(n%60);
                    if (m>=60){
                        n=0;
                        m = 0;
                        f++;
                    }
                    if (f>=60){
                        f=0;
                        s++;
                    }
                    $("#js").text(toDub(s)+":"+toDub(f)+":"+toDub(m)+":"+toDub(ss));
                },1000/60);
            },function () {
                times = new Date().getTime() - times;
                var ar = confirm('确认提交?');
                if (ar){
                    alert(times)
                    clearInterval(timer);
//                    var times = s+":"+f+":"+m+":"+ss;
                    $.post("/Multi?method=subByTime", {"time": times,"pid":pid},
                        function(data){
                            alert('提交成功');
                            window.location = "${pageContext.request.contextPath}/Multi?method=findAlltime&pid="+pid;
                        }, "json");
                }
            })
        })

        //补零
        function toDub(n){
            return n<10?"0"+n:""+n;
        }


    </script>


</head>
<body>
<div><a href="${pageContext.request.contextPath}/Multi?method=findAlltime&pid=${param.pid}">◀返回</a></div>
<div style="width: 100%;height: 100%;text-align: center">
    <span id="js" style="font-size: 50px">00.00.00.00</span>
    <div style="padding-top: 100px"><button style="height: 100px;width: 200px;background: turquoise;font-size: 30px">开始</button></div>
</div>


</body>
</html>
