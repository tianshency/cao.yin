<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>我的预订列表</title>
		<!--标准mui.css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<!--自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/bookingList.css" />
	</head>
	<body>
		<div class="mui-content" id="item1">
			<ul id="OA_task_1" class="mui-table-view">
				<li class="mui-table-view-cell" id="template">
					<a href="releaseshowdetail.html">
						<div class="mui-slider-handle">
							<div class="release_title"><span class="release_header mui-ellipsis" id="title">发布的标题</span><span class="release_time" id="status">完成</span></div>
							<p class="release_body">
								<span class="release_person">类型:<label id="type">粮食</label></span>
								<span class="release_type">发布人：<label id="person">XXX</label></span>
							</p>
						</div>
					</a>
					<div class="btn_opt_div">
						<button class="mui-btn-danger" id="del">取消</button>
						<button class="mui-btn-green" id="comp">完成</button>
					</div>
				</li>
				
				<!-- <li class="mui-table-view-cell">
					<a href="releaseshowdetail.html">
						<div class="mui-slider-handle">
							<div class="release_title"><span class="release_header mui-ellipsis" id="title">发布的标题</span><span class="release_time" id="status">进行中</span></div>
							<p class="release_body">
								<span class="release_person">类型:<b id="type">土地</b></span>
								<span class="release_type">发布人：<b id="userName">XXX</b></span>
							</p>
						</div>
					</a>
					<div class="btn_opt_div">
						<button class="mui-btn-danger">取消</button>
						<button class="mui-btn-green">完成</button>
					</div>
				</li>
				<li class="mui-table-view-cell">
					<a href="releaseshowdetail.html">
						<div class="mui-slider-handle">
							<div class="release_title"><span class="release_header mui-ellipsis">发布的标题</span><span class="release_time">对方取消</span></div>
							<p class="release_body">
								<span class="release_person">类型:土地</span>
								<span class="release_type">发布人：XXX</span>
							</p>
						</div>
					</a>
					<div class="btn_opt_div">
						<button class="mui-btn-danger">取消</button>
						<button class="mui-btn-green">完成</button>
					</div>
				</li> -->
			</ul>
		</div>
		<script src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script src="${ctxContents}js/release/mybookinglist.js"></script>
	</body>
</html>
