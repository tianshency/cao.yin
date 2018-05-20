<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>商户信息</title>
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css" />
		<style>
			.mui-control-content .mui-loading {
				margin-top: 50px;
			}
		</style>		
	</head>
	<body>
		<header class="businessStore-header">
			<a onClick="javascript :history.back(-1);">
				<img src="img/left-a.png"/>
			</a>
			<!--<div class="mui-input-row mui-search">
				<input type="search" class="mui-input-clear" placeholder="搜索店铺内商品">
			</div>-->
			<h2>店铺信息</h2>
			<p class="businessStore-header-classify">
				<img src="img/classify.jpg"/>
				<span>分类</span>
			</p>
			<p class="businessStore-header-more">
				<a href="#Store-header-more">···</a>
				<span></span>
			</p>
			<div id="Store-header-more" class="mui-popover">
				<!--<div class="mui-popover-arrow1"></div>-->
				<i></i>
				<div class="my-Popover-list">
					<div>
						<ul class="myPopover-ul">
							<li><i></i><span>消息</span></li>	
							<li><i></i><span>首页</span></li>
							<li><i></i><span>分享</span></li>
							<li><i></i><span>店铺详情</span></li>
						</ul>
					</div>
				</div>
			</div>
		</header>
		<article class="businessStore-banner">
			<div class="businessStore-banner-logo">
				<a href="">
					<img src="img/store-logo.jpg" alt="" />
					<div class="Store-banner-score">
						<p>京博农业官方旗舰店<i></i></p>
						<span>9.4分</span>
					</div>
				</a>
			</div>
			<div class="businessStore-banner-follow">
				<p><i></i>关注</p>
				<span>234人</span>
			</div>
		</article>
		<section class="businessStore-content">
			<div id="slider" class="mui-slider">
				<!--<div id="sliderSegmentedControl" class="mui-slider-indicator mui-segmented-control mui-segmented-control-inverted">-->
					<!--<a class="mui-control-item mui-active" href="#Store-home">
						<img src="img/Store-home.png" alt="" />
						<span>店铺首页</span>
					</a>-->
					<!--<a class="mui-control-item mui-active" href="#Store-all">
						<img src="img/Store-all.png" alt="" />
						<span>全部商品</span>
					</a>-->
					<!--<a class="mui-control-item" href="#Store-sales">
						<img src="img/Store-sales.png" alt="" />
						<span>促销</span>						
					</a>
					<a class="mui-control-item" href="#Store-news">
						<img src="img/Store-news.png" alt="" />
						<span>上新</span>					
					</a>
					<a class="mui-control-item" href="#Store-dynamic">
						<img src="img/Store-dynamic.png" alt="" />
						<span>店铺动态</span>						
					</a>					-->
				<!--</div>-->
				<div id="sliderProgressBar" class="mui-slider-progress-bar border-btm"></div>
				<div class="mui-slider-group Store-tab">
					<div id="Store-home" class="mui-slider-item mui-control-content">
						<div class="Store-home">
							<div class="Store-home-img">
								<img src="img/store-home1.jpg" alt="" />
								<img src="img/store-home2.jpg" alt="" />
								<img src="img/store-home2.jpg" alt="" />
							</div>
						</div>
						<div class="Store-home-order">
							<h2>超值订单</h2>
							<ul class="Store-home-order-ul">
								<li class="Store-home-order-li">
									<div class="Store-home-order-img">
										<img src="img/store-home-order1.jpg" alt="" />
									</div>
									<p class="Store-home-order-text">京博黄 草莓花芽分化套餐</p>
									<p class="Store-home-order-price">￥<span>25</span>.00</p>
								</li>
								<li class="Store-home-order-li">
									<div class="Store-home-order-img">
										<img src="img/store-home-order2.jpg" alt="" />
									</div>
									<p class="Store-home-order-text">京博黄 草莓花芽分化套餐 防治套餐</p>
									<p class="Store-home-order-price">￥<span>30</span>.00</p>
								</li>	
								<li class="Store-home-order-li">
									<div class="Store-home-order-img">
										<img src="img/store-home-order1.jpg" alt="" />
									</div>
									<p class="Store-home-order-text">京博黄 草莓花芽分化套餐</p>
									<p class="Store-home-order-price">￥<span>25</span>.00</p>
								</li>
								<li class="Store-home-order-li">
									<div class="Store-home-order-img">
										<img src="img/store-home-order2.jpg" alt="" />
									</div>
									<p class="Store-home-order-text">京博黄 草莓花芽分化套餐 防治套餐</p>
									<p class="Store-home-order-price">￥<span>30</span>.00</p>
								</li>								
							</ul>
							<div class="Store-home-order-more">							
								<a href="">更多<i></i></a>								
							</div>												
						</div>						
					</div>
					<div id="Store-all" class="mui-slider-item mui-control-content mui-active">
						<div class="Store-all-nav">
							<a class="Store-all-recommend" href="#">推荐</a>
							<a class="Store-all-sales" href="#">销量</a>
							<a class="Store-all-news" href="#">新品</a>
							<a class="Store-all-price" href="#">价格</a>
							<a class="Store-all-total" href="#"><img src="img/Store-total.png"/></a>
						</div>
						<ul class="Store-home-order-ul Store-all-ul" id="store-all_ul">
							<li class="Store-home-order-li" id="store_all_li" style="display:none;">
								<div class="Store-home-order-img">
									<img src="img/store-home-order1.jpg" alt="" />
								</div>
								<div class="Store-all-text">
									<p class="Store-home-order-text"><!-- <span class="hot-sell">热销</span> -->京博黄 草莓花芽分化套餐京博黄 草莓花芽分化套餐京博黄 草莓花芽分化套餐</p>
									<p class="Store-home-order-price">￥<span>25</span>.00</p>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>		
		</section>
		<nav class="mui-bar mui-bar-tab Store-footer">
			<a class="mui-tab-item" href="main">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item mui-active" href="merchants">
				<span class="mui-icon mui-icon-chatboxes"></span>
				<span class="mui-tab-label">商品信息</span>
			</a>
			<a class="mui-tab-item" href="businessindetail">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">商家介绍</span>
			</a>
		</nav>
		<script src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script>
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
			
			var merchantsId = "${merchantsId}";
		</script>
		<script src="${ctxContents}js/medicial/merchantsproduct.js"></script>
		
	</body>

</html>