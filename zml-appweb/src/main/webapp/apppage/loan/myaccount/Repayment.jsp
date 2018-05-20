<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的还款</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${ctxContents }css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/Loan.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<style type="text/css">
			body{
				background: #fff;	
			}
		</style>
	</head>
	<body>
		<header class="Repayment-header">
			<a href="Loan.html">
				<img src="${ctxContents }img/left-a.png"/>
				返回
			</a>
		</header>
		<section class="Repayment-main">
			<div class="Repayment-title">
				<ul>
					<!-- <li>
						<p>当前借款（元）</p>
						<span id="contractAmt">1300.00</span>
					</li>
					<li>
						<p>已还金额（元）</p>
						<span id="moreRepayAmt">0.00</span>
					</li> -->
					<li>
						<p>应还金额（元）</p>
						<span id="contractBalance"></span>
					</li>
					<li>
						<p>应还利息（元）</p>
						<span id="profitInterest"></span>
					</li>
					<li>
						<p>罚息利息（元）</p>
						<span id="profitPenalty"></span>
					</li>
					<li>
						<p>到期还款日：</p>
						<span id="lastRepayDate"></span>
					</li>
				</ul>
			</div>
			<div class="Repayment-text">
				<p>温馨提示：</p>
				<div class="Repayment-text-main">
					<p><b>1.&nbsp;</b>每笔借款<b>还款日18:00</b>前,系统将自动完成扣款，请务必保证银行卡中活期账户可用余额不少于应还本息。</p>
					<p><b>2.&nbsp;</b>还款日当天不可选择现在还款。</p>
					<p><b>3.&nbsp;</b><b>逾期费用说明：</b>未按时还款会产生滞纳金和相应的逾期手续费。<br/>滞纳金：10元/笔<br/>逾期手续费：自应还款日起每日按借款金额的0.5%计收。</p>
				</div>
			</div>
			<a class="Repayment-btn" href="javascript:void(-1);" id="repay_bth">现在还款</a>
		</section>
	</body>
	<script src="${ctxContents }js/mui.min.js"></script>
	<script type="text/javascript">
		var contractId="${contractId}";
	</script>
	<script type="text/javascript" src="${ctxContents }js/load/repayment.js"></script>

</html>