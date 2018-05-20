<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>收货地址</title>
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
			<h1 class="mui-title">收货地址</h1>
		</header>-->
		<section class="address-list">
			<form class="mui-input-group" id="parent">
				<div class="address-list-content" id="template" style="display:none;">
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
						<div class="address-list-delet">
							<i id="edit"></i>
							<p></p>
							<s id='remove'></s>
						</div>
					</div>
				</div>
			</form>
		</section>
		<footer class="address-list-footer">
			<a href="${ctx }myUserController/toAddUserAddress.do">+添加新地址</a>
		</footer>
	</body>
	<script src="${ctxContents}js/common/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="${ctxContents}js/mui.min.js"></script>
	<script src="${ctxContents}js/address/addresslist.js"></script>
</html>