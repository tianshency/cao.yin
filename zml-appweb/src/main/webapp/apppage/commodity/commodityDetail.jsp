<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="applicable-device" content="mobile" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<title>商品详情</title>
		<link href="${ctxContents}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/goodsdetail.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="${ctxContents}css/media.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/computer.css" />
		<script src="${ctxContents}js/common/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxContents }js/bootstrap.min.js"></script>
		<script src="${ctxContents}js/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxContents}js/mui.zoom.js"></script>
		<script src="${ctxContents}js/mui.previewimage.js"></script>
	</head>

	<body>
		<div class="mobile">
			<!--页面加载 开始-->
			<div id="preloader">
				<div id="status">
					<p class="center-text"><span>拼命加载中···</span></p>
				</div>
			</div>
			<!--页面加载 结束-->
			<!--header 开始-->
			<header>
				<div class="header">
					<h2>商品详情</h2>
					<div class="head_right" style="top:13px;" onclick="JavaScript :history.back(-1)">
						<span style="color:#FFFFFF; font-size:14px;">返回列表</span>
					</div>
				</div>
			</header> 
			<div class="view w detail-content mui-content">
				<ul class="mui-table-view">
					<li class="mui-table-view-cell">
						<div id="Gallery_Toggle" class="mui-switch">
							<div class="mui-switch-handle"></div>
						</div>
					</li>
				</ul>			
				<div id="Gallery"  class="mui-slider detail-img">
					<ul class="mui-slider-group mui-content-padded" id="parentUl">
					</ul>		
				</div>
				<div class="bl_view_title">
					韩国现代（HYUNDAI) 优质化肥韩国现代（HYUNDAI) 优质化肥韩国现代（HYUNDAI) 优质化肥韩国现代（HYUNDAI) 优质化肥韩国现代（HYUNDAI) 优质化肥韩国现代（HYUNDAI) 优质化肥韩国现代（HYUNDAI) 优质化肥 
				</div>
				<!-- <div class="bl_view_note">包邮</div> -->
				<div class="bl_view_tag">
					<div class="bl_view_price" id="price">￥99.00</div>
					<div class="bl_view_oprice" id="oldPrice">￥138.00</div>
					<div class="bl_view_mall">运费：5元</div>
				</div>
				<div class="bl_view_tag">
					<div class="bl_view_user" id="reserveAmount">库存 100件</div>
					<div class="bl_view_time" id="salesVolume">销量 10</div>
				</div>
				<div class="bl_view_tag">
					<div class="bl_view_user" id="mercinfo">化肥厂商</div>
				</div>
			</div>
			<div class="detail-btn">
				<div>				
					<div class="detail-tel">
						<i></i><span>联系卖家</span>
						<a class="detail-tel-a" href="tel:18410178560"></a>
					</div>
					<div class="detail-shop">
						<i></i><span>店铺</span>
						<a class="detail-shop-a" href="merchantsController/toMerchantsInfo.do?merchantsId=402881875997156a01599733d7c20007"></a>
					</div>
					<div class="detail-shop" id="collectionCommodity">
						<i></i><span>收藏</span>
						<a class="detail-shop-a" href="merchantsController/toMerchantsInfo.do?merchantsId=402881875997156a01599733d7c20007"></a>
					</div>
					<div class="detail-shopCart">
						<i></i><span>订购车</span><b></b>
						<div class="detail-shopCart-bg">
							<p>成功加入订购车</p>
							<button class="shopCart-btn" type="button" onclick="location.href='preOrder.html'">去结算</button>
						</div>
					</div>		
					
					<form class="detail-price-btn">
						<div class="detail-auction-btn">
							<input type="button" name="" id="addPreOrder" value="加入订购车" />
						</div>	
					</form>
				</div>
			</div>			
		</div>
		<!-- 弹出对话框 -->
		<!-- 模态框（Modal） -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
							&times;
						</button>
						<h4 class="modal-title" id="myModalLabel">
							试算器列表
						</h4>
					</div>
					<div class="modal-body">
					</div>
					<div class="modal-footer">
						<button type="button" class="mui-btn mui-btn-success"  id ="checkButton">确定预约</button>
						<button type="button" class="mui-btn mui-btn-danger" data-dismiss="modal">关闭</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		
		<!-- 弹出对话框 -->
	</body>
	<%-- <script type="text/javascript" src="${ctxContents }js/jquery-1.8.3.js"></script> --%>
	

	<script type="text/javascript">
		$(window).load(function() {
			$("#status").fadeOut();
			$("#preloader").delay(350).fadeOut("slow");
		})
	
		$(document).ready(function() {
			$(".shaixuan").click(function(event) {
				event.stopPropagation();
				$(".shaixuan_box").show();
				$(".shaixuan_box").animate({
					right: '100%'
				});
				$("html,body").css("overflow", "hidden");
			});
			$(document).click(function(event) {
				$(".shaixuan_box").animate({
					right: '-100%'
				});
				$(".shaixuan_box").hide(5);
				$("html,body").css("overflow", "");
			});
		});
			
		var commodityId = "${commodityId}";
	
	
		mui.previewImage();
		
		mui.init();
		mui.ready(function() {
			var slider = document.getElementById('Gallery');
			var group = slider.querySelector('.mui-slider-group');
			var items = mui('.mui-slider-item', group);
			//克隆第一个节点
			var first = items[0].cloneNode(true);
			first.classList.add('mui-slider-item-duplicate');
			//克隆最后一个节点
			var last = items[items.length - 1].cloneNode(true);
			last.classList.add('mui-slider-item-duplicate');
			//处理是否循环逻辑，若支持循环，需支持两点：
			//1、在.mui-slider-group节点上增加.mui-slider-loop类
			//2、重复增加2个循环节点，图片顺序变为：N、1、2...N、1
			var sliderApi = mui(slider).slider();

			function toggleLoop(loop) {
					if (loop) {
						group.classList.add('mui-slider-loop');
						group.insertBefore(last, group.firstChild);
						group.appendChild(first);
						sliderApi.refresh();
						sliderApi.gotoItem(0);
					} else {
						group.classList.remove('mui-slider-loop');
						group.removeChild(first);
						group.removeChild(last);
						sliderApi.refresh();
						sliderApi.gotoItem(0);
					}
				}
				//按下“循环”按钮的处理逻辑；
			document.getElementById('Gallery_Toggle').addEventListener('toggle', function(e) {
				var loop = e.detail.isActive;
				toggleLoop(loop);
			});
		});	
	</script>
	<script type="text/javascript" src="${ctxContents}js/medicial/commodityDetail.js"></script>
</html>