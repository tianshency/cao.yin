<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>首页</title>
		<%-- <link href="${ctxContents}css/owl.carousel.css" rel="stylesheet"> --%>
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<!--App自定义的css-->
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/index.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/icons-extra.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/alertTip/sweet-alert.css"/>
		<style type="text/css">
			.mui-slider {
			    overflow: visible;
			    height: 140px;
			}
			.mui-content {
			    overflow-x: hidden;
			}
		</style>
	</head>

	<body>
		<div class="mui-content">
			<div id="slider" class="mui-slider">
				<div class="mui-slider-group mui-slider-loop" id="parentTemplate">
				</div>
				<!-- <div class="mui-slider-indicator mui-text-right">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
				</div> -->
			</div>
		</div>
		<!-- <div class="main-wai" style="heigth:150px;" ontouchstart="begin(event)" ontouchmove="mov(event)" ontouchend="ed(event)">
       		<div class="main-nei" id="main-nei" style="left:0;">
           		<ul id="parentTemplate">
               		<li id="templateBanner">
               			<a href="#">
                   			<img src="img/home01.jpg" alt="" id="carouselImg"/>
               			</a>
               		</li>
           		</ul>
       		</div>
    	</div> -->
    	<div class="top w main-photo">
    		<div class="m_nav" id="cropTemplateDiv">
			</div>
    	</div>
    	<div class="speedLoan" id="intoLoan">
    		<img src="${ctxContents}img/loanIndex.jpg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/>
    	</div>
		<div class="mobile">
			<div class="m_mall w">
				<div class="mall_title_merch"><span>合作商家</span><em><a href="${ctx }merchantsController/toMerchantsList.do">更多</a></em></div>
				<div class="mall_list" id="merchTemplateDiv">
				</div>
			</div>
			<div class="m_mall w mall_title_content">
				<div class="mall_title"><span>热门头条</span></div>
				<div class="mall_listr">
					<div id="demo" style="overflow-y:hidden;height:50;width:100%;background:ffffff;color:#000000">
					<div id="demo1">
						
					</div>
					<div id="demo2"></div>
					</div>
				</div> 
			</div>
			<div class="m_baoliao w main-new">
				<div class="baoliao_title"><span>最新推荐</span><em><a href="#"><img src="${ctxContents}img/refresh.png"></a></em></div>
				<div class="baoliao_list" id="commodityParent">
					<div class="baoliao_content" id="commodityTemplate" style="display:none;">
						<a href="baoliao_view.html" id="commodityHref">
							<div class="bl_img"><img src="http://baoliao.178hui.com/upload/2015/0710/12332059693.jpg" id="commodityImg" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'"/></div>
							<div class="bl_right">
								<div class="bl_title mui-ellipsis" id="commodityName">韩国现代（HYUNDAI) BD-YS2003 多功能养生壶 煎药壶2.0L</div>
								<div class="bl_note">运费：<span id="fare">0.00元</span></div>
								<div class="bl_tag">
									<div class="bl_price" >￥99.00</div>
									<div class="bl_oprice">￥138.00</div>
									<div class="bl_time">07-10 12:33</div>
									<div class="bl_mall">京东商城</div>
								</div>
							</div>
						</a>
					</div>
				</div>
				<div class="bl_more">
					<a href="#">加载更多</a>
				</div>
			</div>
		</div>
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item mui-active" href="main.html">
				<span class="mui-icon mui-icon-home"></span>
				<span class="mui-tab-label">商城首页</span>
			</a>
			<a class="mui-tab-item" href="releasetype">
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
		<script src="${ctxContents}js/jquery-1.8.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<%-- <script src="${ctxContents}js/owl.carousel.min.js"></script> --%>
		<script src="${ctxContents}layer/layer.js"></script>
		<script src="${ctxContents}js/medicial/main.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript" src="${ctxContents}js/medicial/index.js"></script>
		<script type="text/javascript" src="${ctxContents}js/alerttip/sweet-alert.js"></script>
		<script type="text/javascript" src="${ctxContents}js/alerttip/showAlert.js"></script>
		<script>
			mui('.mui-bar-tab').on('tap', 'a', function(e) {
				var targetTab = this.getAttribute('href');
				if(targetTab == "main.html"){
					return;
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
			
		/* 	var slider = mui("#slider");
			 mui.ready(function(e){
					slider.slider({
						interval: 4000
					});
			});

			var Gallerygg = mui("#Gallery-gg");
			 mui.ready(function(e){
					Gallerygg.slider({
						interval: 3000
					});
			});
			
			var Galleryxs = mui("#Gallery-xs");	
			 mui.ready(function(e){
					Galleryxs.slider({
						interval: 4000,
						delay:2000
					});
			}); */
			
		</script>
	</body>

</html>