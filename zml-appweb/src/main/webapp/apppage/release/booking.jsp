<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!doctype html>
<html lang="en" class="feedback">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>问题反馈</title>
		<link href="${ctxContents}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/booking.css" />
	</head>

	<body>
		<div class="mui-content">
			<div class="mui-input-row">
				<input id='bookingNum' type="text" class="mui-input-clear contact" placeholder="预定数量" />
			</div>
			<div class="mui-input-row">
				<input id='planDealDate' type="text" class="mui-input-clear contact" placeholder="计划交易日" />
			</div>
			<div class="mui-input-row">
				<input id='bookingtel' type="text" class="mui-input-clear contact" placeholder="联系电话" />
			</div>
			<div class="mui-input-row">
				<textarea id='remarks' class="mui-input-clear question" placeholder="请填写你的预订描术信息"></textarea>
			</div>
		</div>
		<script src="${ctxContents}js/release/booking.js"></script>
	</body>

</html>