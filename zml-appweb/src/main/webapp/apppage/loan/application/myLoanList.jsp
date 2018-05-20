<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
	
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>我的借款</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/Loan.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<style type="text/css">
			body{
				background: #fff;	
			}
		</style>
	</head>
	<body>
		<!--<header id="detail-header" class="mui-bar mui-bar-nav">
			<div class="header-back">
				<a onClick="javascript :history.back(-1);">
					<img src="images/left-b.png"/>
				</a>
			</div>
			<h1 class="mui-title">添加地址</h1>
		</header>-->
		<section class="loan-main">
			<div class="loan-title">
				<ul>
					<li>
						累计借款:&nbsp;<span id="contractCount">3</span>&nbsp;笔
					</li>
					<li>
						当前借款:&nbsp;<span id="totalContractBalance">1000</span>&nbsp;元
					</li>
					<li>
						累计借款:&nbsp;<span id="totalContractAmt">5000</span>&nbsp;元
					</li>
					<li>
						借款状态:&nbsp;<span>正常</span>
					</li>
				</ul>
			</div>
			<div class="loan-list">
				<ul id="parent">
					<li id="template" style="display:none;">
						<p><span id="num">01</span>&nbsp;&nbsp;&nbsp;合同编号：<b id="contractNo">SJ1234354543</b></p>
						<div class="loan-list-box">
							<div class="loan-list-time">
								<p>借款时间：<span id="startTime">2015-09-10</span></p>
								<p>应还时间：<span id="endTime">2015-09-20</span></p>
							</div>
							<div class="loan-list-money">
								<p>借款金额：<span id="contractAmt">1000</span>元</p>
								<p class="loan-state" id="status">正常</p>
							</div>
						</div>
					</li>
					<!-- <li>
						<p><span>02</span>&nbsp;&nbsp;&nbsp;合同编号：<b>SJ1234354543</b></p>
						<div class="loan-list-box">
							<div class="loan-list-time">
								<p>借款时间：<span>2015-09-10</span></p>
								<p>还款时间：<span>2015-09-20</span></p>
							</div>
							<div class="loan-list-money">
								<p>借款金额：<span>1000</span>元</p>
								<p class="loan-state">已结清</p>
							</div>
						</div>
					</li>
					<li>
						<p><span>03</span>&nbsp;&nbsp;&nbsp;合同编号：<b>SJ1234354543</b></p>
						<div class="loan-list-box">
							<div class="loan-list-time">
								<p>借款时间：<span>2015-09-10</span></p>
								<p class="loan-state"><a href="Repayment.html">马上还款</a></p>
							</div>
							<div class="loan-list-money">
								<p>借款金额：<span>1000</span>元</p>
								<p class="loan-state">逾期&nbsp;<span>8</span>&nbsp;天</p>
							</div>
						</div>
					</li> -->
				</ul>
			</div>
		</section>
		<div>
			<a id="applyLoan" href="javascript:void(-1);">借款申请</button>
		</div>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript">
			var userId = "${sessionScope.zmlUser.id}";
		</script>
		<script src="${ctxContents}js/load/loadlist.js"></script>
	</body>

</html>