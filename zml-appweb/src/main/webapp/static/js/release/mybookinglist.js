/**
 * 获得我的订阅信息列表
 */
function getMyBookingListInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"bookingController/findBookingByUserId.do",//
	    data: {page:curPage,rows:pageSize},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	
	    	//下一页的处理
	        if(msg.map.dataGrid.nextPage>msg.map.dataGrid.page){
	        	curpage = msg.map.dataGrid.nextPage;
	        }
	    	//如果状态为真，则做如下赋值操作
	    	$("#template").show();
	    	var data = msg.map.data;
	    	var row =null;
	    	var href=activePath+"bookingController/toBookingDetail.do"
	    	$.each(data, function(i, n){
    			row= $("#template").clone();	
    			row.find("#title").html(n.bookingTitle);
    			row.find("#person").html(n.userName);
    			row.find("#status").html(n.status);
    			row.find("#type").html(n.bookingType);
    			row.find("a").attr("href",href+"?id="+n.id);
    			row.find("#del").bind("click",{id:n.id,flag:"1"},delBookingById);
    			row.find("#comp").bind("click",{id:n.id,flag:"1"},updateBookingById);
    			row.appendTo("#OA_task_1");
	    	});
	    	 $("#template").hide();
	    }
	});
}

/**
 * 完成预订
 * @param event
 */
function updateBookingById(event){
	$.ajax({
		async:false,
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"bookingController/doUpdate.do",//要访问的后台地址
	    data: {id:event.data.id,status:"0"},//要发送的数据
	    complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	if(msg.success){
	    		mui.alert("修改成功");
	    	}
	    	else{
	    		mui.alert(msg.content);
	    	}
	    }
	});
}

/**
 * 删除
 * @param event
 */
function delBookingById(event){
	$.ajax({
		async:false,
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"bookingController/delBooking.do",//要访问的后台地址
	    data: {id:event.data.id},//要发送的数据
	    complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	if(msg.success){
	    		mui.alert("删除成功");
	    	}
	    	else{
	    		mui.alert(msg.content);
	    	}
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
   	 		getMyBookingListInfo();
		}
     }
}); 

$(function(){
	getMyBookingListInfo();
});