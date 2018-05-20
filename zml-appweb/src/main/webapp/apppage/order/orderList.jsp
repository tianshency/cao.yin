<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>订单列表</title>
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/orderinfo.css" rel="stylesheet" type="text/css" />
		<link  href="${ctxContents}css/media.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<div class="oi_order w">
			<div class="order_list">
				<ul id="templateParent">
					<li class="order-finish" id="templateList">
						<a href="orderdetailinfo.html" id="templateA">
							<div class="order_title">
								<div>
									<img src="img/cpmc_inof_icon2.png" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>
									<span id="orderNo">订单编号</span>
								</div>
								<em id="orderStatus">已完成</em>
							</div>
							<div class="order_main">
								<div class="bl_img">
									<img src="img/detail04.jpg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>
								</div>
								<div class="bl_right">
									<div class="bl_title">韩国现代（HYUNDAI) BD-YS2003 多功能养生壶 煎药壶2.0L</div>
									<div class="bl_tag">
										<div class="bl_price">￥99.00 <b>共10件</b> </div>
										<div class="bl_number">
											<p>合计：￥20.00元 (含运费￥0.00)</p>
										</div>
									</div>
								</div>
							</div>
						</a>
						<div class="order_del">
							<button type="button" class="mui-btn mui-btn-danger mui-btn-outlined" id="delBtn">
		            			<span class="mui-icon mui-icon-trash"></span>
		            			删除订单
		        			</button>
		        			<a>再次购买</a>
						</div>
					</li>
					
				</ul>
			</div>
		</div>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript" src="${ctxContents}js/order/orderList.js"></script>
	</body>
</html>