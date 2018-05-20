/*** 获取商家介绍详情*/
function getCommodityDetail(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"merchantsController/merchantsDetail.do",//
	    data: {id:merchantsId},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		mui.alert(msg.content, '消息提示', function() {
	    			return;
	    		});
	    		return;
	    	}
	    	//如果状态为真，则做如下赋值操作
	    	//农资类型
	    	var data = msg.map.data.commodity;
	    	$("#logo").attr("src",basePath+data.logo);
	    	$("#departname").html(data.departname);
	    	$("#grade").html(data.grade);
	    	$("#description").html(data.description);
	    	$("#address").html(data.address);
	    	$("#reserveAmount").html("库存:"+data.reserveAmount);
	    	$("#salesVolume").html("销量："+(data.totalAmount*1-data.reserveAmount*1));
	    	//商品图片信息
	    	var doc_Data = msg.map.data.commodityDoc;
	    	var imgStr="";
	    	$.each(doc_Data, function(i, n){
	    		imgStr = imgStr+'<li class="mui-slider-item" ><img src="'+basePath+n.imagePath+'" data-preview-src="" data-preview-group="1" /></li>';
	    	})
	    	if(imgStr!=""&&imgStr.length>0){
	    		$("#parentUl").html($("#parentUl").html()+imgStr);
	    	}
	    }
	});
}