/**获得商品信息列表*/
function getMerchantsProductList(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"commodityController/findByParamList.do",//
	    data: {merchantsId:merchantsId,page:curPage,rows:pageSize},//要发送的数据
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
	    	 $("#store-all_li").show();
	    	var data = msg.map.data;
	    	$.each(data, function(i, n){
    			var row = $("#store-all_li").clone();	    	
		    	row.find(".Store-home-order-img img").attr("src",basePath+n.imgPath);
		    	row.find(".Store-all-text .Store-home-order-text").html(n.name);
		    	//row.find("#fare").html("运费："+n.fare+"元");
		    	row.find(".Store-home-order-price span").html(n.realPrice);
		    	row.bind("click",{commodityId:n.id},toCommodityDetailInfo);
		    	row.appendTo("#store-all_ul");//添加到模板的容器中
	    	});
	    	 $("#store-all_li").hide();
	    }
	});
}

function toCommodityDetailInfo(event){
	location.href=activePath+"commodityController/toCommodityDetail.do?commodityId="+event.data.commodityId;
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
   	 		getMerchantsProductList();
		}
     }
}); 

/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	//获得商品信息
	getMerchantsProductList();
});