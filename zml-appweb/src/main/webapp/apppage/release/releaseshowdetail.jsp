<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>XXX信息</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css"/>
		<!--自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/releaseshow.css"/>
		<style>
			img{
				width: 99%;
			}
		</style>
	</head>
	<body>
		<div class="container">
			<div class="main-titile">
				<div class="title"></div>
				<div class="body"> <span id="userName"></span> <span id="createTime"></span></div>
			</div>
			<div class="main-content" id="contentInfo">
			<img id="coverImg" src=""/>
			<div id="remark"></div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="#">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">预订</span>
			</a>
			<a class="mui-tab-item" href="#">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">联系</span>
			</a>
		</nav>
		
		<script src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript">
			var releaseId= "${id}";
			var flag= "${flag}";
		</script>
		<script type="text/javascript" src="${ctxContents}js/release/releaseshowdetail.js"></script>
	</body>
</html>
