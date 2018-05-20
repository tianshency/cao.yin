<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>添加地址</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<style type="text/css">
			body{
				background: #f7f7f7;
			}
		</style>
	</head>
	<body>
		<!--<header id="detail-header" class="mui-bar mui-bar-nav">
			<div class="header-back">
				<a onClick="javascript :history.back(-1);">
					<img src="images/left-b.png"/>
				</a>
			</div>
			<h1 class="mui-title">添加地址</h1>
		</header>-->
		<section class="append-address">
			<form action="#" method="post">
			<ul class="append-address-ul">
				<li class="append-address-li append-name">
					<span>收件人</span>
					<input type="text" name="appendName" id="appendName" value="${address.consignee }" />
				</li>
				<li class="append-address-li append-tel">
					<span>联系电话</span>
					<input type="tel" name="appendTel" maxlength="11" id="tel" value="${address.phone }"/>
				</li>
				<li class="append-address-li append-dizhi">
					<span>地址</span>
					<textarea name="appendDizhi" id="appendDizhi" maxlength="40" ">${address.address}</textarea>
				</li>					
			</ul>
				<div class="append-address-div">
					<span>设置默认</span>
					<c:if test="${address.isDefault==1 }">
						<div class="mui-switch mui-switch-mini mui-active">
							<div class="mui-switch-handle"></div>
						</div>
					</c:if>
					<c:if test="${address.isDefault!=1 }">
						<div class="mui-switch mui-switch-mini">
							<div class="mui-switch-handle"></div>
						</div>
					</c:if>
				</div>
			<p class="address-save" type="button">保存</p>				
			</form>
		</section>
		
	
	</body>
	<script src="${ctxContents}js/common/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctxContents}js/mui.min.js"></script>
	<script src="${ctxContents}js/address/updateaddress.js"></script>
	
	<script type="text/javascript">
		mui.init({
			swipeBack:true //启用右滑关闭功能
		});
		var id = "${address.id}";
		var radio = "${address.isDefault}";// 1:设置为默认,  2：关闭设置默认
		mui('.append-address-div .mui-switch').each(function() {
			//	toggle 事件监听
			this.addEventListener('toggle', function(event) {
				//event.detail.isActive 可直接获取当前状态
				radio =event.detail.isActive?1:2; 
				this.parentNode.querySelector('span').innerText = (event.detail.isActive ? '设置默认' : '关闭设置默认');
			});
		});
	</script>

</html>