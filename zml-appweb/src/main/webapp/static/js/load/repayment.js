/**
 * 获得还款基本信息
 */

/**
 * 获得还款基本信息
 */

function getRepayInfo(){
	$.ajax({
		    type: "get",//使用get方法访问后台
		    dataType: "json",//返回json格式的数据
		    url: activePath+"myLoanController/findMyContractShouldRepayInfo.do",//
		    data: {contractId:contractId,num:1},//要发送的数据
		    complete :function(){},//AJAX请求完成时隐藏loading提示
		    success: function(msg){//msg为返回的数据，在这里做数据绑定
		    	//如果状态为假，则给出提示
		    	if(!msg.success){
		    		mui.alert(msg.content, '消息提示', function() {
		    			return;
		    		});
		    		return;
		    	}
		    	var data = msg.map.rsMap;
		    	/*$("#contractAmt").html(data.rsMap.profitPrincipal);*/
		    	$("#endTime").html(data.lastRepayDate);
		    	$("#contractBalance").html(data.profitPrincipal);
		    	/*$("#moreRepayAmt").html(data.contractAmt*1-data.contractBalance*1)*/
		    	$("#profitInterest").html(data.profitInterest);
		    	$("#profitPenalty").html(data.profitPenalty);
		    	$("#profitPenalty").html(data.lastRepayDate);
		    	
		    	var listData = msg.map.listData;
		    }
	});
}

$(function(){
	$("#repay_bth").click(function(){
		location.href=activePath+"myLoanController/toRepayTools.do?contractId="+contractId;
	})
	getRepayInfo();
})