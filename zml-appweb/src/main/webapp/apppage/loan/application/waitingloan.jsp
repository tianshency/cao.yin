<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>等待放款</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/Loan.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<style type="text/css">
			body{
				background: #fff;	
			}
		</style>
	</head>
	<body>
		<header class="sign-back">
			<a onClick="javascript :history.back(-1);">
				<img src="img/head_returnIcon.png"/>
			</a>
		</header>
		<section class="WaitingLoan-main">
			<div class="WaitingLoan-text">
				<h3>借款成功，等待放款！</h3>
				<p>您的借款在2个工作日内打到您的银行账户！</p>
				<p>请注意查收！</p>
				<a href="tel:400-898-0180">客服电话：400-898-0180</a>
			</div>
		</section>
		<footer class="WaitingLoan-footer">
			<a href="">返回首页</a>
		</footer>
	
	</body>
	<script src="${ctxContents}js/mui.min.js"></script>


</html>