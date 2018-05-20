/**
 * 
 */
function showLoadList(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myLoanController/findMyLoanInfo.do",//
	    data: {userId:userId,page:curPage,rows:pageSize},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	var totalData = msg.map.totalData;
	    	$("#contractCount").html(totalData.contractCount);
	    	$("#totalContractBalance").html(totalData.totalContractBalance);
	    	$("#totalContractAmt").html(totalData.totalContractAmt);
	    	
	    	var listData = msg.map.listData;
	    	
	    	//如果状态为真，则做如下赋值操作
	    	$("#template").show();
	    	$.each(listData, function(i, n){
	    			var row = $("#template").clone();
	    			row.find("#contractAmt").html(n.contractAmt);
	    			var num = i;
	    			if(i<10){
	    				num="0"+(i+1)
	    			}
	    			row.find("#num").html(num);
			    	row.find("#contractNo").html(n.contractNo);
			    	row.find("#startTime").html(n.startTime);
			    	row.find("#endTime").html(n.endTime);
			    	var status = n.status;
			    	if(status==0){
			    		row.find("#status").html("未放款");
			    	}
			    	else if(status==1){
			    		row.find("#status").html("<a href='"+activePath+"myLoanController/toRepayment.do?contractId="+n.contractId+"'>立即还款</a>");
			    	}
			    	else if(status==2){
			    		row.find("#status").html("逾期");
			    	}
			    	else if(status==3){
			    		row.find("#status").html("核销");
			    	}
			    	else{
			    		row.find("#status").html("结清");
			    	}
			    	row.appendTo("#parent");//添加到模板的容器中
	    	});
	    	$("#template").hide();
	    }
	});
}


//每次查询获得回数据赋值
var curPage=1;
//初始赋值
var curPage_tmp=1;

$(window).scroll(function () {
    //已经滚动到上面的页面高度
   var scrollTop = $(this).scrollTop();
    //页面高度
   var scrollHeight = $(document).height();
     //浏览器窗口高度
   var windowHeight = $(this).height();
    //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
    if (scrollTop + windowHeight == scrollHeight) {
   	 	if(curPage != curPage_tmp){
   	 		curPage_tmp =curPage; 
   	 		showLoadList();
		}
     }
}); 


/**
 * 申请贷款
 */
$(function(){
	$("#applyLoan").click(function(){
		location.href=activePath+"loanApplicationController/toApplyLoan.do?loanType=1";
	});
	showLoadList();
})