$(document).bind("contextmenu", function () {
    return false;
})
$("#user_img").click(function () {
    toUserHome();
})
$(".menu-button:eq(0)").click(function () {
    toHomepage();
})
$(".menu-button:eq(1)").click(function () {
    toShare();
})

//菜单淡入淡出
// $(document).ready(function () {
//
//     $("#menu").delay(4000).animate({
//
//             opacity:'0'
//         },
//         1000,
//         function () {
//             $("#menu").stop(true)
//         });
//     $("#menu").mouseenter(function () {
//         if (!$("#menu").is(":animated"))
//         {
//             $(this).animate({
//
//                     opacity:'1'
//                 },
//                 500);
//         }
//         setTimeout(function () {
//             if (!$("#menu").is(":animated"))
//             {
//                 $("#menu").animate({
//                         opacity:'0'
//                     },
//                     1000);
//             }
//         },10000)
//     })
//     $("#menu").click(function () {
//         if (!$("#menu").is(":animated"))
//         {
//             $("#menu").animate({
//                     opacity:'1'
//                 },
//                 500);
//         }
//         setTimeout(function () {
//             if (!$("#menu").is(":animated"))
//             {
//                 $("#menu").animate({
//                         opacity:'0'
//                     },
//                     1000);
//             }
//         },10000)
//     })
// })
function toIndex() {
    window.open('/', '_self')
}

function toLogin() {
    window.open('/login', '_self')
}

function toRegister() {
    window.open('/register', '_self')
}

function toHomepage() {
    window.open('/homepage', '_self')
}

function toShare() {
    if (getCookie("email") != null && getCookie("email") != 0) {
        window.open('/share', '_self');
    } else {
        window.open('/login', '_self');
    }
}

function toUserHome() {
    if (getCookie("email") != null && getCookie("email") != 0) {
        window.open('/userHome', '_self');
    } else {
        window.open('/login', '_self');
    }
}

function toMyQuestion() {
    if (getCookie("email") != null && getCookie("email") != 0) {
        window.open('/myQuestion', '_self');
    } else {
        window.open('/login', '_self');
    }
}

function getCookie(name) {
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(name);
    if (cookie_pos != -1) {
        cookie_pos += name.length + 1;
        var cookie_end = allcookies.indexOf(';', cookie_pos);
        if (cookie_end == -1) {
            cookie_end = allcookies.length;
        }
        var value = unescape(allcookies.substring(cookie_pos, cookie_end))
    }
    return value;
}

function clearAllCookie() {
    var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
    var date = new Date();
    date.setTime(date.getTime() - 1);
    if (keys) {
        for (var i = keys.length; i--;) {
            document.cookie = keys[i] + '=0;expires' + date.toUTCString();
        }
    }
}
// var theme = true;

// function nextTheme() {
//     if (theme)
//     {
//         $("body").css({'background-color':'#1b6d85'});
//         $("#menu").css({'background-color':'#1b6d85'});
//         theme = false;
//     }
//     else
//     {
//         $("body").css({"background-color": "#f7fafc"});
//         $("#menu").css({'background-color':'#f7fafc'});
//         theme = true;
//     }
//
// }
$(document).ready(function () {
    menuIni();
    rootIni();
    $("#search_btn").click(function () {
        if ($.trim($("#search_input").val()) != '') {
            search($.trim($("#search_input").val()));
        } else {
            alert("请先输入")
        }
    })
    var f = false;
    $("#profile").click(function () {
        if (!f) {
            $(".dropdown-menu").css({"display": "block"});
            f = true
        } else {
            $(".dropdown-menu").css({"display": "none"});
            f = false
        }
    })
    $("body").css({"background-color": "#f7fafc"});
    $("#logo").click(function () {
        toIndex();

    })
    // $("#search_btn").click(function () {
    //     var val = document.getElementById('search_input').value;
    //     if (val == '我是猪猪')
    //     {
    //         alert('你真傻，🐖🐖')
    //     }
    // })
    // $("#user_img").click(function () {
    //     if (getCookie("email") != null && getCookie("email") != 0)
    //     {
    //         toUserHome();
    //     }
    //     else
    //     {
    //         toLogin();
    //     }
    // })
})

function logOut() {
    if (confirm('确认退出登录?')) {
        clearAllCookie();
        toIndex();
    }
}

function search(e) {
    $("body").html('' +
        '<div id="menu" class="container navbar navbar-default navbar-fixed-top" style="background-color: #d6e9c6">' +
        '</div>' +
        '<div style="position:absolute;top:10px;left: 0;right: 0">\n' +
        '    <div id="questions" class="container" style="margin-top: 6rem">' +
        '    </div>' +
        '</div>');
    menuIni();
    rootIni();
    $("#search_btn").click(function () {
        if ($("#search_input").val() != '') {
            search($("#search_input").val());
        } else {
            alert("请先输入")
        }
    })
    var f = false;
    $("#profile").click(function () {
        if (!f) {
            $(".dropdown-menu").css({"display": "block"});
            f = true
        } else {
            $(".dropdown-menu").css({"display": "none"});
            f = false
        }
    })
    $("body").css({"background-color": "#f7fafc"});
    $("#logo").click(function () {
        toIndex();
    })
    var question_list = new Array();
    var username_list = new Array();
    var q_list;
    var id;
    var email;
    var question;

    function toSearch(e) {
        $("#questions").html('')
        $.ajax({
            url: '/answer/select',
            type: 'POST',
            success: function (data) {
                answer_list = data;
                $.ajax({
                    url: '/question/search',
                    type: 'POST',
                    data: {
                        "e": e
                    },
                    success: function (data) {
                        if (data.length == 0) {
                            alert("无搜索结果");
                            toHomepage();
                        }
                        q_list = new Array(data.length);
                        question_list = data;
                        for (var i = 0; i < data.length; i++) {
                            q_list[i] = 0;
                            for (var j = 0; j < answer_list.length; j++) {
                                if (data[i].id == answer_list[j].question_id) {
                                    q_list[i]++;
                                }
                            }
                        }
                        for (var i = 0; i < q_list.length - 1; i++) {
                            for (var j = i + 1; j < q_list.length; j++) {
                                if (q_list[i] > q_list[j]) {
                                    var temp = q_list[i];
                                    q_list[i] = q_list[j];
                                    q_list[j] = temp;
                                    var temp2 = question_list[i];
                                    question_list[i] = question_list[j];
                                    question_list[j] = temp2;
                                }
                            }
                        }
                        var $root = $("#questions");
                        for (var i = question_list.length - 1; i >= 0; i--) {
                            var id = question_list[i].id;
                            var email = question_list[i].email;
                            var question = question_list[i].question;
                            var div = $("<div class='table-bordered' style='padding: 1rem;margin: 1rem'></div>")
                            $root.append(div)
                            var div1 = $("<div></div>")
                            var div2 = $("<div style='height: auto'></div>")
                            var br = $("<br>")
                            div.append(div1);
                            div.append(div2);
                            var span1 = $("<span style='color: #4cae4c ;font-size: 2.5rem;margin-left: 1rem;margin-right: 2rem;position: relative;top: 0.1rem'></span>")
                            if (i == question_list.length - 1) {
                                span1.css({"color": "#d43f3a", "font-size": "4rem"});
                            } else if (i == question_list.length - 2) {
                                span1.css({"color": "coral", "font-size": "3.5rem"});
                            } else if (i == question_list.length - 3) {
                                span1.css({"color": "#8a6d3b", "font-size": "3rem"});
                            }
                            var span2 = $("<span style='cursor: pointer;font-size: 2rem' onclick='toAnswer(" + id + ",\"" + email + "\",\"" + question + "\")'></span>");
                            var span3 = $("<span style='color: gray;margin-left: 4.5rem;font-size: 1.5rem'></span>")
                            var username = null;
                            for (var j = 0; j < username_list.length; j++) {
                                if (username_list[j].email == question_list[i].email) {
                                    username = username_list[j].username;
                                    break
                                }
                            }
                            span1.html(question_list.length - i)
                            span2.html(question_list[i].question);
                            span2.attr("title", question_list[i].question)
                            var num = 0;
                            for (var j = 0; j < answer_list.length; j++) {
                                if (answer_list[j].question_id == id) {
                                    num++;
                                }
                            }
                            span3.html(num + "条回答")
                            div2.append(span1);
                            div2.append(span2);
                            span2.append(br)
                            span2.append(span3)
                        }
                    }
                })
            }
        })
    }

    var answer_list = new Array();
    $.ajax({
        url: '/answer/select',
        type: 'POST',
        success: function (data) {
            answer_list = data;
            $.ajax({
                url: '/username/select',
                type: 'POST',
                success: function (data) {
                    username_list = data
                    toSearch(e);
                }
            })
        }
    })

    function toAnswer(id, email, question) {
        this.id = id;
        this.email = email;
        this.question = question;
        var $root = $("#questions");
        $("#questions").html('');
        var child = $("<div>\n" +
            "      <button id='returnSearch' class=\"btn btn-info\" onclick='toHomepage()'>返回</button>\n" +
            "    </div>")
        $root.append(child)
        var div1 = $("<div class='table-bordered' style='padding: 1rem'></div>")
        $root.append(div1);
        var span1 = $("<span style='font-size: 3rem'></span>")
        span1.html("问题id:" + id);
        div1.append(span1)
        var br = $("<br>")
        var div2 = $("<div></div>")
        div1.append(div2)
        var span2 = $("<span style='font-size: 2.5rem'></span>")
        var span3 = $("<span style='font-size: 2.5rem'></span>")
        var username = null;
        for (var i = 0; i < username_list.length; i++) {
            if (username_list[i].email == email) {
                username = username_list[i].username;
                break;
            }
        }
        span2.html("用户:" + username);
        span3.html("问题:" + question);
        div2.append(span2)
        div2.append(br)
        div2.append(span3);
        var $root = $("#questions");
        var flag = 0;
        for (var i = answer_list.length - 1; i >= 0; i--) {
            if (answer_list[i].question_id == id) {
                flag++;
                var div1 = $("<div class=\"table-bordered\" style=\"margin-top: 1rem;padding: 1rem\"></div>")
                $root.append(div1);
                var span1 = $("<span style='font-size: 2rem'></span>");
                var span2 = $("<span style='font-size: 2rem'></span>");
                var br = $("<br>")
                var username = null;
                for (var j = 0; j < username_list.length; j++) {
                    if (username_list[j].email == answer_list[i].email) {
                        username = username_list[j].username;
                        break;
                    }
                }
                span1.html("用户:" + username)
                span2.html("回答:" + answer_list[i].answer)
                div1.append(span1)
                div1.append(br)
                div1.append(span2)
            }
        }
        if (flag == 0) {
            var div1 = $("<div class=\"table-bordered\" style=\"margin-top: 1rem;padding: 1rem\">暂无回答</div>")
            $root.append(div1);
        }
        var d1 = $("<div class=\"form-group\" style=\"margin-top: 10rem\"></div>");
        $root.append(d1);
        var textarea = $("<textarea id=\"answer_text\" autofocus=\"autofocus\" placeholder=\"在这里填好你的回答，然后发送\" maxlength=\"500\" style=\"font-size: 20px;height: 50%;width: 100%;resize: none\"></textarea>")
        d1.append(textarea)
        var d2 = $("<div></div>")
        $root.append(d2)
        var btn = $("<button id=\"send\" class=\"btn btn-info\" onclick='send()'>发送</button>")
        d2.append(btn)
        $("#answer_text").html('');
    }

    function send() {
        if (getCookie("email") == null || getCookie("email") == 0) {
            toLogin();
        } else if ($.trim($("#answer_text").val()) != '') {
            var answer = {
                "question_id": id,
                "email": getCookie("email"),
                "answer": $("#answer_text").val()
            }
            $.ajax({
                url: '/answer/insert',
                type: 'POST',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(answer),
                success: function (data) {
                    $.ajax({
                        url: '/answer/select',
                        type: 'POST',
                        data: {
                            'id': id
                        },
                        success: function (data) {
                            answer_list = data;
                            toAnswer(id, email, question)
                        }
                    })
                }
            })
        } else {
            alert("请不要发送空内容")
        }
    }
}

function rootIni() {
    if (getCookie("email") == null || getCookie("email") == 0) {
        $("#share").remove();
        $("#question").remove();
        $("#logout").remove();
        if (window.location.pathname != '/' && window.location.pathname != '/login' && window.location.pathname != '/register' && window.location.pathname != '/homepage') {
            $("body").html('');
            toIndex();
        }
    }
}

function menuIni() {
    var $menu = $("#menu");
    var div1 = $("<div class=\"navbar-header\"></div>");
    var div2 = $("<div id=\"navbar\" class=\"collapse navbar-collapse\"></div>");
    $menu.append(div1);
    $menu.append(div2);
    var button = $("<button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\"></button>");
    div1.append(button);
    var a1 = $("<a id='logo' class=\"navbar-brand\" style=\"cursor:pointer;font-size: 35px;color: #5bc0de;font-family: 'Leelawadee UI' \" href='/'>问答</a>");
    div1.append(a1)
    var span1 = $("<span class=\"sr-only\">Toggle navigation</span>")
    var span2 = $("<span class=\"icon-bar\"></span>")
    var span3 = $("<span class=\"icon-bar\"></span>")
    var span4 = $("<span class=\"icon-bar\"></span>")
    button.append(span1);
    button.append(span2);
    button.append(span3);
    button.append(span4);
    var ul1 = $("<ul class=\"nav navbar-nav\"></ul>");
    div2.append(ul1);
    var li1 = $("<li class='whoActive'></li>")
    ul1.append(li1)
    var a2 = $("<a class=\"menu-button\" href=\"/homepage\">广场</a>");
    li1.append(a2)
    var li2 = $("<li id='question' class='whoActive'></li>")
    ul1.append(li2)
    var a3 = $("<a class=\"menu-button\" href=\"/question\">提问题</a>");
    li2.append(a3)
    var li4 = $("<li id=\"share\" class='whoActive'></li>")
    ul1.append(li4)
    var a5 = $("<a class=\"menu-button\" href=\"/share\">资源</a>");
    li4.append(a5)

    var ul2 = $("<ul class=\"nav navbar-nav navbar-sub pull-right\"></ul>");
    div2.append(ul2);
    var li7 = $("<li></li>")
    var li8 = $("<li></li>")
    ul2.append(li7);
    ul2.append(li8);
    var div3 = $("<div class=\"input-group \" style=\"width: 30rem;margin-top: 1rem;margin-right: 1rem\"></div>")
    li7.append(div3);
    var input = $("<input id='search_input' class=\"form-control\" type=\"text\" placeholder=\"搜索\" aria-describedby=\"search_btn\">")
    div3.append(input);
    var span3 = $("<span id=\"search_btn\" class=\"input-group-addon\" style='cursor: pointer'></span>");
    div3.append(span3);
    var span4 = $("<span class=\"glyphicon glyphicon-search\"></span>")
    span3.append(span4)

    var span4 = $("<span id=\"profile\" class=\"pull-right\" style=\"margin-left: 2rem;margin-top: 0.5rem;margin-right: 1rem\"></span>")
    li8.append(span4);
    var img = $("<img id=\"user_img\" title=\"个人中心\" alt=\"Home\" src=\"/img/profile/default.png\" style=\"cursor: pointer;border-radius: 50%;width: 5.5rem;height: 5.5rem\">")
    span4.append(img);
    if (getCookie("profile") != null && getCookie("profile") != 0 && getCookie("profile") != "") {
        $("#user_img").attr('src', '/img/profile/' + getCookie("profile"));
    }
    var ul3 = $("<ul id='dropdown-menu' class=\"dropdown-menu\" style=\"top: 95%\"></ul>")
    span4.append(ul3);
    var l1 = $("<li></li>")
    var l2 = $("<li></li>")
    // var l4 = $("<li id='theme'></li>")
    var l3 = $("<li id='logout'></li>")
    ul3.append(l1)
    ul3.append(l2)
    // ul3.append(l4)
    ul3.append(l3)
    var a7 = $("<a style='cursor: pointer' onclick='toUserHome()'>个人中心</a>")
    var a8 = $("<a style='cursor: pointer' onclick='toMyQuestion()'>我的问题</a>")
    // var a10 = $("<a style='cursor: pointer' onclick='nextTheme()'>切换主题</a>")
    var a9 = $("<a style='cursor: pointer' onclick='logOut()'>退出登录</a>")
    l1.append(a7)
    l2.append(a8)
    // l4.append(a10)
    l3.append(a9)
}