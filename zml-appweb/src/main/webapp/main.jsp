<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html lang="en" style="font-size: 80px;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<!-- 忽略页面中的数字识别为电话，忽略email识别 -->
<meta name="format-detection" content="telphone=no, email=no">
<!--清除缓存 微信浏览器缓存严重又无刷新-->
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<!--便于搜索引擎搜索-->
<meta name="keywords"
	content="速贷360,速贷之家,极速贷款,快速贷款、快速分期一站式智能搜索比价平台,帮助借款人选择最适合他的借款方案">
<meta name="description"
	content="速贷之家是全国首家消费金融贷款智能搜索匹配平台，我们以最快的速度帮您借到最便宜的钱。速贷之家，极速借现金。">
<!--地址栏图标-->
<link rel="shortcut icon"
	href="https://event.sudaizhijia.com/m/landing13/favicon.ico"
	type="image/x-icon">
<title>极速贷款，上速贷之家</title>
<script src="${ctxContents}jszj/htmlrem.min.js"></script>
<link rel="stylesheet" href="${ctxContents}jszj/resetA.css">
<link rel="stylesheet" href="${ctxContents}jszj/index.css">
<link rel="stylesheet" href="${ctxContents}jszj/jquery.toast.min.css">
</head>

<body>
	<div class="container">
		<section class="top_img"> <img src="${ctxContents}jszj/banner.png"
			alt="">
		<img src="${ctxContents}jszj/liucheng.png" alt=""> </section>
		<section> <!--每月利息-->
		<div class="interest">
			<span><i class="bantopr">25075</i>元</span>
		</div>
		<!--借款金额-->
		<h3 class="j_money">
			借款金额 <span><i class="my_account">25000</i>元</span>
		</h3>
		<div class="rangeSlider">
			<div>
				<input type="range" name="loan_amount" value="25000"
					id="loan_amount"
					style="background: linear-gradient(to right, #FEF059, #E0E5F0 73.33333%, #E0E5F0)"
					min="1000" max="50000" step="1000">
			</div>
			<ul class="amount">
				<li style="text-indent: 0"><span>1000</span></li>
				<li style="text-indent: 2%"><span></span></li>
				<li style="text-indent: 9%"><span></span></li>
				<li style="text-align: right"><span>50000</span></li>
			</ul>
		</div>
		<!--借款期限-->
		<h3 class="j_time">借款期限</h3>
		<div class="month">
			<span class="choice" data-value="1"></span> <span data-value="3"></span>
			<span data-value="6"></span><span data-value="12"></span>
		</div>
		</section>
		<footer>
		<div>
			<div>
				<input type="tel" placeholder="请输入您的手机号">
			</div>
			<input type="button" value="点击借款">
		</div>
		<p>所有权 © 2017-2018 北京智借网络科技有限公司. All rights reserved.</p>
		</footer>
	</div>
	<div class="popup">请输入正确手机号</div>
	<div class="cover">
		<div>
			<img src="${ctxContents}jszj/coverBtn.png" alt="" class="coverBtn"> <input
				type="text" placeholder="请输入短信验证码" class="ma">
			<div class="countDown">
				<div id="djs">
					重新获取(<b class="time">60</b>s)
				</div>
				<div id="again">重新获取</div>
			</div>
			<div class="sureBtn"></div>
		</div>
	</div>
	<div class="landingCover">
		<div class="landing">
			<img src="${ctxContents}jszj/load2.gif" alt="">
		</div>
	</div>
	<script src="${ctxContents}jszj/jquery-3.1.1.min.js"></script>
	<script src="${ctxContents}jszj/rangeSlider.js"></script>
	<script src="${ctxContents}jszj/jquery.cookie-1.4.1.min.js"></script>
	<script src="${ctxContents}jszj/jquery.toast.min.js"></script>
	<script src="${ctxContents}jszj/sha1.js"></script>
	<script src="${ctxContents}jszj/beforeSend.js"></script>
	<script src="${ctxContents}jszj/index.js"></script>
	<script src="${ctxContents}jszj/jweixin-1.2.0.js"></script>
	<script src="${ctxContents}jszj/wx.js"></script>
	<script type="text/javascript">
		var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cspan id='cnzz_stat_icon_1261063062'%3E%3C/span%3E%3Cscript src='"
						+ cnzz_protocol
						+ "s95.cnzz.com/z_stat.php%3Fid%3D1261063062' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<span id="cnzz_stat_icon_1261063062"><a
		href="http://www.cnzz.com/stat/website.php?web_id=1261063062"
		target="_blank" title="站长统计">站长统计</a></span>
	<script src="${ctxContents}jszj/z_stat.php" type="text/javascript"></script>
	<script src="${ctxContents}jszj/core.php" charset="utf-8" type="text/javascript"></script>
</body>
</html>