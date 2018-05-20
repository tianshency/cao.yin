<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>商品信息列表</title>
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/productlist.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<style type="text/css">
			body{
				background: #f6f7f8;
			}
		</style>
	</head>

	<body>
		<div class="search-info pro-list-header">
			<div class="mui-input-row mui-search">
				<input type="search" class="mui-input-clear" placeholder="">
			</div>
			<a onClick="javascript :history.back(-1);">取消</a>
		</div>
		<div class="pro-list-bar">
			<div class="pro-list-bar-main">
				<ul id="segmentedControl" class="mui-segmented-control">
					<li class="list-bar-li">
						<i></i>
						<a class="mui-control-item" href="#item1">销量</a>
					</li>
					<li class="list-bar-li">
						<i></i>
						<a class="mui-control-item" href="#item2">价格</a>
					</li>
					<li class="list-bar-li">
						<i></i>
						<a class="mui-control-item" href="#item3">品类</a>
					</li>
				</ul>
			</div>
			<div id="item1" class="pro-list-div mui-popover">
				<!--<ul class="mui-table-view mui-table-view-radio">
						<li class="mui-table-view-cell">
							<p class="mui-navigate-right">1</p>
						</li>
					</ul>-->
			</div>
			<div id="item2" class="pro-list-div mui-popover">
				<ul class="mui-table-view mui-table-view-radio mui-table-price-ul">
					<li class="mui-table-view-cell mui-table-price">
						<p class="mui-navigate-right">低价</p>
					</li>
					<li class="mui-table-view-cell mui-table-price">
						<p class="mui-navigate-right">高价</p>
					</li>
				</ul>
			</div>
			<div id="item3" class="pro-list-div mui-popover">
				<ul class="mui-table-view mui-table-view-radio">
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">黄豆</p>
					</li>
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">玉米</p>
					</li>
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">大豆</p>
					</li>
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">葵瓜子</p>
					</li>
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">化肥</p>
					</li>
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">农药</p>
					</li>
					<li class="mui-table-view-cell">
						<p class="mui-navigate-right">猪肉</p>
					</li>
				</ul>
			</div>
		</div>
		<!--
        	作者：1063314647@qq.com
        	时间：2016-12-26
        	描述：筛选条件
        -->
		<div class="search-con">

		</div>
		<div class="m_baoliao main">
			<div class="baoliao_list">
				<ul id="commodityParent">
					<li id="commodityTemplate" style="display:none;">
						<div class="baoliao_content">
							<a href="bussinessdetail.html" id="commodityHref">
							<div class="bl_img"><img src="http://baoliao.178hui.com/upload/2015/0710/12332059693.jpg" id="commodityImg"/></div>
							<div class="bl_right">
								<div class="bl_title mui-ellipsis" id="commodityName"></div>
								<div class="bl_note" id="fare"></div>
								<div class="bl_tag">
									<div class="bl_price">￥99.00</div>
									<div class="bl_oprice">￥138.00</div>
								</div>
							</div>
							</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="main.html">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item mui-active" href="#">
				<span class="mui-icon mui-icon-chatboxes"></span>
				<span class="mui-tab-label">商品信息</span>
			</a>
			<a class="mui-tab-item" href="businessintroduction.html">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">商家介绍</span>
			</a>
		</nav>
		<script type="text/javascript" src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script>
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				var targetTab = this.getAttribute('href');
				if(targetTab == "main.html") {
					location.href = "main.html";
				} else if(targetTab == "businessintroduction.html") {
					location.href = "businessintroduction.html";
				}
			});
			var merchantsId = "${merchantsId}";
		</script>
		<script type="text/javascript" src="${ctxContents}js/medicial/merchantsInfo.js"></script>
		
	</body>

</html>