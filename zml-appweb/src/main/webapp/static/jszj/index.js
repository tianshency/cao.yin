;
(function ($) {
    var btn = true;
    /*截取地址栏信息*/
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }
    var sd_invite_code = GetQueryString("sd_invite_code") || '';
    var tid = GetQueryString("tid");
    var source = GetQueryString("source");
    var sudai_plat_fr ="channel_801";//GetQueryString("sd_plat_fr");
    /*cookie*/
    if (sudai_plat_fr) {
        $.cookie('sd_plat_fr', sudai_plat_fr, {
            expires: 1,
            path: '/'
        });
    }
    /*借款期限*/
    var $moneySpan = $('.month>span');
    $moneySpan.click(function () {
        var m = $(this).attr('data-value');
        var money = $('.my_account').text();
        var per = parseInt(money * 0.003 + money / m);
        $('.bantopr').html(per);
        $(this).addClass('choice').siblings().removeClass('choice');
    });

    function toAPP() {
        var Terminal = {
            platform: (function () {
                var u = navigator.userAgent,
                    app = navigator.appVersion;
                return {
                    android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
                    iPhone: u.indexOf('iPhone') > -1 || /(iPhone|iPod|iOS)/i.test(u.userAgent) || !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
                    iPad: u.indexOf('iPad') > -1 || /(iPad)/i.test(u.userAgent),
                    weChat: u.indexOf('MicroMessenger') > -1
                };
            })(), // 终端的语言
            language: (navigator.browserLanguage || navigator.language).toLowerCase()
        };;
        if (Terminal.platform.weChat) {
            window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.yeer.sdzj";
        } else {
            if (Terminal.platform.iPhone) {
                window.location.href = "https://itunes.apple.com/cn/app/id1192130188?mt=8";
            } else {
                window.location.href = "http://download.sudaizhijia.com/android/sudaizhijia/v2/sdzj-last_release-cpa-sign.apk";
            }
        }
    }
    window.onload = function () {
        //        var myscroll = new iScroll('wrapper', {
        //            vScrollbar: false
        //        })
        var sd_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        /*接口提交*/
        //        var api_sudaizhijia_host = sd_protocol + "uat.api.sudaizhijia.com";
        var api_sudaizhijia_host = sd_protocol + "api.sudaizhijia.com";
        /*手机号正则*/
        var phonez = /^1[3|4|5|7|8]\d{9}$/;
        //点击＇点击借款＇按钮
        $("input[type='button']").click(function () {
            if (btn) {
                btn = false;
                setTimeout(function () {
                    btn = true;
                }, 3000)
                phoneVal = $("input[type='tel']").val();
                if (phonez.test(phoneVal)) {
                	//将手机号码存入我方数据库
					var jsondata =  {
                            "phone": phoneVal,
                            "tid":tid,
                            "source":source
                        };
      
					$.ajax({
                        url: "appRegeditController.do?sendPhone&phone="+phoneVal+"&tid="+tid+"&source="+source,
                        type:'get',   
				        dataType : 'json',   
				        success  : function(data) {  
				            alert(123);  
				        },  
				        error : function(XMLHttpRequest, textStatus, errorThrown) {  
				            alert(XMLHttpRequest.status);
							 alert(XMLHttpRequest.readyState);
							 alert(textStatus); 
				        }  
                    })
                    //判断手机号是否注册
                    $.ajax({
                        url: api_sudaizhijia_host + "/v1/sms/check",
                        type: "post",
                        dataType: "json",
                        data: {
                            "mobile": phoneVal
                        },
                        success: function (json) {
                            if (json.error_code == 1124) {
                                maAjax(); //发送验证码
                            } else if (json.error_code == 1115) {
                                //已注册手机号直接跳转
                                $.toast("手机号已注册");
                                setTimeout(function () {
                                    toAPP();
                                }, 3000)
                            } else {
                                $.toast(json.error_message)
                            }
                        }
                    })
                } else {
                    $(".popup").css({
                        "display": "block"
                    }).fadeIn(500).delay(2000).fadeOut(500);
                }
            } else {
                return false;
            };
        });
        /*倒计时*/
        function daojishi() {
            $('#djs').show();
            $('#again').hide();
            var time = 60;
            t = setInterval(function () {
                time--;
                $(".time").html(time);
                if (time == 0) {
                    clearInterval(t);
                    $('#djs').hide();
                    $('#again').show();
                    $(".time").html('60');
                }
            }, 1000)
        }
        $('#again').click(function () {
            maAjax();
        });
        $(".coverBtn").click(function () {
            $(".cover").hide();
            clearInterval(t);
            $(".time").html('60');
        });
        /*给未注册手机号发送短信验证码*/
        function maAjax() {
            $.ajax({
                url: api_sudaizhijia_host + "/v1/sms/register",
                type: "post",
                dataType: "json",
                data: {
                    "mobile": phoneVal,
                    "version": "4"
                },
                success: function (json) {
                    if (json.code == 200 && json.error_code == 0) {
                        codeSign = json.data.sign;
                        $(".cover").show();
                        daojishi()
                    } else {
                        $.toast(json.error_message)
                    }
                }
            })
        };
        //短信验证码判断
        $(".sureBtn").click(function () {
            var ma = $(".ma").val();
            if (ma.length == 4) {
                $(".landingCover").show();
                $.ajax({
                    url: api_sudaizhijia_host + "/v1/auth/quicklogin",
                    type: "post",
                    dataType: "json",
                    data: {
                        "mobile": phoneVal,
                        "code": ma,
                        "version": "4",
                        "sign": codeSign,
                        "channel_fr": $.cookie('sd_plat_fr') || '',
                        "sd_invite_code": sd_invite_code
                    },
                    success: function (json) {
                        if (json.code == 200 && json.error_code == 0) {
                            setTimeout(function () {
                                $(".landingCover").hide();
                                toAPP();
                            }, 1000);
                        } else {
                            $(".landingCover").hide();
                            $.toast(json.error_message);
                        }
                    }
                })
            }
        })
    }
})(jQuery);
