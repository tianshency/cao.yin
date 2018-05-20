<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>账号</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${ctxContents}css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctxContents}css/bootstrap-theme.min.css" />
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css"/>
		<link rel="stylesheet" href="${ctxContents}css/myaccountpage.css" />
	</head>

	<body>
		<div class="headerImg">
			
			<div class="img_div">
			<img src="img/header_img.png"/>
				<span>梁君波</span>
			</div>
			<div class="class_cover">
			</div>
		</div>
		<div class="account_total">
			<p><span>我的信用(元)</span><span>1000.00</span></p>	
			<p><span>累计借款(元)</span><span>1000.00</span></p>	
			<p><span>待还金额(元)</span><span>1000.00</span></p>	
		</div>
		<div class="mui-content">
		    <ul class="mui-table-view mui-table-view-chevron">
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right" href="${ctx }loanApplicationController/list.do">
						<img class="mui-media-object mui-pull-left" src="img/setting_img.png">
						<div class="mui-media-body">
							我的借款
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" src="img/about_dbh.png">
						<div class="mui-media-body">
							我要还款
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" src="img/about_dbh.png">
						<div class="mui-media-body">
							银行卡
						</div>
					</a>
				</li>
				<li class="mui-table-view-cell mui-media">
					<a class="mui-navigate-right">
						<img class="mui-media-object mui-pull-left" src="img/about_dbh.png">
						<div class="mui-media-body">
							个人信息修改
						</div>
					</a>
				</li>
			</ul>    
		</div>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="main.html">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">首页</span>
			</a>
			<a class="mui-tab-item" href="releasetype.html">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">账号</span>
			</a>
			<a class="mui-tab-item" href="information">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">更多...</span>
			</a>
		</nav>
	</body>
</html>