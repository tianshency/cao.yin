
/**完成预订*/
function completeBooking(){
	var bookingNum = $("#bookingNum").val();
	var planDealDate = $("#planDealDate").val();
	var bookingtel = $("#bookingtel").val();
	var remarks = $("#remarks").val();
	var unit = $("#unit").val();
	$.ajax({
	    type: "post",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"bookingController/addBooking.do",//
	    data: {bookingId:releaseId,bookingType:flagType,bookingNum:bookingNum,planDealDate:planDealDate,phone:bookingtel,bookingDetail:remarks,unit:unit},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	else{
	    		location.href=activePath+"bookingController/toMyBookingList.do";
	    	}
	    }
	});
}

$(function(){
	$('#checkButton').on('click',  function() {	
		completeBooking();
	});
})
