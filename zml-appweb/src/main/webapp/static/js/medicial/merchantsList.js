/** 获取商家列表页*/
function getMerchantList(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"merchantsController/findByParamList.do",//
	    data: {},//要发送的数据
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
	    	//合作商家
	    	var datamerc = msg.map.data;
	    	var mercHref=activePath+"merchantsController/toMerchantsInfo.do?";
	    	var merHtml ="";
	    	$.each(datamerc, function(i, n){
	    		mercHref = mercHref+"merchantsId="+n.id;
	    		merHtml =merHtml + '<a href="'+mercHref+'" class="mall">'+
				'<div class="mall_logo"><img src="'+basePath+n.logo+'" onerror="javascript:this.src=\''+errorImg+'\'"/></div></a>';
	    	});
	    	$("#merchTemplateDiv").html(merHtml);
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
   	 		getMerchantList();
		}
     }
}); 

/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	//1.获得商家列表信息
	getMerchantList();
});