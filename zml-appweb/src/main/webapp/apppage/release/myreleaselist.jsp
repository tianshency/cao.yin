<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>我的发布信息列表</title>
		<!--标准mui.css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<!--自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
	</head>
	<body style="background: #fff;">
		<div class="release-List-header">
			<div>
				<div id="segmentedControl" class="mui-segmented-control">
					<a class="mui-control-item mui-active" href="item1">粮食</a>
					<a class="mui-control-item" href="item2">土地</a>
					<a class="mui-control-item" href="item3">其它</a>
				</div>
			</div>
		</div>
		<div class="mui-content release-List-content" id="item1">
			<ul id="OA_task_1" class="release-List">
				<li class="release-List-li" id="template1">
					<div class="release-List-main">
						<div class="release-List-img">
							<img src="img/cpmc_bigImg.jpg" id="coverImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>					
						</div>
						<div class="release-List-text">
							<p class="release-text-top" id="title">粮食</p>
							<p class="release-text-middle">
								<span class="release-state" id="userName" >未上架</span>&nbsp;
								发布于<span class="release-time" id="createDate">09.18</span>
							</p>
							<p class="release-text-btm">浏览<span class="release-number" id="viewCount">216</span>次<b class="release-up" id="status">上架中</b></p>
						</div>	
						<div class="release-List-btn">
							<a href="javascript:void(-1);" class="release-List-new" id="editBtn">编辑</a>
							<a href="javascript:void(-1);" class="release-List-more" id="delBtn">删除</a>
						</div>					
					</div>
				</li>
			</ul>
		</div>
		<div class="mui-content release-List-content" id="item2">
			<ul id="OA_task_2" class="release-List">
				<li class="release-List-li" id="template2">
					<div class="release-List-main">
						<div class="release-List-img">
							<img src="img/cpmc_bigImg.jpg" id="coverImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'" />					
						</div>
						<div class="release-List-text">
							<p class="release-text-top" id="title">土地</p>
							<p class="release-text-middle">
								<span class="release-state" id="userName" >未上架</span>&nbsp;
								发布于<span class="release-time" id="createDate">09.18</span>
							</p>
							<p class="release-text-btm">浏览<span class="release-number">216</span>次<b class="release-up" id="status">上架中</b></p>
						</div>
						<div class="release-List-btn">
							<a href="javascript:void(-1);" class="release-List-new" id="editBtn">编辑</a>
							<a href="javascript:void(-1);" class="release-List-more" id="delBtn">删除</a>
						</div>						
					</div>
				</li>
			</ul>
		</div>
		<div class="mui-content release-List-content" id="item3">
			<ul id="OA_task_3" class="release-List">
				<li class="release-List-li" id="template3">
					<div class="release-List-main">
						<div class="release-List-img">
							<img src="img/cpmc_bigImg.jpg" id="coverImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'" />					
						</div>
						<div class="release-List-text">
							<p class="release-text-top" id="title">其它</p>
							<p class="release-text-middle">
								<span class="release-state" id="userName" >未上架</span>&nbsp;
								发布于<span class="release-time" id="createDate">09.18</span>
							</p>
							<p class="release-text-btm">浏览<span class="release-number" id="viewCount">216</span>次<b class="release-up" id="status">上架中</b></p>
						</div>	
						<div class="release-List-btn">
							<a href="javascript:void(-1);" class="release-List-new" id="editBtn">编辑</a>
							<a href="javascript:void(-1);" class="release-List-more" id="delBtn">删除</a>
						</div>					
					</div>
				</li>
			</ul>
		</div>
		<script src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script>
			mui.init();
			(function($) {
				//第一个demo，拖拽后显示操作图标，点击操作图标删除元素；
				$('#OA_task_1').on('tap', '.mui-btn', function(event) {
					var elem = this;
					var li = elem.parentNode.parentNode;
					mui.confirm('确认删除该条记录？', 'Hello MUI', btnArray, function(e) {
						if (e.index == 0) {
							li.parentNode.removeChild(li);
						} else {
							setTimeout(function() {
								$.swipeoutClose(li);
							}, 0);
						}
					});
				});
				var btnArray = ['确认', '取消'];
				//第二个demo，向左拖拽后显示操作图标，释放后自动触发的业务逻辑
				$('#OA_task_2').on('slideleft', '.mui-table-view-cell', function(event) {
					var elem = this;
					mui.confirm('确认删除该条记录？', 'Hello MUI', btnArray, function(e) {
						if (e.index == 0) {
							elem.parentNode.removeChild(elem);
						} else {
							setTimeout(function() {
								$.swipeoutClose(elem);
							}, 0);
						}
					});
				});
				//第二个demo，向右拖拽后显示操作图标，释放后自动触发的业务逻辑
				$('#OA_task_2').on('slideright', '.mui-table-view-cell', function(event) {
					var elem = this;
					mui.confirm('确认删除该条记录？', 'Hello MUI', btnArray, function(e) {
						if (e.index == 0) {
							elem.parentNode.removeChild(elem);
						} else {
							setTimeout(function() {
								$.swipeoutClose(elem);
							}, 0);
						}
					});
				});
			})(mui);
	
			mui('.mui-segmented-control').on('tap', 'a', function(e) {
				var targetTab = this.getAttribute('href');
				if(targetTab == "item1"){
					$("#item1").show();
					$("#item2").hide();
					$("#item3").hide();
				}
				else if(targetTab == "item2"){
					$("#item1").hide();
					$("#item2").show();
					$("#item3").hide();
				}
				else if(targetTab == "item3"){
					$("#item1").hide();
					$("#item2").hide();
					$("#item3").show();
				}
			});
		</script>
		<script src="${ctxContents}js/release/myreleaseList.js"></script>
	</body>
</html>

