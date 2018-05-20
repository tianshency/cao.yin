<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>申请拒绝</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/Loan.css"/>
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
		<section class="ApplyPass-main">
			<img src="img/feedback.png"/>
			<div>
				<h4>您的申请已被拒绝！</h4>
				<span>拒绝原因：您的综合情况暂不符合秒借要求，请您30天后再次提交申请</span>
			</div>
		</section>
	</body>
	<script src="${ctxContents}js/mui.min.js"></script>


</html>