<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>签约</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<meta http-equiv="x-rim-auto-match" content="none">
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/Loan.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/media.css"/>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<style type="text/css">
			body{
				background: #fff;	
			}
			input[type=number]{
			    line-height: 21px;
			    width: 48%;
			    height: 22px;
			    margin-bottom: 15px;
			    padding: 2px 2px;
			    -webkit-user-select: text;
			    border: 1px solid rgba(0,0,0,.2);
			    border-radius: 3px;
			    outline: 0;
			    background-color: #fff;
			    -webkit-appearance: none;
			}
		</style>
	</head>
	<body>
		<header class="sign-back">
			<a onClick="javascript :history.back(-1);">
				<img src="${ctxContents }img/head_returnIcon.png"/>
			</a>
		</header>
		<section class="sign-main">
			<div class="sign-top">
				<ul>
					<li>
						借款人:&nbsp;<span>波波</span>
					</li>
					<li>
						借款期限:&nbsp;<span id="term"></span>&nbsp;<span id="termUnit"></span>
					</li>
					<li>
						审批额度:&nbsp;<span id="approveAmount"></span>&nbsp;元
					</li>
					<li>
						借款金额:&nbsp;<input type="number" id="contractAmt">元
					</li>
					<li>
						服务费:&nbsp;<span id="fee"></span>&nbsp;元
					</li>
					<!-- <li>
						储蓄卡:&nbsp;<span>光大银行</span>
					</li>
					<li>
						银行卡号:&nbsp;<span>6220****3923</span>
					</li> -->
				</ul>
			</div>
			<div class="sign-btm">
				<p>应还时间&nbsp;:&nbsp;<span>2015.12.22</span></p>
				<p>应还金额&nbsp;:&nbsp;<span>1000</span>&nbsp;元</p>
				<div class="sign-btm-text">
					<b>还款方式：</b>默认为委托代扣还款，只要您保证在还款日当天18：00前绑定银行卡中活期可用余额大于应还金额，我们会自动扣除您的应还金额，您也可以在账户中进行手动还款操作。
				</div>
				<div class="sign-btm-a">
					注：逾期将产生<a href="">滞纳金和罚息</a>
				</div>
				<div class="sign-form">
					<form action="">
					    <input type="hidden" name="applyApproveId" id="applyApproveId" value="" />
						<div class="sign-form-checkbox">
							<div>
								<input type="checkbox" name="mjjkxy" id="mjjkxy" value="" />
								<label for="#mjjkxy">确认借款并同意</label>
								<a href="">《秒借借款协议》</a>
							</div>
							<div>
								<input type="checkbox" name="wtdkxy" id="wtdkxy" value="" />
								<label for="#wtdkxy">确认借款并同意</label>
								<a href="">《委托代扣协议》</a>
							</div>
						</div>
						<input class="sign-submit" type="button" id="sign_btn" value="确定签约" disabled="disabled"/>
					</form>
				</div>
			</div>
		</section>
	</body>
	<script type="text/javascript">
		var applyId = "${applyId}";
	</script>
	<script src="${ctxContents }js/mui.min.js"></script>
	<script src="${ctxContents }js/load/loansign.js"></script>
</html>