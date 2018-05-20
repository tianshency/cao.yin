<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>订单明细</title>
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/icons-extra.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />

		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/orderinfo.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="${ctxContents}css/media.css" />
	</head>

	<body>
		<div class="orderDetail-time">
			<p class="orderDetail-ddh">订单号：<span>1234567890</span></p>
			<p class="orderDetail-xdsj">下单时间：<span>2016-03-09 09:28</span></p>
			<p class="orderDetail-mjfk">等待买家付款</p>
		</div>
		<div class="orderDetail-tel">
			<p>
				<span id="userName">王小平</span>
				<span id="phone">18701364897</span>
			</p>
			<p>
				<span class="mui-icon mui-icon-location"></span>收货地址：<b id="address">黑龙江省哈尔滨市XXX区XXX路56号1309</b></p>
		</div>

		<div class="oi_order">
			<div class="order_list">
				<ul>
					<li class="orderDetail-li" id="orderDetail-li" style="display:none;">
						<div class="order_title">
							<div>
								<img src="img/cpmc_inof_icon2.png" />
								<span>公司名称</span>
							</div>
							<em></em>
						</div>
						<div class="order_main">
							<div class="bl_img">
								<img src="img/detail04.jpg" />
							</div>
							<div class="bl_right">
								<div class="bl_title">韩国现代（HYUNDAI) BD-YS2003 多功能养生壶 煎药壶2.0L</div>
								<div class="bl_tag">
									<div class="bl_price">￥99.00 <b>X10</b></div>
									<div class="bl_number">
										<p>合计：￥990.00元 (含运费￥0.00)</p>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="orderDetail-payment">
			<h2>支付方式</h2>
			<div class="orderDetail-btn">
				<button type="button" class="mui-btn mui-btn-outlined">现 金</button>
				<button type="button" class="mui-btn mui-btn-outlined">微 信</button>
				<button type="button" class="mui-btn mui-btn-outlined">支付宝</button>
			</div>
		</div>
		<div class="orderDetail-price">
			<p class="orderDetail-price-p1">
				<span>商品金额</span>
				<span id="merchartsMoney">9900.00</span>
			</p>
			<p class="orderDetail-price-p2">
				<span>运费(总重：2500kg)</span>
				<span id="fareMoney">0.00</span>
			</p>
		</div>
		<!--结算信息 start-->
		<div class="settlement">
			<div class="settlement_left">
				<font class="zongji">应付款：</font>
				<font class="money">￥9900.00</font>
			</div>
			<div class="settlement_right">
				<button type="button" class="mui-btn" id="cancleBtn"> 取消订单</button>
				<button type="button" class="mui-btn mui-btn-outlined" id="againBtn"> 再次订购 </button>
			</div>
		</div>
		<script type="text/javascript" src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		
		<script type="text/javascript">
			var orderId = "${orderId}";
			
		</script>
		<script type="text/javascript" src="${ctxContents}js/order/orderdetail.js"></script>
	</body>

</html>