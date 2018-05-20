<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<script type="text/javascript">
	//var title = '大帮汇-外汇智能投资工具';
	//var backImg = 'http://rtestapp.dbhapp.com/static/images/log_img.png';//'http://wechatapp.dbhapp.com/static/images/log_img.png';
	//var linkUrl = "http://rtestapp.dbhapp.com/signal/toSignalList?token=${sessionScope.token}";//"${wxSigunate.url}";
	//var linkUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=${sessionScope.appin_info}&redirect_uri=http://wechatapp.dbhapp.com/signal/toSignalList?token=${sessionScope.token}&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";//"${wxSigunate.url}";
	//var linkUrl = "http://betaweixin.dbhapp.com/signal/toSignalList?token=${sessionScope.token}";//"${wxSigunate.url}";
	//var linkUrl = "http://betaweixin.dbhapp.com/signal/toSignalList?token=${sessionScope.token}";//"${wxSigunate.url}";
	//var summary = '24小时全自动交易，年化收益可达50%以上';
	//var title = '外汇智能投资工具';
	//var backImg = 'http://betaweixin.dbhapp.com/static/images/log_img.png';
	/*var linkUrl = "https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI1NDQ5MDM3MQ==&scene=124#wechat_redirect";
	var summary = '欢迎关注大帮汇';  */
	var title="${sessionScope.weChatInfo.publicTitle}";
	var backImg="${sessionScope.weChatInfo.headImg}";
	//var linkUrl="http://rtestapp.dbhapp.com/Index/toSharePage.html?token=${sessionScope.token}";
	var linkUrl="http://wechatapp.dbhapp.com/Index/toSharePage.html?token=${sessionScope.token}";
	var summary='${sessionScope.weChatInfo.descprice}'; 
</script>
<script type="text/javascript">
/**
 * 关于分享微信的操作
 */

// 微信信息的以及调用的配置
wx.config({
    debug: false, 
    appId: '${sessionScope.appin_info}',//wx4294ae45244ca120  
    timestamp: '${wxSigunate.timestamp}', 
    nonceStr: '${wxSigunate.nonceStr}', 
    signature: '${wxSigunate.signature}',
    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage','onMenuShareQQ','onMenuShareWeibo','onMenuShareQZone','getLocation'] 
});

wx.ready(function(){
	//alert('${wxSigunate.url}');
    // 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
    wx.onMenuShareTimeline({
    	 title: title, // 分享标题
         desc: summary, // 分享描述
         link: linkUrl,
         imgUrl: backImg,
         type: 'link',//分享类型,music、video或link，不填默认为link
       	 trigger: function (res) {
                // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
               //alert('用户点击分享到朋友圈');
            },
            success: function (res) {
               //alert('已分享');
            },
            cancel: function (res) {
               //alert('已取消');
            },
            fail: function (res) {
               alert(JSON.stringify(res));
            }
    });
    // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
    wx.onMenuShareAppMessage({
        title: title, // 分享标题
        desc: summary, // 分享描述
        link: linkUrl,
        imgUrl: backImg,
        type: 'link', // 分享类型,music、video或link，不填默认为link
        trigger: function (res) {
            // 不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
           //alert('用户点击分享到朋友圈');
        },
        success: function (res) {
           //alert('已分享');
        },
        cancel: function (res) {
           //alert('已取消');
        },
        fail: function (res) {
           //alert(JSON.stringify(res));
        }
    });
    
    //获取“分享到QQ”按钮点击状态及自定义分享内容接口
    wx.onMenuShareQQ({
    	title: title, // 分享标题
        desc: summary, // 分享描述
        link: linkUrl,
        imgUrl:backImg,
        type: 'link', // 分享类型,music、video或link，不填默认为link
        success: function () { 
           // 用户确认分享后执行的回调函数
        },
        cancel: function () { 
           // 用户取消分享后执行的回调函数
        }
    });
    
    //获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口
    wx.onMenuShareWeibo({
    	title: title, // 分享标题
        desc: summary, // 分享描述
        link: linkUrl,
        imgUrl: backImg,
        type: 'link', // 分享类型,music、video或link，不填默认为link
        success: function () { 
           // 用户确认分享后执行的回调函数
        },
        cancel: function () { 
            // 用户取消分享后执行的回调函数
        }
    });
    
    //获取“分享到QQ空间”按钮点击状态及自定义分享内容接口
    wx.onMenuShareQZone({
        title: title, // 分享标题
        desc: summary, // 分享描述
        link: linkUrl, // 分享链接
        imgUrl: backImg, // 分享图标
        success: function () { 
           // 用户确认分享后执行的回调函数
        },
        cancel: function () { 
            // 用户取消分享后执行的回调函数
        }
    });
    
    //获取用户是否已经同意获取地理位置了
    function getLocation_local(){
    	 $.post("/myUserController/isAgreementLocation.do", {
 			"token":"APP"
 		}, function(data) {
 			var data = eval("(" + data + ")");
 			if(data.map.data!="Y"){
 				//获取地理位置
 			    wx.getLocation({
 			        success: function (res) {
 			            alert("小宝鸽获取地理位置成功，经纬度为：（" + res.latitude + "，" + res.longitude + "）" );
 			           	$.post("/myUserController/addUserAddress.do", {
 			           		"addrPrecision":res.longitude,
 			           		"addrLatitude":res.latitude
	 			   		}, function(data) {
	 			   			
	 			   		});
 			        },
 			        fail: function(error) {
 			            alert("获取地理位置失败，请确保开启GPS且允许微信获取您的地理位置！");
 			        }
 			    });
 				
 			}
 		});
    }
   
    getLocation_local();
   
});

</script>