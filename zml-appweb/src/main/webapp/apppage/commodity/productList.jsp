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
					<li class="list-bar-li SalesVolume">
						<a>销量</a>
					</li>
					<li class="list-bar-li ControlPrice">
						<a>价格</a>
						<s></s>
					</li>
					<li class="list-bar-li ControlType">
						<a>品类</a>
						<s></s>	
					</li>
				</ul>
			</div>
		<!-- <div id="item1" class="pro-list-div mui-popover">
				<ul class="mui-table-view mui-table-view-radio">
						<li class="mui-table-view-cell" onclick='shaixuan(this,"salesNum",1)'>
							<p class="mui-navigate-right">升序</p>
						</li>
						<li class="mui-table-view-cell" onclick='shaixuan(this,"salesNum",0)'>
							<p class="mui-navigate-right">降序</p>
						</li>
				</ul>
			</div>
			<div id="item2" class="pro-list-div mui-popover">
				<ul class="mui-table-view mui-table-view-radio mui-table-price-ul">
					<li class="mui-table-view-cell mui-table-price" onclick='shaixuan(this,"salesPrice",0)'>
						<p class="mui-navigate-right">低价</p>
					</li>
					<li class="mui-table-view-cell mui-table-price" onclick='shaixuan(this,"salesPrice",1)'>
						<p class="mui-navigate-right">高价</p>
					</li>
				</ul>
			</div> -->
			<div id="Type-main">
				<ul class="mui-table-view Type-main-ul">
					<li class="mui-table-view-cell"  onclick='shaixuan(this,"commodityType",0)'>
						<p>黄豆</p><i></i>
					</li>
					<li class="mui-table-view-cell"  onclick='shaixuan(this,"commodityType",0)'>
						<p>玉米</p><i></i>
					</li>
					<li class="mui-table-view-cell " onclick='shaixuan(this,"commodityType",0)'>
						<p>大豆</p><i></i>
					</li>
					<li class="mui-table-view-cell" onclick='shaixuan(this,"commodityType",0)'>
						<p>葵瓜子</p><i></i>
					</li>
					<li class="mui-table-view-cell" onclick='shaixuan(this,"commodityType",0)'>
						<p>化肥</p><i></i>
					</li>
					<li class="mui-table-view-cell" onclick='shaixuan(this,"commodityType",0)'>
						<p>农药</p><i></i>
					</li>
					<li class="mui-table-view-cell" onclick='shaixuan(this,"commodityType",0)'>
						<p>猪肉</p><i></i>
					</li>
				</ul>
				<p class="Type-btn">
					<input type="reset" class="TypeInput-rest" value="重置" />
					<input type="button" class="TypeInput-sbm" value="确定">
				</p>
				
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
							<div class="bl_img"><img src="http://baoliao.178hui.com/upload/2015/0710/12332059693.jpg" id="commodityImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/></div>
							<div class="bl_right">
								<div class="bl_title mui-ellipsis" id="commodityName"></div>
								<div class="bl_note" id="fare"></div>
								<div class="bl_tag">
									<div class="bl_price">￥99.00</div>
									<div class="bl_oprice">￥138.00</div>
									<!-- <div class="bl_time">07-10 12:33</div>  -->
									<div class="bl_mall">京东商城</div>
								</div>
							</div>
							</a>
						</div>
					</li>
				</ul>
			</div>
		</div>
		
		<script type="text/javascript" src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${ctxContents}js/mui.min.js"></script>
		
		<script type="text/javascript">
			$(function() {
				$("#preOrder_btn").click(function() {
					location.href="pregoodscar.html";
				});
				$("#order_btn").click(function() {
					location.href="goodscar.html";
				});
			});
			var typeId = "${typeId}";
		</script>
		<script type="text/javascript" src="${ctxContents}js/medicial/productList.js"></script>
		<script type="text/javascript">
	
	//价格
	/*$(".ControlPrice>a").toggle(
    function(){
		alert("升序！！！");
    	$(".SalesVolume>a").css({"color":"#333"});
		$(".ControlType>a").css({"color":"#333"});
		$(".ControlPrice>a").css({"color":"#fb3038"});		
		$(".ControlType").removeClass("Type-down");
    	$(".ControlPrice").addClass("price-up");
    	$("#Type-main").hide();
    },
    function(){
    	alert("降序！！！");
    	$(".SalesVolume>a").css({"color":"#333"});
		$(".ControlType>a").css({"color":"#333"});
		$(".ControlPrice>a").css({"color":"#fb3038"});		
		$(".ControlType").removeClass("Type-down");
    	$(".ControlPrice").addClass("price-down");
    	$("#Type-main").hide();
    }
  );
	*/

</script>
	</body>

</html>