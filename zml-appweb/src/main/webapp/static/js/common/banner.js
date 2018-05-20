/**
 * Created by Administrator on 2016/6/23.
 */
var pt = {
    x: 0,
    y: 0
}
var pt1 = {
    x: 0,
    y: 0
}
var intx = 0;
var ping = 0;
// 当前在第几屏
var total = 4;
//总屏幕数
var pingw = 480;
// 默认一屏幕360
$(function() {
    var li = $(".main-nei ul li").clone()
    //克隆
    $(".main-nei ul").append(li)
    // js
})
$(window).bind("load resize", function() {
    var abc = $(window).width();
    if (abc >= 750) {
        abc = 750;
    }
    pingw = abc;
    $(".main-nei ul li").css({
        width: abc + "px",
        height: (abc / 480) * 189 + "px"
    })
    //
    $(".main-wai").css({
        width: abc + "px",
        height: (abc / 480) * 189 + "px"
    })
    $(".main-nei").css({
        left: -pingw * ping + "px"
    })
})
var timer = window.setInterval(goRight, 4000)
function goRight() {
    ping++
    if (ping > 2 * total - 1) {
        $(".main-nei").css({
            left: -pingw * (total - 1) + "px"
        })
        ping = total;
    }
    $(".main-nei").animate({
        left: -pingw * ping + "px"
    }, 800)
    //  $(".ctrl ul li").removeClass("current").eq(ping%total).addClass("current")
}
function begin(e) {
    window.clearInterval(timer)
    pt.x = e.targetTouches[0].clientX;
    intx = parseInt($("#main-nei").css("left"))
    if (ping == 2 * total - 1) {
        $(".main-nei").css({
            left: -pingw * (total - 1) + "px"
        })
        ping = total - 1;
        intx = -pingw * (total - 1);
    } else if (ping == 0) {
        $(".main-nei").css({
            left: -pingw * (total) + "px"
        })
        ping = total;
        intx = -pingw * (total);
    }
    e.preventDefault();
}
function mov(e) {
    pt1.x = e.targetTouches[0].clientX;
    var off = pt1.x - pt.x;
    var zuix = intx + off
    $("#main-nei").css("left", zuix + "px")
}
function ed(e) {
    pt1.x = e.changedTouches[0].clientX;
    var off = pt1.x - pt.x;
    // 判断用户 往左滑动大于50  往右滑动大于50
    if (off <= -50) {
        //alert("左滑动")
        ping++;
        if (ping > 2 * total - 1) {
            ping = 2 * total - 1;
        }
    } else if (off >= 50) {
        //alert("右滑动")
        ping--;
        if (ping < 0) {
            ping = 0;
        }
    } else {}
    $("#main-nei").animate({
        left: -pingw * ping + "px"
    }, 800, function() {
        timer = window.setInterval(goRight, 5000)
    })
    //  $(".ctrl ul li").removeClass("current").eq(ping%total).addClass("current")
}
