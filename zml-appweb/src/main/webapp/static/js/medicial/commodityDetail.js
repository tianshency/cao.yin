/*** 获取商品详情图片*//*
function getCommodityDetailImg(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"commodityController/commodityDetail.do",//
	    data: {commodityId:commodityId},//要发送的数据
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
	    	var data = msg.map.commodity;
	    	$.each(data, function(i, n){
	    		if(i==0){
		    		var imgStr = '<li class="mui-slider-item"><img src="img/detail03.jpg" data-preview-src="" data-preview-group="1" /></li>';
		    		$("#imgPath").html($("#imgPath").html()+imgStr);
	    		}
	    		else{
	    			var imgStr = '<li class="mui-slider-item"><img src="img/detail03.jpg" /></li>';
		    		$("#imgPath").html($("#imgPath").html()+imgStr);
	    		}
	    	});
	    }
	});
	
}*/
/*** 获取商品详情*/
function getCommodityDetail(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"commodityController/commodityDetail.do",//
	    data: {commodityId:commodityId},//要发送的数据
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
	    	$(".bl_view_title").html(data.name+":"+data.introduction);
	    	$("#price").html(data.realPrice);
	    	$("#oldPrice").html(data.price);
	    	$(".bl_view_mall").html("运费："+data.fare+"元");
	    	$("#mercinfo").html(data.manufacturers);
	    	$("#reserveAmount").html("库存:"+data.reserveAmount);
	    	$("#salesVolume").html("销量："+(data.totalAmount*1-data.reserveAmount*1));
	    	commodityId = data.id;
	    	//商品图片信息
	    	var doc_Data = msg.map.data.commodityDoc;
	    	var imgStr="";
	    	$.each(doc_Data, function(i, n){
	    		imgStr = imgStr+'<li class="mui-slider-item" ><img src="'+basePath+n.imagePath+'" data-preview-src="" data-preview-group="1" /></li>';
	    	})
	    	if(imgStr!=""&&imgStr.length>0){
	    		$("#parentUl").html($("#parentUl").html()+imgStr);
	    	}
	    	
	    	if(data.reserveAmount==null||data.reserveAmount==0){
	    		$("#addPreOrder").attr("disabled",true);
	    		$("#reserveAmount").html("暂无货");
	    		$("#reserveAmount").css("color","#C55B67");
	    		var obj=$(".detail-auction-btn");
	    		obj.css("background","#F597A3");
	    	}
	    }
	});
}

/**查询购物车数量接口*/
function getShoppingCartCount(){
	$.ajax({
	    type: "post",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"cartsController/findCartsCountByUserId.do",//
	    data: {userId:userId},//要发送的数据
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
	    		$(".detail-shopCart>b").show();
	    		//location.href="";
	    	}
	    }
	});
}

/**加入购物车*/
function JoinShoppingCart(){
	var totalNums = 0;
	$('input[name="numInfos"]').each(function(){
		totalNums = totalNums+($(this).val())*1
	});
	$.ajax({
	    type: "post",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"cartsController/addCarts.do",//
	    data: {userId:userId,commodityId:commodityId,amount:totalNums},//要发送的数据
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
	    		$(".detail-shopCart>b").show();
				$(".detail-shopCart-bg").show();
				$(this).attr('disabled',"true");
				setTimeout( 
		       	function test(){
		     		$(".detail-shopCart-bg").hide();  
		     	},2000);
	    	}
	    }
	});
}

/**保存栽种计划
	@Excel(name="种植类型")
	private java.lang.String cropType;
	@Excel(name="土地数量")
	private java.math.BigDecimal landAmout;
	@Excel(name="使用商品ID")
	private java.lang.String useCommodityId;
	@Excel(name="计算商品数量")
	private java.math.BigDecimal calcCommodityAmout;
	@Excel(name="实际商品数量")
	private java.math.BigDecimal realCommodityAmout;
 * */
var featureCodes=[];
function saveCropPlanInfo(){
	var lis = $(".modal-body li");
	$.each(lis, function(i, n){
		var str ="";
		if(i!=0){
			var cropType=$(n).find("input[name='cropType']").val();
			var landAmout=$(n).find("input[name='landAmount']").val();
			var recAmount=$(n).find("input[name='recAmount']").val();
			var realCommodityAmout = $(n).find("input[name='numInfos']").val();
			var str ={cropType:cropType,landAmout:landAmout,useCommodityId:commodityId,calcCommodityAmout:recAmount,realCommodityAmout:realCommodityAmout};
			  featureCodes.push(str );
		}
	})
	
	 $.ajax({
         type: "POST",
         url: activePath+"cropPlanController/addCropPlan.do",//
        /* contentType: "application/json",*/
         dataType: "json",
         data: JSON.stringify(featureCodes),
         headers : {
             'Accept' : 'application/json',
             'Content-Type' : 'application/json'
         },
         success: function (msg) {
        	 if(msg=="0"){
 	    		JoinShoppingCart();
 	    	}
 	    	else{
 	    		mui.alert(msg.content, '消息提示', function() {
 	    			return;
 	    		});
 	    	}
         }
     });
}

/**转到购物车列表页*/
function shoppingcart(){
	$(".detail-shopCart>b").show();
	if($(".detail-shopCart>b").is(":hidden")){
		alert("您的购物车是空的！");
	}else{	
		location.href=activePath+'cartsController/toShoppingCartPage.do?userId='+userId+"&page="+curPage+"&rows="+pageSize;
	}
}	

/**加载预订订单接口*/
function showPreOrderDialog(){
	url=activePath+"commodityController/commodityStandardList.do?commodityId="+commodityId+"&tmp="+Math.random(1000);
    $.get(url, '', function(data){  
        $('#myModal .modal-body').html(data)  
    });  
    $('#myModal').modal({show:true,backdrop:'static'});  //backdrop:true  设置背景不可操作
}

/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	//1.获得商品信息
	getCommodityDetail();
	//2.查看购物车数量
	getShoppingCartCount();
	//触发弹出栽种计划层   
	//$("#addPreOrder").on('click',showPreOrderDialog);
	$("#addPreOrder").on('click',JoinShoppingCart);
	$(".detail-shopCart").on('click',shoppingcart);
	//加入购物车
	$('#checkButton').on('click',  function() {	
		//JoinShoppingCart();
		//保存栽种计划暂不需要支持
		saveCropPlanInfo();
	});
	//收藏商品信息
	$("#collectionCommodity").on('click',function(){
		url=activePath+"myCollectionController/collectionInfo.do?collectionType=1&dataId="+commodityId+"&tmp="+Math.random(1000);
	    $.get(url, '', function(data){  
	    	mui.alert("收藏成功~", '消息提示', function() {});
	    });  
	});
});