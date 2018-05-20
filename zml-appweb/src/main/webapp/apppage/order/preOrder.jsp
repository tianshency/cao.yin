<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
		<meta name="format-detection" content="telephone=no">
		<title>预订计算</title>
	</head>
	<body>
		<!--<div class="computerInfo w">
			<div class="orderlist_title"><span>试算器</span></div>
			<form class="mui-input-group">
				<div class="mui-input-row">
						<label>选择规格</label>
						<input type="text" placeholder="亩用量">
					</div>
					<div class="mui-input-row">
						<label>地亩数</label>
						<input type="text" class="mui-input-clear" placeholder="地亩数">
					</div>
					<div class="mui-input-row">
						<label>试算用量</label>
						<input type="text" class="mui-input-clear" placeholder="试算用量">
					</div>

					<div class="mui-button-row">
						<button type="button" class="mui-btn mui-btn-primary" onclick="return false;">确认</button>&nbsp;&nbsp;
						<button type="button" class="mui-btn mui-btn-danger" onclick="return false;">清除</button>
					</div>
			</form>
		</div>-->
		<div class="oi_order w">
			<form class="mui-input-group">
			<div class="order_list">
				<ul>
					<li>
						<div>亩用量</div>
						<div>地亩数(亩)</div>
						<div>推荐量(袋)</div>
						<div>够买量(袋)</div>
					</li>
					<c:forEach var="item" items="${zmlCommodityStandarList}"> 
						<li>
							<div>${item.name }${item.dosage_start }-${item.dosage_end }${item.dosage_unit }
							<input type="hidden" name="preAmount" value="${item.dosage_start }-${item.dosage_end }"/>
							<input type="hidden" name="cropType" value="${item.name }"/>
							<input type="hidden" name="id" value="${item.id }"/>
							</div>
							<div class="mui-input-row"><input type="text" class="mui-input" placeholder="地亩数" name="landAmount"></div>
							<div class="mui-input-row"><input type="text" class="mui-input" placeholder="推荐量" name="recAmount" readonly></div>
							<div class="mui-input-row"><input type="text" name="numInfos" class="mui-input" placeholder="够买量"></div>
						</li>
					</c:forEach>
				</ul>
			</div>
			</form>
		</div>
		<script type="text/javascript">
			//触发计算推荐量
			$("input[name='landAmount']").change(function(){
				var value = $(this).val();
				var perAmount = $(this).parent().parent().find("input[name='preAmount']").val();
				var arr = perAmount.split("-");
				var amount = (arr[0]*1+arr[1]*1)/2;
				$(this).parent().parent().find("input[name='recAmount']").val(amount*value/50);
			});
		</script>
	</body>
</html>
    