<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>我的收藏</title>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/app.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/collection.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/main.css" />
		<link rel="stylesheet" href="${ctxContents}css/media.css" />
	</head>
	<body>
		<div class="mui-content">
			<ul id="Collection" class="release-List">
				<li class="Collection-List-li" id="template"  style="display:none;">
					<div class="Collection-List-main">
						<p class="Collection-delet" id="delCollectById"><span>删除</span></p>
						<div class="Collection-main" id="linkedHref">
							<div class="Collection-List-img">
								<img src="" onerror="javascript:this.src='${ctxContents}img/noImg.jpg'" id="coverImg"/>					
							</div>
							<div class="Collection-List-text">				
								<p class="Collection-text-top" id="dataName">公开发给过覆盖发广告反对反对反对法公开发给过覆盖发广告反对反对反对法</p>
								<p class="Collection-text-btm">
									<span class="Collection-jiage">￥<span id="price">9999</span>元</span>
									<span class="Collection-time" id="updateDate">2017-7-20 9:22</span>
								</p>
							</div>
						</div>
					</div>
				</li>
			</ul>
		</div>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript">
			var userId = "${sessionScope.zmlUser.id}";
		</script>
		<script src="${ctxContents}js/collection/collection.js"></script>
		
	</body>
</html>
