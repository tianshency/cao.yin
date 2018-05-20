<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>支付页面</title>
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link href="${ctxContents}css/mui.picker.css" rel="stylesheet" />
		<link href="${ctxContents}css/mui.poppicker.css" rel="stylesheet" />
		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/payinfo.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/orderinfo.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
	</head>
	<body>
		<section id="payInfo">
		<!--  个人地址信息        -->
		<div class="oi_userinfo">
			<p>
				<span id="consignee">王小平</span>
				<span id="tel">18701364897</span>
			</p>
			<p>
				<span class="oi_sp1" id="address">收货地址：黑龙江省哈尔滨市XXX区XXX路56号1309dfdfdfd</span>
				<span class="mui-icon mui-icon-location"></span>
				<span class="mui-icon mui-icon-arrowright oi_sp2" id="arrowAddress"></span>
			</p>
		</div>
		<!--  商品信息 -->
		<div class="oi_order">
			<div class="order_list">
				<ul>
					<li class="orderDetail-li">
						<div class="order_main" style="display:none;" id="template">
							<div class="bl_img">
								<img src="img/detail04.jpg" />
							</div>
							<div class="bl_right">
								<div class="bl_title">韩国现代（HYUNDAI) BD-YS2003 多功能养生壶 煎药壶2.0L</div>
								<div class="bl_tag">
									<div class="bl_price"><span id="price">99.00</span><b id="amount">X10</b></div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<div class="pay_class">
			<p class="title">支付方式</p>
			<p class="tools">
				<button type="button" class="mui-btn mui-btn-danger mui-btn-outlined">
					现 金
				</button>
				<button type="button" class="mui-btn mui-btn-royal mui-btn-outlined">
					微 信
				</button>
				<button type="button" class="mui-btn mui-btn-warning mui-btn-outlined">
					支付宝
				</button>
			</p>
		</div>
		<div class="user_remark">
			<textarea rows="3" cols="50" id="userRemarks"></textarea>
		</div>
		<div class="fee_class">
			<p><span>商品金额</span><span id="totalMoney">9900.00</span></p>
			<p><span>运费(总重：2500kg)</span><span id="fare">0.00</span></p>
		</div>
		<!--结算信息 start-->
		<div class="settlement">
			<div class="settlement_left">
				<font class="zongji">应付款：</font>
				<font class="money" id="money">9900.00</font>
			</div>
			<div class="settlement_right">
				<input type="button" id="createOrder" value="提交订单" />
			</div>
		</div>
		<!--结算信息 end-->
		</section>
		<section  style="display:none;" id="addressInfo">
		<section class="address-list">
			<form class="mui-input-group" id="parentAddress">
				<div class="address-list-content" id="templateAddress" style="display:none;">
					<div class="address-list-top">
						<div class="address-list-name">
							<span id="consignee">小蜜蜂</span><b id="phone">18310902001</b>
						</div>
						<p class="address-list-dizhi" id="address">北京市海淀区西四环五路居北京市海淀区西四环五路居</p>
					</div>
					<div class="address-list-bottom">
						<div class="address-list-checked mui-input-row mui-radio mui-left">
							<label>默认地址</label>
							<input name="radio" value="1" type="radio" checked id="isDefault"/>
						</div>
					</div>
				</div>
			</form>
		</section>
		<footer class="address-list-footer">
			<a href="${ctx }myUserController/toAddUserAddress.do">+添加新地址</a>
		</footer>
		</section>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript">
			var ids = "${ids}";
		</script>
		<script type="text/javascript" src="${ctxContents }js/order/payinfo.js"></script>
	</body>
</html>
