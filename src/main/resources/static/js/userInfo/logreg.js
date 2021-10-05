//切换js
document.querySelector('.img__btn').addEventListener('click', function() {
    document.querySelector('.dowebok').classList.toggle('s--signup')
});

layui.use(['form','layer'], function () {
    var form=layui.form;
    var $ = layui.jquery,
        layer = layui.layer;
});
//登录js
function submitlogin() {

    var username=$("#username").val();
    if (username.length == 0) {
        layer.tips("请输入账号", '#username', {
            tips: [1, "#0FA6D8"],
            tipsMore: !1,
            time: 1300
        });
        $("#username").focus();
        return;
    }
    var password=$("#password").val();
    if (password.length == 0) {
        layer.tips("请输入密码", '#password', {
            tips: [1, "#0FA6D8"],
            tipsMore: !1,
            time: 1300
        });
        $("#password").focus();
        return;
    }
    if (password.length > 20 || password.length < 5) {
        layer.tips("请输入合法密码", '#password', {
            tips: [1, "#FF5722"],
            tipsMore: !1,
            time: 1300
        });
        $("#password").focus();
        return;
    }

    $.ajax({
        url: basePath + "/user/login",
        data: {
            "username" : $('#username').val(),
            "password" : $('#password').val(),
        },
        // contentType: "application/json;charset=UTF-8", //发送数据的格式
        type: "post",
        dataType: "json", //回调
        beforeSend: function () {
            layer.load(1, { //icon支持传入0-2
                content: '登陆中...',
                success: function (layero) {
                    layero.find('.layui-layer-content').css({
                        'padding-top': '39px',
                        'width': '60px'
                    });
                }
            });
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (data) {
            if (data.status == 200) {
                layer.msg(data.message, {
                    time: 1000,
                    icon: 1,
                    offset: '100px'
                }, function () {
                    location.href= basePath +"/indexForUser?userID=" + data.data;
                });
            } else {
                layer.msg(data.message, {
                    time: 1000,
                    icon: 5,
                    offset: '100px'
                });
                $("#submitlg").removeClass("layui-btn-disabled");
                $("#submitlg").attr("disabled", false);
            }
        },error:function () {
            layer.msg("系统错误", {
                time: 1000,
                icon: 2,
                offset: '100px'
            });
        }
    });
}

function submitregister() {
    var phone=$("#userphone").val();
    var username2=$("#username2").val();
    var password2=$("#password2").val();
    var useremail=$("#useremail").val();

    if (username2.length == 0) {
        layer.tips("请输入用户名", '#username2', {
            tips: [1, "#0FA6D8"],
            tipsMore: !1,
            time: 1300
        });
        $("#username2").focus();
        return;
    }
    if (password2.length == 0) {
        layer.tips("请输入密码", '#password2', {
            tips: [1, "#0FA6D8"],
            tipsMore: !1,
            time: 1300
        });
        $("#password2").focus();
        return;
    }
    if (password2.length > 20 || password2.length < 5) {
        layer.tips("密码长度为：5-20", '#password2', {
            tips: [1, "#FF5722"],
            tipsMore: !1,
            time: 1500
        });
        $("#password2").focus();
        return;
    }

    $.ajax({
        url: basePath + "/user/register",
        data: {
            "username" : username2,
            "password" : password2,
            "mobilephone" : phone,
            "email" : useremail
        },
        //contentType: "application/json;charset=UTF-8", //发送数据的格式
        type: "post",
        dataType: "json", //回调
        beforeSend: function () {
            layer.load(1, { //icon支持传入0-2
                content: '注册中...',
                success: function (layero) {
                    layero.find('.layui-layer-content').css({
                        'padding-top': '39px',
                        'width': '60px'
                    });
                }
            });
        },
        complete: function () {
            layer.closeAll('loading');
        },
        success: function (data) {
            if (data.status == 201) {
                layer.msg(data.message, {
                    time: 1000,
                    icon: 2,
                    offset: '100px'
                });
            }else if (data.status == 200) {
                layer.msg(data.message, {
                    time: 1000,
                    icon: 1,
                    offset: '100px'
                }, function () {
                    layer.open({
                        type: 2,
                        title: '完善信息',
                        shadeClose: true,
                        shade: 0.8,
                        maxmin: true,
                        area: ['60%', '70%'],
                        content: basePath+'/user/perfectInfo?userID=' + data.data,
                        end: function () {
                            location.href=basePath+"/index";
                        }
                    });
                });
            }
            $("#submitrg").removeClass("layui-btn-disabled");
            $("#submitrg").attr("disabled", false);
        },error:function () {
            layer.msg("系统错误", {
                time: 1000,
                icon: 2,
                offset: '100px'
            });
        }
    });
}