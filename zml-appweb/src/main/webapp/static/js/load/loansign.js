/**
 * 查询签约数据
 */
var applyApproveId=null;
function findSignContractData(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"loanApplicationController/findSignContractData.do",
	    data: {applyId:applyId},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为真，则做如下赋值操作
	    	var data = msg.map.data; 
	    	$("#term").html(data.term);
	    	$("#termUnit").html(data.termUnit);
	    	$("#approveAmount").html(data.approveAmount);
	    	$("#fee").html(data.fee);
	    	applyApproveId=data.id;
	    	$("#sign_btn").attr("disabled",false);
	    }
	});
}

$(function(){
	findSignContractData();
	//签约
	$("#sign_btn").click(function(){
		if(applyApproveId==null){
			return;
		}
		var contractAmt = $("#contractAmt").val();
		$.ajax({
		    type: "get",//使用get方法访问后台
		    dataType: "json",//返回json格式的数据
		    url: activePath+"myLoanController.do?signedContract",
		    data: {applId:applyId,approveAmount:contractAmt,applyApproveId:applyApproveId},//要发送的数据
		    complete :function(){},//AJAX请求完成时隐藏loading提示
		    success: function(msg){//msg为返回的数据，在这里做数据绑定
		    	alert(msg.content);
		    	if(msg.success){
		    		location.href=activePath+"myLoanController/toMyLoan.do";
		    	}
		    	
		    }
		});
	})
});

