/**获得订单列表*/
function getOrderList(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"orderController/findByParamList.do",//
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
	    	//下一页的处理
	        if(msg.map.dataGrid.nextPage>msg.map.dataGrid.page){
	        	curpage = msg.map.dataGrid.nextPage;
	        }
	    	//如果状态为真，则做如下赋值操作
	    	 $("#orderTemplate").show();
	    	var data = msg.map.data;
	    	var href= activePath+"orderController/toOrderDetail.do";//转到订单详情页面
	    	var orderId="";
	    	var row =null;
	    	$.each(data, function(i, n){
	    		if(orderId==""|| (orderId!='' && orderId!=n.id)){
	    			orderId=n.id;
	    			row= $("#templateList").clone();	
	    			/*row.find("#mercLog").attr("src",basePath+n.mercLog);*/
	    			row.find("#orderNo").html(n.orderNum);
	    			row.find("#orderStatus").html(dealOrderStatus(n.status));
	    			row.find(".bl_img img").attr("src",basePath+n.coverImg);
	    			row.find(".bl_title").html(n.commodityName);
	    			row.find(".bl_price").html(n.price+" 元 <b>共"+n.allCount+"件</b>");
	    			row.find(".bl_number p").html("合计："+n.ocAllAmount+"元 (含运费"+n.logisticsFee+" 元)");
	    			if(n.status==4||n.status==5 || n.status==6){
	    				row.find("#delBtn").show();
	    				row.find("#delBtn").bind("click",{obj:this,orderId:n.id},delOrderInfoById);
	    				row.find(".order_del a").bind("click",{orderId:n.id},againPurchase);
	    			}
	    			else{
	    				row.find(".order_del").hide();
	    			}
	    			
	    			row.find("#templateA").attr("href",href+"?orderId="+n.id);
	    			row.appendTo("#templateParent");
	    		}
	    		
	    		else{
	    			var rowchild = $("#templateList").find(".order_main").clone();
	    			rowchild.find(".bl_img img").attr("src",basePath+n.coverImg);
	    			rowchild.find(".bl_title").html(n.commodityName);
	    			rowchild.find(".bl_price").html(n.price+" 元 <b>共"+n.allCount+"件</b>");
	    			rowchild.find(".bl_number p").html("合计："+n.ocAllAmount+"元 (含运费"+n.logisticsFee+" 元)");
		    		rowchild.appendTo(row.find("#templateA"));//添加到模板的容器中
	    		}
	    		
	    	});
	    	 $("#templateList").hide();
	    }
	});
}

/**订单状态处理方法*/
function dealOrderStatus(orderStatus){
	var states ="未付款";
	if(1==orderStatus) states="处理中";
	else if(2==orderStatus) states="商家已发货";
	else if(3==orderStatus) states="未付余款";
	else if(4==orderStatus) states="订单完成";
	else if(5==orderStatus) states="系统取消";
	else if(6==orderStatus) states="用户取消";
	return states
}


/**删除订单*/
function delOrderInfoById(event){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"orderController/delOrder.do",//
	    data: {orderId:event.data.orderId},//要发送的数据
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
	    		mui.alert("删除成功");
	    		getOrderList();
	    	}
	    }
	});
}

/**再次购买 通过商品id下订单   这个功能暂时没有*/
function againPurchase(event){
	//将订单中的商品加入到购物车
	
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
   	 		getOrderList();
		}
     }
}); 

/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	getOrderList();
});