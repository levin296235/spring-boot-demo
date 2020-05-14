function register() {
    //跳转到注册界面register.html进行注册
    window.open("register.html", "_blank");  //_self,_parent,_top,_blank
}
function login() {
    //登录逻辑
    //jQuery写法
    var user = $('#user').val();
    var password = $('#password').val();
    // var rememberMe = $('#rememberMe').val();
    // var checked = $("input[name='checkbox']").prop("checked");
    // var checked  = $("#rememberMe").prop("checked",true);
    // alert("chekcke status == " + checked);
    //JavaScript原生写法
    //var user = document.getElementById('user').value;
    //var password = document.getElementById('password').value;
    debugger;
    $.ajax({
        type: "post",  //post put get 等等
        url: "/loginPage",
        //编写登录功能时，要将异步设置为false（缺省为true）
        //如果async是ture,对于FireFox浏览器，会刷新掉alert()弹出框的内容
        //对于Chrome浏览器，第一次注册时会执行error的回调函数，输出“请求在连接过程中出现错误..”
        async:false,
        data: {  //要传入ashx文件的数据
            "username": user,
            "password": password,
        },
        success: function (data, textStatus) {
            //连接至ashx文件成功时，执行函数
            //data是从ashx文件返回来的信息，可以是字符串也可以是一个对象
            //如果data是字符串，则可以输出data的值
            //如果data是对象，则可以将这个对象的各属性值赋给其他变量
            //textStatus是表示状态的字符串，这里textStatus的值是"success"
            debugger;
            console(data);
            alert("hello ==");
            window.top.window.location = "home.html";

            if (data == "ok") {
                alert("abc===");
                window.top.window.location = "home.html";
                // window.open("home.html", "_blank");
            }
            else {
                // alert(data);  //这里data是从ashx文件返回的“账户名或密码不存在..
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {  //连接至ashx文件失败时，执行函数
            //XMLHttpRequest在这个例子里没有用到
            //textStatus是表示状态的字符串，这里textStatus的值是"error"
            //errorThrown包含连接失败的信息，可以输出查看
            // alert("请求在连接过程中出现错误..\n" + errorThrown);
        }
    });
}