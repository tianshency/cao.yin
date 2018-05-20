/**获得商品信息列表*/
function getProductList(orders){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"commodityController/findByParamList.do",//
	    data: {classifyOneLevel:typeId,page:curPage,rows:pageSize,orders:orders},//要发送的数据
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
	    	 $("#commodityTemplate").show();
	    	 $("#commodityParent>li").not(":eq(0)").remove();
	    	var data = msg.map.data;
	    	var href=activePath+"commodityController/toCommodityDetail.do";
	    	$.each(data, function(i, n){
    			var row = $("#commodityTemplate").clone();	    	
		    	row.find("#commodityHref").attr("href",href+"?commodityId="+n.id);
		    	row.find("#commodityImg").attr("src",basePath+n.imgPath);
		    	row.find("#commodityName").html(n.name);
		    	row.find("#fare").html("运费："+n.fare+"元");
		    	row.find(".bl_price").html(n.realPrice);
		    	row.find(".bl_oprice").html(n.price);
		    	row.find(".bl_time").html(n.productionDate);
		    	row.find(".bl_mall").html(n.manufacturers);
		    	
		    	row.appendTo("#commodityParent");//添加到模板的容器中
	    	});
	    	 $("#commodityTemplate").hide();
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
	   		getProductList();
		}
     }
}); 


/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	//获得商品信息
	getProductList(null);
});

//筛选查询
var orders=null;
function shaixuan(obj,type,value){
	if(orders==null){
		orders="'"+value+"'";
	}
	else{
		orders=orders+",'"+value+"'";
	}
}



$(document).ready(function(){ 
	//销量
	$(".SalesVolume").on('click',  function(){	
		$(".SalesVolume>a").css({"color":"#fb3038"});
		$(".ControlPrice>a").css({"color":"#333"});	
		$(".ControlType>a").css({"color":"#333"});
		
		$(".ControlPrice").removeClass("price-up");
		$(".ControlPrice").removeClass("price-down");
		$(".ControlType").removeClass("Type-down");
		$("#Type-main").hide();
		getProductList(21);
	});
	//价格
	
	$(".ControlPrice").on('click',  function(){	
		alert("价格")
		/*$(".ControlPrice>a").css({"color":"#fb3038"});
		$(".ControlPrice>a").css({"color":"#333"});	*/
		$(".SalesVolume>a").css({"color":"#333"});
		$(".ControlType>a").css({"color":"#333"});
		var classPrice = $(".ControlPrice").attr("class");
		$(".ControlType").removeClass("Type-down");
		$("#Type-main").hide();
		if(classPrice.indexOf("price-up")<0 ){
			$(".ControlPrice").addClass("price-up");
			$(".ControlPrice").removeClass("price-down");
			getProductList("23");//升序
		}
		else if(classPrice.indexOf("price-down")<0){
			$(".ControlPrice").addClass("price-down");
			$(".ControlPrice").removeClass("price-up");
			getProductList("24");//升序
		}
	});
	//品类
	$(".ControlType").on('click',  function(){	
		$(".ControlType>a").css({"color":"#fb3038"});
		$(".SalesVolume>a").css({"color":"#333"});
		$(".ControlPrice>a").css({"color":"#333"});	
		
		$(".ControlPrice").removeClass("price-up");
		$(".ControlPrice").removeClass("price-down");
		$(".ControlType").addClass("Type-down");
		$("#Type-main").show();
	});
	
    $("#Type-main li").click(function() {
        $(this).addClass("selected");

    });
      //重置
    $(".TypeInput-rest").on('click',  function(){	
    	$("#Type-main li").removeClass("selected");
    })
    //确定
    $(".TypeInput-sbm").on('click',  function(){
    	alert("开始查询品类！！！");
    	$("#Type-main").hide();
    	getProductList(orders);//升序
    })
    
	});	