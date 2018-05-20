<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>发布信息</title>
		<link href="${ctxContents}css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<!--标准mui.css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		
		<link href="${ctxContents}fonts/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<!--自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/booking.css" />
		
		<link type="text/css" rel="stylesheet" href="${ctxContents}css/style.min.css">
		<link type="text/css" rel="stylesheet" href="${ctxContents}css/laydate.css">
		<link type="text/css" rel="stylesheet" href="${ctxContents}css/laydate(1).css" id="LayDateSkin"></head>
		<style type="text/css">
			#bookingNum{
				width:75%;
			}
			#unit{
				width:20%;
				padding-left: 4%;
			}
			#bookingNum +span{
				width:75%;
			}
		</style>
	</head>
	<body style="background: #fff;">
		<div class="release-List-header">
			<div>
				<div id="segmentedControl" class="mui-segmented-control">
					<a class="mui-control-item mui-active" href="item1">粮食</a>
					<a class="mui-control-item" href="item2">土地</a>
					<a class="mui-control-item" href="item3">综合</a>
				</div>
			</div>
		</div>
		<div class="mui-content release-List-content" id="item1">
			<ul id="OA_task_1" class="release-List">
				<li class="release-List-li" id="template1" style="display:none;">
					<div class="release-List-main">
						<div class="release-List-img">
							<img src="img/cpmc_bigImg.jpg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>					
						</div>
						<div class="release-List-text">
							<p class="release-text-top" id="title">粮食</p>
							<p class="release-text-middle">
								<span class="release-state" id="userName" >未上架</span>&nbsp;
								发布于<span class="release-time" id="createDate">09.18</span>
							</p>
							<p class="release-text-btm">浏览<span class="release-number" id="viewCount">216</span>次<!-- <b class="release-up" id="status">上架中</b> --></p>
						</div>	
						<div class="release-List-btn">
						<a href="" class="release-List-new">收藏</a>
						<a href="#more-choose" class="release-List-more">预订</a>
						<!--<a class="release-List-new">上架</a>-->
					</div>					
					</div>
				</li>
			</ul>
		</div>
		<div class="mui-content release-List-content" id="item2">
			<ul id="OA_task_2" class="release-List">
				<li class="release-List-li" id="template2" style="display:none;">
					<div class="release-List-main">
						<div class="release-List-img">
							<img src="img/cpmc_bigImg.jpg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>					
						</div>
						<div class="release-List-text">
							<p class="release-text-top" id="title">土地</p>
							<p class="release-text-middle">
								<span class="release-state" id="userName" >未上架</span>&nbsp;
								发布于<span class="release-time" id="createDate">09.18</span>
							</p>
							<p class="release-text-btm">浏览<span class="release-number" id="viewCount">216</span>次<!-- <b class="release-up" id="status">上架中</b> --></p>
						</div>		
						<div class="release-List-btn">
							<a href="#more-choose" class="release-List-new">收藏</a><a href="#more-choose" class="release-List-more">预订</a>
						</div>				
					</div>
				</li>
			</ul>
		</div>
		<div class="mui-content release-List-content" id="item3">
			<ul id="OA_task_3" class="release-List">
				<li class="release-List-li" id="template3" style="display:none;">
					<div class="release-List-main">
						<div class="release-List-img">
							<img src="img/cpmc_bigImg.jpg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>					
						</div>
						<div class="release-List-text">
							<p class="release-text-top" id="title">其它</p>
							<p class="release-text-middle">
								<span class="release-state" id="userName" >未上架</span>&nbsp;
								发布于<span class="release-time" id="createDate">09.18</span>
							</p>
							<p class="release-text-btm">浏览<span class="release-number" id="viewCount">216</span>次<!-- <b class="release-up" id="status">上架中</b> --></p>
						</div>	
						<div class="release-List-btn">
							<input type="hidden"  id = "releaseId"/>
							<a href="#more-choose" class="release-List-new">收藏</a>
							<a href="javascript:void(-1)" class="release-List-more">预订</a>
						</div>					
					</div>
				</li>
			</ul>
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
							在线预约
						</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal m-t">
							<div class="mui-content">
								<div class="mui-input-row">
									<input id='bookingNum' type="text" class="mui-input-clear contact" placeholder="预定数量" />
									<select id="unit" name="unit" placeholder="单位" >
										<option selected>单位</option>
						  				<option value="吨">吨</option>
						  				<option value="公斤">公斤</option>
						  				<option value="斤">斤</option>
									</select>
								</div>
								<div class="mui-input-row">
									<!-- <input id='planDealDate' type="text" class="mui-input-clear contact" placeholder="计划交易日" /> -->
									<!--  <div class="form-group"> -->
	                                      <input id="planDealDate" type="text" class="mui-input-clear laydate-icon form-control layer-date"  placeholder="计划交易日" >
	                               <!--   </div> -->
								</div>
								<div class="mui-input-row">
									<input id='bookingtel' type="text" class="mui-input-clear contact" placeholder="联系电话" />
								</div>
								<div class="mui-input-row">
									<textarea id='remarks' class="mui-input-clear question" placeholder="请填写你的预订描术信息"></textarea>
								</div>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="mui-btn mui-btn-success"  id ="checkButton">确定预约</button>
						<button type="button" class="mui-btn mui-btn-danger" data-dismiss="modal">关闭</button>
					</div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="main.html">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item" href="releasetype">
				<span class="mui-icon mui-icon-compose"></span>
				<span class="mui-tab-label">发布</span>
			</a>
			<a class="mui-tab-item mui-active" href="information">
				<span class="mui-icon mui-icon-eye"></span>
				<span class="mui-tab-label">资讯</span>
			</a>
			<a class="mui-tab-item" href="toPersonal">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">我的</span>
			</a>
		</nav>
		<script src="${ctxContents}js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents }/js/bootstrap.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script src="${ctxContents}js/laydate.js"></script>
		<script>
			laydate({elem: "#planDealDate", event: "focus"});
			mui.init();
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				var targetTab = this.getAttribute('href');
				if(targetTab == "main.html"){
					location.href=activePath;
				}
				else if(targetTab == "releasetype"){
					location.href=activePath+"releaseInfoController/toReleaseTypePage.do";
				}
				else if(targetTab == "information"){
					location.href=activePath+"releaseInfoController/toReleaseZiXun.do";
				}
				else{
					location.href = activePath+"personalController/toPersonalPage.do";
				}
			});
			
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
		<script src="${ctxContents}js/release/releaseshowlist.js"></script>
		<script src="${ctxContents}js/release/booking.js"></script>
	</body>
</html>

