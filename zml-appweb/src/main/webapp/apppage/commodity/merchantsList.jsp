<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>商家信息列表</title>
		<link href="${ctxContents}css/owl.carousel.css" rel="stylesheet">
		<link href="${ctxContents}css/public.css" rel="stylesheet" type="text/css" />
		<link href="${ctxContents}css/index.css" rel="stylesheet" type="text/css" />
		<script src="${ctxContents}js/jquery-1.8.3.min.js"></script>
		<script src="${ctxContents}js/owl.carousel.min.js"></script>
		<script src="${ctxContents}layer/layer.js"></script>
	</head>

	<body>
		<div class="mobile">
			<div class="m_mall w">
				<div class="mall_title"><span>合作商家</span></div>
				<div class="mall_list" id="merchTemplateDiv">
							
				</div>
			</div>
		</div>
		<div class="gotop backtop" style="display:none;"></div>
		<script type="text/javascript" src="${ctxContents }js/mui.min.js"></script>
		<script type="text/javascript" src="${ctxContents }js/medicial/merchantsList.js"></script>
	</body>
</html>