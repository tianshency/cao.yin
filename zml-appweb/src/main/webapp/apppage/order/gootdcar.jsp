<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>购物车</title>
		<link href="${ctxContents}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />

		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/index.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/goodscar.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents }js/bootstrap.min.js"></script>
		
	</head>

	<body>
		<!-- <div class="mui-content header_car">
			<div>
				<div id="segmentedControl" class="mui-segmented-control">
					<a class="mui-control-item mui-active" href="#item2">购物车</a>
					<a class="mui-control-item mui-active" href="#">预订购物车</a>
				</div>
			</div>
		</div> -->
				
		<div class="gc_goodscar">
			<div class="goodscar_list">
				<ul id="goodscarParent">
					<li id="goodscartemplate" style="display:none;">
						<div class="goodscar_title">
							<div><input type="checkbox" id="mercId"/></div>
							<div><img src="" /><span id="mercName">公司名称</span> </div>
							<em><a href="javascript:void(-1);" id="delByMercId">删除</a></em>
						</div>
						<div class="goodscar_main" id="goodsList">
							<div id="goodsListTemplate">
								<div class="bl_radio"><input type="checkbox" id="goodsId"/></div>
								<div class="bl_img">
									<img src="" id="goodsImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>
								</div>
								<div class="bl_right">
									<div class="bl_title" id="goodsTitle"></div>
									<div class="bl_tag">
										<div class="bl_price" id="realPrice"> </div>
										<div class="bl_number">
											<b id="reserveAmount"></b>
											<input class="add" type="button" value="+" />
											<input class="num" id="purchaseNum" type="text" value="1" />
											<input class="del" type="button" value="-"/>
										</div>
									</div>
								</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		<!--结算信息 start-->
		<div class="settlement">
			<div class="settlement_left">
				<input type="checkbox" id="selectAll">全选
			</div>
			<div class="settlement_middle">
				<font class="zongji">总计：</font>
				<font class="money">￥0</font><br />共0件，不包含运费
			</div>
			<div class="settlement_right">
				<input type="button" value="去支付"  id="submitOrder"/>
			</div>
		</div>
		
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModalDel" tabindex="-1" role="dialog"  aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<input type="hidden" id="cartId">
						请选择您的操作
					</div>
					<div class="modal-footer">
						<button type="button" class="mui-btn mui-btn-success" data-dismiss="modal"  id ="checkButton">取消</button>
						<button type="button" class="mui-btn mui-btn-danger"  id="delCartById">删除</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		
		<!--结算信息 end-->
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript" src="${ctxContents}js/order/goodscar.js"></script>
	</body>

</html>