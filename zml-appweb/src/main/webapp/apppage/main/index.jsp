<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<meta name="format-detection" content="telephone=no">
<title>首页</title>
	<!--标准mui.css-->
	<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
	<!--App自定义的css-->
	<%-- <link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" /> --%>
	<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
	<link href="${ctxContents}css/index.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" type="text/css" href="${ctxContents}css/icons-extra.css" />
	<%-- <link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css" /> --%>
	<link rel="stylesheet" type="text/css" href="${ctxContents}css/common/style.css"/>
   	<link rel="stylesheet" type="text/css" href="${ctxContents}css/common/base.css"/>
   	<link rel="stylesheet" type="text/css" href="${ctxContents}css/common/media.css"/>
   	<link rel="stylesheet" type="text/css" href="${ctxContents}css/alertTip/sweet-alert.css"/>
	<script src="${ctxContents}js/common/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
   	<script src="${ctxContents}js/common/mui.min.js"></script>
   	<script src="${ctxContents}js/common/banner.js" type="text/javascript" charset="utf-8"></script>
   	<script type="text/javascript" charset="utf-8">
     		mui.init();
   	</script>
   	
   	<style type="text/css">
   		.home-list-main>ul>li{
   			width:48.5%;
   			display: inline-block;
   			margin-left: 1%;
   		}
   	</style>
</head>

<body>
	<nav id="home-bar" class="mui-bar mui-bar-tab home-footer-bar">
		<a class="home-nav-classify mui-tab-item mui-active" href="main.html">
			<i></i>
			<span class="mui-tab-label">首页</span>
		</a>
		<a class="home-nav-fabu mui-tab-item" href="releasetype">
			<i></i>
			<span class="mui-tab-label">发布</span>
		</a>
		<a class="home-nav-zixun mui-tab-item" href="information">
			<i></i>
			<span class="mui-tab-label">咨询</span>
		</a>
		<a class="home-nav-my mui-tab-item" href="toPersonal">
			<i></i>
			<span class="mui-tab-label">我的</span>
		</a>
	</nav>
	<header class="home-header">
		<div class="main-wai" ontouchstart="begin(event)"
			ontouchmove="mov(event)" ontouchend="ed(event)">
			<div class="main-nei" id="main-nei" style="left: 0;" >
				<ul id="parentTemplate">
					<li id="templateBanner" style="display:none;"><a href="#"> <img src="${ctxContents}img/index/home01.jpg" alt="" /> </a></li>
				</ul>
			</div>
		</div>
	</header>
	<section>
		<div class="home-nav" id="cropTemplateDiv">
			<a href="productList.html"> <img src="img/nav01.jpg"> <span>农药</span> </a> 
		</div>
		<div class="home-headlines">
			<b>热门头条：</b>
			<div class="headlines-text">
				<ul id="headerlineParent">
					<li id="headerlineTemp" style="display:none;"><a href="#">你是我的小丫小苹果 </a></li>
				</ul>
			</div>
			<div class="headlines-btn">
				<b>｜</b> <a href="">更多</a>
			</div>
		</div>
		<div class="home-partner">
			<div class="home-partner-title">
				<h2>合作商家</h2>
				<a href="${ctx }merchantsController/toMerchantsList.do">更多</a>
			</div>
			<div class="home-partner-img" id="merchTemplateDiv" >
			</div>
		</div>
		<div class="home-list">
			<div class="home-partner-title">
				<h2>最新推荐</h2>
				<i></i>
			</div>
			<div class="home-list-main">
				<ul id="commodityParent">
					<li id="commodityTemplate" style="display:none;">
						<a href="" id="commodityHref"> 
							<img src="${ctxContents}img/index/homeList01.jpg" id="commodityImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>
							<div class="home-list-text">
								<p id="commodityName">此处是文本内容，商品介绍文字,此处是文本内容，商品介绍文字</p>
								<div class="home-list-price">
									￥<span id="price">599</span>.00
								</div>
								<div class="home-list-time">
									<span id="shopName">京东商城</span> <span id="shopTime">07-12 12:22</span>
									<p>
										运费：<span id="fare">12</span>元
									</p>
								</div>
							</div>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</section>
	<script src="${ctxContents}layer/layer.js"></script>
	<script type="text/javascript" src="${ctxContents}js/medicial/index.js"></script>
	<script type="text/javascript" src="${ctxContents}js/alerttip/sweet-alert.js"></script>
	<script type="text/javascript" src="${ctxContents}js/alerttip/showAlert.js"></script>
	<script>
		//滚动
		function autoScroll(obj) {
			$(obj).find("ul").animate({
				marginTop : "-39px"
			}, 500, function() {
				$(this).css({
					marginTop : "0px"
				}).find("li:first").appendTo(this);
			})
		}
		$(function() {
			setInterval('autoScroll(".headlines-text")', 2000);

		})
		mui('.mui-bar-tab').on(
				'tap',
				'a',
				function(e) {
					var targetTab = this.getAttribute('href');
					if (targetTab == "main.html") {
						return;
					} else if (targetTab == "releasetype") {
						location.href = activePath
								+ "releaseInfoController/toReleaseTypePage.do";
					} else if (targetTab == "information") {
						location.href = activePath
								+ "releaseInfoController/toReleaseZiXun.do";
					} else {
						location.href = activePath
								+ "personalController/toPersonalPage.do";
					}
				});
	</script>
	<%@include file="../common/wxconfig.jsp"%>
</body>

</html>