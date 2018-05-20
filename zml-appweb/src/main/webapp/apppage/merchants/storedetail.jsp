<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>商户详情</title>
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<style>
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
		</style>		
	</head>
	<body>
		<header class="businessStore-header StoreDetail-header">
			<a onClick="javascript :history.back(-1);">
				<img src="${ctxContents}img/left-a.png"/>
			</a>
			<h2>店铺详情</h2>
			<p class="businessStore-header-classify StoreDetail-share">
				<img src="${ctxContents}img/classify.jpg"/>
				<span>分享</span>
			</p>
		</header>
		<article class="StoreDetail-banner">
			<div class="businessStore-banner-logo">
				<a href="">
					<img src="${ctxContents}img/store-logo.jpg" alt="" id="logo"/>
					<div class="StoreDetail-score">
						<p id="departname">京博农业官方旗舰店<i></i></p>
						<span id="grade">9.4分</span>
					</div>
				</a>
			</div>
			<!-- <div class="businessStore-banner-follow" >
				<p><i></i>关注</p>
				<span>234人</span>
			</div> -->
		</article>
		<section class="StoreDetail-content">
			<div id="StoreDetail-Control" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">
				<a class="mui-control-item" href="#">
					<img src="${ctxContents}img/Store-all.png" alt="" />
					<span>全部商品</span>
				</a>
				<a class="mui-control-item" href="#">
					<img src="${ctxContents}img/Store-news.png" alt="" />
					<span>上新</span>					
				</a>					
				<a class="mui-control-item" href="#">
					<img src="${ctxContents}img/Store-sales.png" alt="" />
					<span>促销</span>						
				</a>
				<a class="mui-control-item" href="#">
					<img src="${ctxContents}img/Store-dynamic.png" alt="" />
					<span>店铺动态</span>						
				</a>
			</div>
			<div class="StoreDetail-evaluate">
				<ul class="StoreDetail-evaluate-ul">
					<li class="StoreDetail-pingjia">
						<span class="StoreDetail-li-span">商品评价</span>
						<p class="StoreDetail-li-fen"><span>9.59</span>分</p>
						<p class="StoreDetail-li-baifenbi">高于同行  <span>10.2</span>%</p>
					</li>
					<li class="StoreDetail-taidu">
						<span class="StoreDetail-li-span">服务态度</span>
						<p class="StoreDetail-li-fen"><span>9.59</span>分</p>
						<p class="StoreDetail-li-baifenbi">高于同行  <span>10.2</span>%</p>
					</li>
					<li class="StoreDetail-taidu">
						<span class="StoreDetail-li-span">物流速度</span>
						<p class="StoreDetail-li-fen"><span>9.59</span>分</p>
						<p class="StoreDetail-li-baifenbi">高于同行  <span>10.2</span>%</p>
					</li>					
				</ul>
			</div>
			<div class="StoreDetail-contact">
				<ul>
					<li>
						<a href="">
							<span>联系卖家</span>
							<i class="contact-tel"></i>
						</a>
					</li>
					<li>
						<a href="">
							<span>店铺二维码</span>
							<i class="contact-code"></i>
						</a>
					</li>
					<li>
						<a href="">
							<span>证照信息</span>
							<i class="contact-info"></i>
						</a>
					</li>					
				</ul>
			</div>
			<div class="StoreDetail-contact">
				<ul>
					<li>
						<div>
							<span>店铺简介</span>
							<p id="description">品类齐全 快递物流 	厂家直销 正品保障</p>
						</div>
					</li>
					<li>
						<div>
							<span>所在地区</span>
							<p id="address">黑龙江齐齐哈尔市</p>
						</div>
					</li>
					<li>
						<div>
							<span>开店时间</span>
							<p>2016-12-22</p>
						</div>
					</li>					
				</ul>
			</div>
			<div class="StoreDetail-brand">
				<h2>授权品牌</h2>
				<div>
					<p>京博农业</p>
					<p>京博</p>
				</div>
			</div>
		</section>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="main">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item" href="merchants">
				<span class="mui-icon mui-icon-chatboxes"></span>
				<span class="mui-tab-label">商品信息</span>
			</a>
			<a class="mui-tab-item mui-active" href="businessindetail">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">商家介绍</span>
			</a>
		</nav>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script>
			mui.init({
				swipeBack: false
			});	
			var merchantsId = "${merchantsId}";
			
			mui.init({
				swipeBack: false
			});
			
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				var targetTab = this.getAttribute('href');
				if(targetTab == "main"){
					location.href=activePath;
				}
				else if(targetTab == "merchants"){
					location.href=activePath+"merchantsController/toMerchantsInfo.do";
				}
				else{//转到商户介绍页面
					location.href = activePath+"merchantsController/toBusinessDetail.do?merchantsId=${merchantsId}";
				}
			});
		</script>
		
		<script src="${ctxContents}js/medicial/storedetail.js"></script>
	</body>

</html>