$(function () {
    $("input[name='loginName']").blur(function () {
        var value = $(this).val();
        $("#user").remove();
        if (value) {
            var aaa = /^[a-zA-Z]\w{4,10}$/;
            if (aaa.test(value)) {
                $(this).parent().append("<span id='user' class='yes'>用户名可用<span>")
            } else {
                $(this).parent().append("<span class='error'  id='user'>用户名格式不正确<span>")
            }
        } else {
            $(this).parent().append("<span class='error'  id='user'>用户名不能为空<span>")
        }
    });
    $("input[name='password']").blur(function () {
        var value = $(this).val();
        $("#pass").remove();
        if (value) {
            var aaa = /^\d{6,20}$/;
            if (aaa.test(value)) {
                $(this).parent().append("<span id='pass' class='yes'>密码可用<span>")
            } else {
                $(this).parent().append("<span class='error'  id='pass'>密码格式不正确<span>")
            }
        } else {
            $(this).parent().append("<span class='error'  id='pass'>密码不能为空<span>")
        }
    });
    $("input[name='reword']").blur(function () {
        $("#re").remove();
        if ($("input[name='password']").val() == $(this).val()) {
            $(this).parent().append("<span class='yes'  id='re'>密码一致<span>")
        } else {
            $(this).parent().append("<span class='error'  id='re'>密码不一致<span>")
        }
    })
    $("input[name='username']").blur(function () {
        if ($(this).val()==""){
            $("#nameLast").addClass("error");
            $("#nameLast").text("昵称不能为空!");
        }
    })
    $(":input").focus(function () {
        if ($(this).is($("input[name='loginName']"))) {
            $("#user").remove();
            $(this).parent().append("<span class='ts'  id='user'>以字母开头的5~20位数字或字母组合 <span>")
        } else if ($(this).is($("input[name='password']"))) {
            $("#pass").remove();
            $(this).parent().append("<span class='ts' id='pass'>6~20位数字组成  <span>")
        } else if ($(this).is($("input[name='reword']"))) {
            $("#re").remove();
            $(this).parent().append("<span class='ts' id='re'>请再输入一次  <span>")
        }
    })
})

$(function () {
    $("#name").change(function () {
        var ar = $(this).val();
        $.post("/Login?method=recah", {name: ar},
            function (data) {
                if (data  == "已经存在该昵称") {
                    $("#nameLast").addClass("error");
                } else {
                    $("#nameLast").removeClass("error")
                    $("#nameLast").addClass("yes");
                }
                $("#nameLast").text(data)
            });
    })
})