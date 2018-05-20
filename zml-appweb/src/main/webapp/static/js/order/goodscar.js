// JavaScript Document
$(document).ready(function(){
	//全选  反选
	$("#selectAll").click(function(){
		var checks = $("input[type='checkbox']");
		var flag = this.checked;
		$.each(checks, function(i, n){
			if(i!=0&&i!=1){
				n.checked=flag;
			}
		});
	});
	/**
	 * 转到支付页面
	 */
	$("#submitOrder").click(function(){
		var checks = $(".bl_radio>input:checkbox:checked");
		var strId = "";
		$.each(checks, function(i, n){
			if(i==0){
				strId=n.value;
			}else{
				strId=strId+","+n.value;
			}
		});
		if(strId==null || strId=="" || strId==undefined){
			alert("请选择要付款的商品信息！");
			return;
		}
		location.href=activePath+"cartsController/toPayShoppingConfirm.html.do?ids="+strId;
	});
	
	/** 长按删除购物车中的某一条信息*/
	$("#delCartById").click(function(){
		deleteShoppingCarInfo();
	});
});

/**获得购车列表*/
function getGoodscarList(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"cartsController/findByParamList.do",//
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
	    	 $("#goodscartemplate").show();
	    	var data = msg.map.data;
	    	var href=activePath+"commodityController/toCommodityDetail.do";
	    	var mercId="";
	    	var row =null;
	    	var str="";
	    	$.each(data, function(i, n){
	    		if(mercId==""|| (mercId!="" && mercId!=n.merchantsId)){
	    			if(mercId!=""){
	    				row.appendTo("#goodscarParent");
	    			}
	    			mercId=n.merchantsId;
	    			row= $("#goodscartemplate").clone();
	    			if(n.reserveAmount==null || n.reserveAmount==0){
		    			row.find("input[type='checkbox']").attr("disabled",true);
		    			row.find(".add").attr("disabled",true);
		    			row.find(".del").attr("disabled",true);
		    		}
	    			else{
	    				row.find(".add").bind("click",{obj:this,id:n.id},addBtn);
		    			row.find(".del").bind("click",{obj:this,id:n.id},subBtn);
	    			}
	    			row.find("#mercName").html(n.manufacturers);
	    			row.find("#mercId").val(n.merchantsId);
	    			row.find("#mercId").bind("click",{obj:row.find(".goodscar_title")},selectAllByMerch);
	    			row.find("#goodsId").val(n.id);
	    			row.find("#goodsImg").attr("src",basePath+"static/img/huafei.jpg");//图片
	    			row.find("#goodsTitle").html(n.name);
	    			row.find("#realPrice").html(n.realPrice);
	    			row.find("#reserveAmount").html(n.reserveAmount);
	    			row.find("#purchaseNum").val(n.amount);//购买数量
	    			//row.bind("touchstart",{obj:this,id:n.id},longButtonEvent)//触发长按事件 20170717
	    			//row.bind("touchend",{obj:this,id:n.id},longTouchend)//触发长按事件 20170717
	    			row.find("#goodsImg").bind("touchstart",{obj:row,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		row.find("#goodsImg").bind("touchend",{obj:row,id:n.id},longButtonEvent)//触发长按事件 20170717);
	    			row.find("#goodsTitle").bind("touchstart",{obj:row,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		row.find("#goodsTitle").bind("touchend",{obj:row,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		row.find("#realPrice").bind("touchstart",{obj:row,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		row.find("#realPrice").bind("touchend",{obj:row,id:n.id},longButtonEvent)//触发长按事件 20170717);
	    			
	    		}
	    		else{
	    			var rowchild = $("#goodscartemplate").find("#goodsListTemplate").clone();
		    		rowchild.find("#goodsId").val(n.id);
		    		rowchild.find("#goodsImg").attr("src",basePath+"static/img/huafei.jpg");//图片 n.coverImg
		    		rowchild.find("#delByMercId").bind("click",{obj:this,id:n.id},deleteShoppingCarInfo)
			    	
		    		rowchild.find("#goodsTitle").html(n.name);
		    		rowchild.find("#realPrice").html(n.realPrice);
		    		rowchild.find("#reserveAmount").html(n.reserveAmount);
		    		rowchild.find("#goodsImg").bind("touchstart",{obj:rowchild,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		rowchild.find("#goodsImg").bind("touchend",{obj:rowchild,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		rowchild.find("#goodsTitle").bind("touchstart",{obj:rowchild,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		rowchild.find("#goodsTitle").bind("touchend",{obj:rowchild,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		rowchild.find("#realPrice").bind("touchstart",{obj:rowchild,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		rowchild.find("#realPrice").bind("touchend",{obj:rowchild,id:n.id},longButtonEvent)//触发长按事件 20170717);
		    		//rowchild.bind("touchstart",{obj:this,id:n.id},longButtonEvent)//触发长按事件 20170717
		    		//rowchild.bind("touchend",{obj:this,id:n.id},longTouchend)//触发长按事件 20170717
		    		if(n.reserveAmount==null || n.reserveAmount==0){
		    			rowchild.find("input[type='checkbox']").attr("disabled",true);
		    			rowchild.find(".del").bind("click",{obj:this,id:n.id},subBtn);
			    		rowchild.appendTo(row.find("#goodsList"));//添加到模板的容器中
		    		}
		    		else{
		    			rowchild.find(".del").bind("click",{obj:this,id:n.id},subBtn);
			    		rowchild.appendTo(row.find("#goodsList"));//添加到模板的容器中
		    		}
		    		if(n.amount==1){//当订购车中的订购数量为1时，则将减按钮置灰，不允计点击
		    			rowchild.find(".del").attr("disabled",true);
		    		}
		    		rowchild.find("#purchaseNum").val(n.amount);//购买数量
		    		rowchild.find(".add").bind("click",{obj:this,id:n.id},addBtn);
		    		
	    		}
	    		
	    		if(i==data.length-1){
	    			row.appendTo("#goodscarParent");
	    		}
	    	});
	    	 $("#goodscartemplate").hide();
	    }
	});
}

/**删除一条购物信息*/
function deleteShoppingCarInfo(event){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"cartsController/delCarts.do",//
	    data: {id:event.data.id},//要发送的数据
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
	    	}
	    }
	});
}

/**点击长按弹出层的按钮触发删除一条购物信息*/
var rowSelect=null;//此值在长按时赋值
function deleteShoppingCarInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"cartsController/delCarts.do",//
	    data: {id:$("#cartId").val()},//要发送的数据
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
	    		$("#myModalDel").modal("hide");
	    		mui.alert("删除成功");
	    		$(rowSelect).remove();
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
   	 		getGoodscarList();
		}
     }
}); 

/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	getGoodscarList();
});

/**增加数量按钮*/
function addBtn(event){
	var num = $(this).parent().index();
	var value = $(this).parent().find(".num").val();
	value++;
	if(value>=0){
		$(this).parent().find(".num").val(value);
	}
	updateCartsNum(this,value,event.data.id);
}

/**减少数量按钮*/
function subBtn(event){
	var num = $(this).parent().index();
	var value = $(this).parent().find(".num").val();
	value--;
	updateCartsNum(this,value,event.data.id);
}

function updateCartsNum(obj,value,cartId){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"cartsController/doUpdate.do",//
	    data: {id:cartId,amount:value},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(msg.success){
	    		if(value>=0){
	    			$(obj).parent().find(".num").val(value);
	    			if(value==1){
	    				$(obj).attr("disabled",true);
	    			}
	    			else{
	    				$(obj).attr("disabled",false);
	    			}
	    		}
	    	}
	    	else{
	    		mui.alert("修改数量失败！");
	    	}
	    }
	});
}

/**某个商家选中时，则其下购物车的商品应全部被选中*/
function selectAllByMerch(event){
	var parentEle = $(event.data.obj).parent();
	var flag = this.checked;
	var radios = parentEle.find(".bl_radio>input");
	$.each(radios, function(i, n){
		n.checked=flag;
	});
}


/** 长按事件弹出一个删除层*/
var time=0;
function longButtonEvent(event){
	rowSelect=event.data.obj;
	event.stopPropagation();  
    time = setTimeout(function(){  
    	$("#cartId").val(event.data.id);
    	$('#myModalDel').modal({show:true,backdrop:'static'});  //backdrop:true  设置背景不可操作
    }, 1000);//这里设置长按响应时间  
}

function longTouchend(event){
	 clearTimeout(time);  
	 time=0;
}


