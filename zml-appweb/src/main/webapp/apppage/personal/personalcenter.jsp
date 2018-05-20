<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/icons-extra.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css"/>
		<link rel="stylesheet" href="${ctxContents}css/main.css">

	</head>

	<body>
		<div class="headerImg">
			<img src="../static/img/cpmc_pj_photo.png"/>
		</div>
		<div class="mui-content" id="App-MyContent">
			<ul class="MyContent-top">
		    	<li class="">
		           <a href="${ctx }myLoanController/toMyLoan.do">
		              <span class="mui-icon-extra mui-icon-extra-gold">
		              	<span class="mui-badge">5</span>
		              </span>
		              <div class="mui-media-body">我的借款</div>
		           </a>
		        </li>
		        <li class="">
		        	<a href="${ctx }orderController/toOrderlist.do">
		           		<span class="mui-icon-extra mui-icon-extra-order">
		           			<span class="mui-badge">5</span>
		           		</span>
		                <div class="mui-media-body">我的订单</div>
		            </a>
		        </li>
		        <li class="">
		           <a href="${ctx }cartsController/toShoppingCartPage.do">
		              <span class="mui-icon-extra mui-icon-extra-cart">
		              	<span class="mui-badge">5</span>
		              </span>
		              <div class="mui-media-body">购物车</div>
		           </a>
		        </li>
		        <li class="">
		        	<a href="#">
		              <span class="mui-icon-extra mui-icon-extra-express">
		              	<span class="mui-badge">5</span>
		              </span>
		              <div class="mui-media-body">待收货</div>
		            </a>
		        </li>
		     </ul>
		     <ul class="MyContent-mid">
		        <li class="">
		            <a href="${ctx }myCollectionController/toCollectionPage.do">
		               <span class="mui-icon-extra mui-icon-extra-share"></span>
		               <div class="mui-media-body">我的收藏</div>
		            </a>
		        </li>
		        <li class="">
		           <a href="${ctx }releaseInfoController/tomyReleaseListPage.do">
		               <span class="mui-icon-extra mui-icon-extra-class"></span>
		               <div class="mui-media-body">我的发布</div>
		           </a>
		        </li>
		        <li class="">
		           <a href="${ctx }bookingController/toMyBookingList.do">
		              <span class="mui-icon-extra mui-icon-extra-calendar"></span>
		              <div class="mui-media-body">我的预订</div>
		           </a>
		        </li>
		        <li class="">
		           <a href="${ctx }myUserController/toUserAddressList.do">
		               <span class="mui-icon mui-icon-location"></span>
		               <div class="mui-media-body">收货地址</div>
		           </a>
		        </li>
		     </ul>
		     <div class="MyContent-btm">
		        <div class="">
		            <a href="netintroduction.html">
		               <!--   <span class="mui-icon mui-icon-info"></span>-->
		                <div class="mui-media-body">关于我们</div>
		                <span class="mui-icon mui-icon-arrowright"></span>
		            </a>
		        </div>
		        <div class="">
		           	<a href="${ctx }feedBackController/toFeedBackPage.do">
		                <!--  <span class="mui-icon mui-icon-compose"></span>-->
		                <div class="mui-media-body">意见反馈</div>
		                <span class="mui-icon mui-icon-arrowright"></span>
		           </a>
		        </div>
		        <div class="">
		           <a href="Location.html">
		                <!--  <span class="mui-icon mui-icon-navigate"></span>-->
		                <div class="mui-media-body">地理定位</div>
		                <span class="mui-icon mui-icon-arrowright"></span>
		           </a>
		         </div>
		      </div>
		       <!-- <ul class="mui-table-view mui-grid-view mui-grid-9">
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }myLoanController/toMyLoan.do">
		                    <span class="mui-icon-extra mui-icon-extra-order" style="color: #007AFF"><span class="mui-badge">5</span></span>
		                    <div class="mui-media-body">我的借款</div>
		            	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }orderController/toOrderlist.do">
		                    <span class="mui-icon-extra mui-icon-extra-order" style="color: #007AFF"><span class="mui-badge">5</span></span>
		                    <div class="mui-media-body">我的订单</div>
		            	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }cartsController/toShoppingCartPage.do">
		                    <span class="mui-icon-extra mui-icon-extra-cart" style="color: #4cd964"><span class="mui-badge">5</span></span>
		                    <div class="mui-media-body">购物车</div>
		            	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3"><a href="#">
		                    <span class="mui-icon-extra mui-icon-extra-express" style="color: #f0ad4e"><span class="mui-badge">5</span></span>
		                    <div class="mui-media-body">待收货</div></a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		             	<a href="${ctx }myCollectionController/toCollectionPage.do">
		                    <span class="mui-icon-extra mui-icon-extra-share" style="color: #f13db8"></span>
		                    <div class="mui-media-body">我的收藏</div>
		             	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }releaseInfoController/tomyReleaseListPage.do">
		                    <span class="mui-icon-extra mui-icon-extra-topic" style="color: #a42cec"></span>
		                    <div class="mui-media-body">我的发布</div>
		            	</a>
		            </li>
		             <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }bookingController/toMyBookingList.do">
		                    <span class="mui-icon-extra mui-icon-extra-topic" style="color: #a42cec"></span>
		                    <div class="mui-media-body">我的预订</div>
		            	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }myUserController/toUserAddressList.do">
		                    <span class="mui-icon mui-icon-location" style="color: #dd524d"></span>
		                    <div class="mui-media-body">收货地址</div>
		            	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		             	<a href="netintroduction.html">
		                    <span class="mui-icon mui-icon-info" style="color: #f13db8"></span>
		                    <div class="mui-media-body">关于我们</div>
		             	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="${ctx }feedBackController/toFeedBackPage.do">
		                    <span class="mui-icon mui-icon-compose" style="color: #a42cec"></span>
		                    <div class="mui-media-body">意见反馈</div>
		            	</a>
		            </li>
		            <li class="mui-table-view-cell mui-media mui-col-xs-4 mui-col-sm-3">
		            	<a href="Location.html">
		                    <span class="mui-icon mui-icon-compose" style="color: #a42cec"></span>
		                    <div class="mui-media-body">地理定位</div>
		            	</a>
		            </li>
		        </ul> 
		         -->
		</div>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="main">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item" href="releasetype">
				<span class="mui-icon mui-icon-compose"></span>
				<span class="mui-tab-label">发布</span>
			</a>
			<a class="mui-tab-item" href="information">
				<span class="mui-icon mui-icon-eye"></span>
				<span class="mui-tab-label">资讯</span>
			</a>
			<a class="mui-tab-item mui-active" href="toPersonal">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">我的</span>
			</a>
		</nav>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script>
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				var targetTab = this.getAttribute('href');
				if(targetTab == "main"){
					location.href=activePath;
				}
				else if(targetTab == "releasetype"){
					location.href=activePath+"releaseInfoController/toReleaseTypePage.do";
				}
				else if(targetTab == "information"){
					location.href=activePath+"releaseInfoController/toReleaseZiXun.do";
				}
				else{
					location.href = activePath+"personalController/toPersonalPage.do";
				}
			});
		</script>
	</body>
</html>