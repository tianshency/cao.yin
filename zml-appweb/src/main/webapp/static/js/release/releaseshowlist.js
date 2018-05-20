//食物分页
var foodCurPage=1;
var foodPageSize=10;

//土地分页
var landCurPage=1;
var landPageSize=10;

//其它信息
var otherCurPage = 1;
var otherPageSize=1;
//初始赋值
var foodCurPage_tmp=1;
var landCurPage_tmp=1;
var otherCurPage_tmp=1;

$(window).scroll(function () {
    //已经滚动到上面的页面高度
   var scrollTop = $(this).scrollTop();
    //页面高度
   var scrollHeight = $(document).height();
     //浏览器窗口高度
   var windowHeight = $(this).height();
    //此处是滚动条到底部时候触发的事件，在这里写要加载的数据，或者是拉动滚动条的操作
    if (scrollTop + windowHeight == scrollHeight) {
   	 	if(foodCurPage != foodCurPage_tmp){
   	 		foodCurPage_tmp =foodCurPage; 
   	 		getReleaseFoodInfo();
		}
   	 	else if(landCurPage!=landCurPage_tmp){
   	 		landCurPage_tmp=landCurPage;
   	 		getReleaseTerritoryInfo();
   	 	}
   	 	else if(otherCurPage!=otherCurPage_tmp){
   	 		otherCurPage_tmp=otherCurPage;
   	 		getReleaseOtherInfo();
	 	}
     }
}); 

/**
 * 获得食物信息
 */
function getReleaseFoodInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"releaseInfoController/findFoodByParamList.do",//
	    data: {page:foodCurPage,rows:foodPageSize},//要发送的数据
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
	        	foodCurPage = msg.map.dataGrid.nextPage;
	        }
	    	
	    	//如果状态为真，则做如下赋值操作
	    	 $("#template1").show();
	    	var data = msg.map.data;
	    	var row =null;
	    	$.each(data, function(i, n){
    			row= $("#template1").clone();	
    			row.find("#title").html(n.title);
    			row.find("#userName").html(n.userName);
    			row.find("#createDate").html(formatDateTime(n.createDate));
    			row.find("#viewCount").html(n.viewCount);
    			if(1==n.status){
    				row.find("#status").html("审核中");
    			}
    			var img = n.coverImg==null?"img/cpmc_bigImg.png":n.coverImg;
    			row.find("#coverImg").attr("src",picturePath+"picture/"+img);
    			row.find(".release-List-text").bind("click",{id:n.id,flag:"2"},showReleaseInfoDetail);
    			row.find(".release-List-img").bind("click",{id:n.id,flag:"2"},showReleaseInfoDetail);
    			if(row.find(".release-List-btn .release-List-more")!=null&& row.find(".release-List-btn .release-List-more")!=undefined)
    				row.find(".release-List-btn .release-List-more").bind("click",{id:n.id,flag:"2"},showDiailog);
    			row.find(".release-List-btn .release-List-new").bind("click",{id:n.id,type:"31"},collectionInfo);
    			row.appendTo("#OA_task_1");
	    	});
	    	 $("#template1").hide();
	    }
	});
}


/**
 * 获得土地信息
 */
function getReleaseTerritoryInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"releaseInfoController/findTerritoryByParamList.do",//
	    data: {page:landCurPage,rows:landPageSize},//要发送的数据
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
	        	landCurPage = msg.map.dataGrid.nextPage;
	        }
	    	//如果状态为真，则做如下赋值操作
	    	 $("#template2").show();
	    	var data = msg.map.data;
	    	var row =null;
	    	$.each(data, function(i, n){
    			row= $("#template2").clone();	
    			row.find("#title").html(n.title);
    			row.find("#userName").html(n.userName);
    			row.find("#createDate").html(formatDateTime(n.createDate));
    			row.find("#viewCount").html(n.viewCount);
    			var img = n.coverImg==null?"img/cpmc_bigImg.png":n.coverImg;
    			row.find("#coverImg").attr("src",picturePath+"picture/"+img);
    			//row.bind("click",{id:n.id,flag:"1"},showReleaseInfoDetail);
    			row.find(".release-List-text").bind("click",{id:n.id,flag:"1"},showReleaseInfoDetail);
    			row.find(".release-List-img").bind("click",{id:n.id,flag:"1"},showReleaseInfoDetail);
    			if(row.find(".release-List-btn .release-List-more")!=null&& row.find(".release-List-btn .release-List-more")!=undefined)
    				row.find(".release-List-btn .release-List-more").bind("click",{id:n.id,flag:"1"},showDiailog);
    			row.find(".release-List-btn .release-List-new").bind("click",{id:n.id,type:"30"},collectionInfo);
    			row.appendTo("#OA_task_2");
	    	});
	    	 $("#template1").hide();
	    }
	});
}

/**
 * 获得综合信息
 */
function getReleaseOtherInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"releaseInfoController/findFoodByParamList.do",//
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
	        	otherCurPage = msg.map.dataGrid.nextPage;
	        }
	    	//如果状态为真，则做如下赋值操作
	    	 $("#template3").show();
	    	var data = msg.map.data;
	    	var row =null;
	    	$.each(data, function(i, n){
    			row= $("#template3").clone();	
    			row= $("#template1").clone();	
    			row.find("#title").html(n.title);
    			row.find("#userName").html(n.userName);
    			row.find("#createDate").html(formatDateTime(n.createDate));
    			row.find("#viewCount").html(n.viewCount);
    			var img = n.coverImg==null?"img/cpmc_bigImg.png":n.coverImg;
    			row.find("#coverImg").attr("src",picturePath+"picture/"+img);
    			//row.find("#status").html(n.status);
    			//row.bind("click",{id:n.id,flag:"3"},showReleaseInfoDetail);
    			row.find(".release-List-text").bind("click",{id:n.id,flag:n.releaseType},showReleaseInfoDetail);
    			row.find(".release-List-img").bind("click",{id:n.id,flag:n.releaseType},showReleaseInfoDetail);
    			if(row.find(".release-List-btn .release-List-more")!=null&& row.find(".release-List-btn .release-List-more")!=undefined)
    				row.find(".release-List-btn .release-List-more").bind("click",{id:n.id,flag:"3"},showDiailog);
    			row.find(".release-List-btn .release-List-new").bind("click",{id:n.id,type:"99"},collectionInfo);
    			row.appendTo("#OA_task_3");
	    	});
	    	 $("#template1").hide();
	    }
	});
}
//转到发布详情页面
function showReleaseInfoDetail(event){
	 location.href=activePath+"releaseInfoController/toReleaseDetailPage.do?id="+event.data.id+"&flag="+event.data.flag;
}

//弹出操作框，且保留要操作的id
var releaseId=null;
var flagType=null;
function showDiailog(event){
	releaseId = event.data.id;
	flagType = event.data.flag;
	$("#bookingNum").val("");
	$("#unit").val("");
	$("#planDealDate").val("");
	$("#bookingtel").val("");
	$("#remarks").val("");
	//showBookingInfo();
	$('#myModal').show();
	 $('#myModal').modal({show:true,backdrop:'static'});  //backdrop:true  设置背景不可操作
}

/**加载在线预订接口*/
function showBookingInfo(){
	url=activePath+"bookingController/toAddBookingPage.do?releaseId="+releaseId+"&flagType="+flagType+"&tmp="+Math.random(1000);
    $.get(url, '', function(data){  
        $('#myModal .modal-body').html(data)  
    });  
    $('#myModal').modal({show:true,backdrop:'static'});  //backdrop:true  设置背景不可操作
}

/**触发收藏事件 */
function collectionInfo(event){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"myCollectionController/collectionInfo.do",//
	    data: {dataId:event.data.id,collectionType:event.data.type},//要发送的数据
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
	    		mui.alert("收藏成功~", '消息提示', function() {
	    			return;
	    		});
	    	}
	    }
	});
}

//删除发部信息
function delReleaseInfo(){
	
}

//js 时间戳格式化
function formatDateTime(inputTime) {    
    var date = new Date(inputTime);  
    var y = date.getFullYear();    
    var m = date.getMonth() + 1;    
    m = m < 10 ? ('0' + m) : m;    
    var d = date.getDate();    
    d = d < 10 ? ('0' + d) : d;    
    var h = date.getHours();  
    h = h < 10 ? ('0' + h) : h;  
    var minute = date.getMinutes();  
    var second = date.getSeconds();  
    minute = minute < 10 ? ('0' + minute) : minute;    
    second = second < 10 ? ('0' + second) : second;   
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;    
}; 


$(function(){
	$("#item1").show();
	$("#item2").hide();
	$("#item3").hide();
	//得到食物信息
	getReleaseFoodInfo();
	//得到土地信息
	getReleaseTerritoryInfo();
	//得到其它信息
	getReleaseOtherInfo();
});