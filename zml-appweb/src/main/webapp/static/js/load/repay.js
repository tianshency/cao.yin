/**
 * 
 */
var count=0;
function repayInfo(){
	if(repayChannel==null){
		swal({
			title: "提示",
			text: "请选择还款渠道~~",
			timer: 5000
		});
		return;
	}
	if($("#previewImg")==null||$("#previewImg")==""){
		swal({
			title: "提示",
			text: "请上传凭证~~",
			timer: 5000
		});
		return;
	}
	var repayAmt = $("#contractBalance").val();
	$.ajaxFileUpload({
        url: activePath+"myLoanController/repayment.do", 
        type: 'post',
        data : {
        	contractId:contractId,
        	repayAmt:repayAmt,
        	repayChannel:repayChannel,
        	repayPlanId:1,
        	token:1
        },
        secureuri: false, //一般设置为false
        fileElementId: "previewImg", // 上传文件的id、name属性名
        dataType: 'JSON', //返回值类型，一般设置为json、application/json  这里要用大写  不然会取不到返回的数据
        success: function(data, status){
        	count++;
        	data = eval("(" + data + ")");
        	if(data.success){
	        	swal({
					title: "小提示!",
					text: "还款申请成功,1-2个工作日给您答复~",
					timer: 5000
				});
        		location.href=activePath+"myLoanController/toMyLoan.do";
        	}
        	else{
        		if(count>1){
        			swal({
    					title: "小提示!",
    					text: "还款申请失败，请联系管理员",
    					timer: 5000
    				});
        			count=0;
        		}
        		else{
        			swal({
    					title: "小提示!",
    					text: "还款申请失败，"+data.content+"~~",
    					timer: 5000
    				});
        		}
        	}
        },
        error: function(data, status, e){ 
            alert(e);
        }
    }); 
}

$("#repay").click(function(){
	repayInfo();
})