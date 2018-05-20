/** 获得我的收藏列表*/
function getMyCollectionList(){
	var collectionType = $("#collectionType").val();
	$.ajax({
	    type: "post",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myCollectionController/findCollectionByParamList.do",//
	    data: {userId:userId,collectionType:collectionType,page:curPage,rows:pageSize},//要发送的数据
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
	    	var href =activePath+"myUserController/toBannerDetail.do";
	    	$.each(data, function(i, n){
	    			var row = $("#template").clone();
	    			row.find("#linkedHref").bind("click",{dataId:n.dataId,},findDetailInfo);
	    			row.find("#coverImg").html(picturePath+"picture/"+n.coverImg);
			    	row.find("#dataName").html(n.dataName);
			    	row.find("#updateDate").html(n.updateDate);
			    	row.find("#price").html(n.price);
			    	row.find("#remarks").html(n.remarks);
			    	row.find("#delCollectById").bind("click",{obj:row,id:n.id},deleteColectionInfoById);
			    	var status = n.status;
			    	if(status==1){
			    		row.find("#status").html("正常");
			    	}
			    	else if(status==2){
			    		row.find("#status").html("删除");
			    	}
			    	else{
			    		row.find("#status").html("失效");
			    	}
			    	//row.bind("click",{url:url},findDetailInfo);
			    	row.appendTo("#Collection");//添加到模板的容器中
	    	});
	    	$("#template").hide();
	    }
	});
}

//查看收藏的商品或文章详情
function findDetailInfo(event){
	location.href=event.data.url;
}

//删除收藏信息
function deleteColectionInfoById(event){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myCollectionController/delCollectionInfo.do",//
	    data: {id:event.data.id,flag:1},//要发送的数据
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
	    		var obj = event.data.obj;
	    		$(obj).remove();  
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
   	 		getMyCollectionList();
		}
     }
}); 

$(function(){
	getMyCollectionList();
})