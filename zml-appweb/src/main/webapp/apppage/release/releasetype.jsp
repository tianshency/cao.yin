<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>发布类型</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">

		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/release.css"/>
		
		<style type="text/css">
			body{
				background: #fff;
			}
		</style>
	</head>
	<body>
		<header class="release-type-header">
			<h1>选择发布类别</h1>
			<span id="myRelease">我的发布</span>
		</header>
		<section class="release-type">
			<ul class="release-type-ul">
				<li class="release-type-li">
					<a href="#releasePopover1">
						<img src="img/liangshi.png" alt="" onerror="javascript:this.src='${ctxContents}img/release/liangshi.jpg'"/>
						<p>粮食</p>
					</a>
				</li>
				<li class="release-type-li">
					<a href="#releasePopover2">
						<img src="img/tudi.png" alt="" onerror="javascript:this.src='${ctxContents}img/release/tudi.jpg'"/>
						<p>土地</p>						
					</a>
				</li>
				<li class="release-type-li">
					<a href="#releasePopover3">
						<img src="img/jiche.png" alt="" onerror="javascript:this.src='${ctxContents}img/release/jiche.jpg'"/>
						<p>机车</p>						
					</a>
				</li>
				<li class="release-type-li">
					<a href="#releasePopover5">
						<img src="img/qita.png" alt="" />
						<p>雇佣</p>						
					</a>
				</li>	
				<li class="release-type-li">
					<a href="#releasePopover4">
						<img src="img/qita.png" alt="" onerror="javascript:this.src='${ctxContents}img/release/qita.jpg'"/>
						<p>其他</p>						
					</a>
				</li>				
			</ul>
		</section>
		<article id="releasePopover1" class="mui-popover releasePopover">
			<div class="mui-popover-arrow1"></div>
			<div class="my-Popover-list">
				<div>
					<ul class="myPopover-ul">
						<li class="myPopover-li">
							<a id="chushou" href="javascript:void(-1);" onclick="toReleasePage('01','1')">出售</a>
						</li>
						<li class="myPopover-li">
							<a id="shougou" href="javascript:void(-1);" onclick="toReleasePage('01','2')">收购</a>
						</li>						
					</ul>
				</div>
			</div>
		</article>
		<article id="releasePopover2" class="mui-popover releasePopover">
			<div class="mui-popover-arrow1"></div>
			<div class="my-Popover-list">
				<div>
					<ul class="myPopover-ul">
						<li class="myPopover-li">
							<a id="zuling" href="javascript:void(-1);" onclick="toReleasePage('02','01')">出租土地</a>
						</li>
						<li class="myPopover-li">
							<a id="maimai" href="javascript:void(-1);" onclick="toReleasePage('02','02')">承包土地</a>
						</li>
						<li class="myPopover-li">
							<a id="zhuanrang" href="javascript:void(-1);" onclick="toReleasePage('02','03')">转让土地</a>
						</li>
					</ul>
				</div>
			</div>
		</article>
		<article id="releasePopover3" class="mui-popover releasePopover">
			<div class="mui-popover-arrow1"></div>
			<div class="my-Popover-list">
				<div>
					<ul class="myPopover-ul">
						<li class="myPopover-li">
							<a id="tuolaji" href="javascript:void(-1);" onclick="toReleasePage('03','01')">出售</a>
						</li>
						<li class="myPopover-li">
							<a id="shougeji" href="javascript:void(-1);" onclick="toReleasePage('03','02')">购买</a>
						</li>				
					</ul>
				</div>
			</div>
		</article>
		<article id="releasePopover5" class="mui-popover releasePopover">
			<div class="mui-popover-arrow1"></div>
			<div class="my-Popover-list">
				<div>
					<ul class="myPopover-ul">
						<li class="myPopover-li">
							<a id="zhaogong" href="javascript:void(-1);" onclick="toReleasePage('05','01')">招工 </a>
						</li>
						<li class="myPopover-li">
							<a id="zhaohuo" href="javascript:void(-1);" onclick="toReleasePage('05','02')">找活</a>
						</li>						
					</ul>
				</div>
			</div>
		</article>	
		<article id="releasePopover4" class="mui-popover releasePopover">
			<div class="mui-popover-arrow1"></div>
			<div class="my-Popover-list">
				<div>
					<ul class="myPopover-ul">
						<li class="myPopover-li">
							<a id="huafei" href="javascript:void(-1);" onclick="toReleasePage('04','01')">出售农用物资</a>
						</li>
						<li class="myPopover-li">
							<a id="nongyao" href="javascript:void(-1);" onclick="toReleasePage('04','02')">购买农用物资</a>
						</li>						
					</ul>
				</div>
			</div>
		</article>	
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item" href="main.html">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item mui-active" href="releasetype">
				<span class="mui-icon mui-icon-compose"></span>
				<span class="mui-tab-label">发布</span>
			</a>
			<a class="mui-tab-item" href="information">
				<span class="mui-icon mui-icon-eye"></span>
				<span class="mui-tab-label">资讯</span>
			</a>
			<a class="mui-tab-item" href="toPersonal">
				<span class="mui-icon mui-icon-contact"></span>
				<span class="mui-tab-label">我的</span>
			</a>
		</nav>
		
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		
		<script>
			mui.init({
				swipeBack:true //启用右滑关闭功能
			});
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
			
		</script>
		<script src="${ctxContents}js/release/releaseType.js"></script>
	</body>

</html>