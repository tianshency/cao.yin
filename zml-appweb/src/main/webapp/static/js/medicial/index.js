/** 获得轮播图*/
function getBannerList(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"indexController.do?bannerList",//
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
	    	//如果状态为真，则做如下赋值操作
	    	$("#templateBanner").show();
	    	var data = msg.map.bannersList; 
	    	var href =activePath+"indexController/toBannerDetail.do";
	    	var str = "";
	    	var tmp="";
	    	$.each(data, function(i, n){
	    			/*if(i==0){
	    				str=str +
	    						'<div class="mui-slider-item mui-slider-item-duplicate">'+
								'<a href="'+href+'?bannerId='+n.id+'">'+
							'<img src="'+n.path+'" onerror="javascript:this.src="'+basePath+'img/noImg.jpg">'+
							'<p class="mui-slider-title">'+n.title+'</p>'+
							'</a>'+
							'</div>';
	    				tmp = str;
	    			}
	    			else{
	    				str=str +
							'<div class="mui-slider-item">'+
								'<a href="'+href+'?bannerId='+n.id+'">'+
							'<img src="'+n.path+'" onerror="javascript:this.src="'+basePath+'img/noImg.jpg">'+
							'<p class="mui-slider-title">'+n.title+'</p>'+
							'</a>'+
							'</div>';
	    			}
	    			if(i==data.length-1){
	    				str=str+tmp;
	    				$("#parentTemplate").html(str);
	    				var slider = mui("#slider");
	    				slider.slider({
	    					interval: 5000
	    				});
	    			}*/
	    			
	    			var row = $("#templateBanner").clone();	    	
			    	row.find("a").attr("href",href+"?bannerId="+n.id);
			    	row.find("img").attr("src",n.path);
			    	row.appendTo("#parentTemplate");//添加到模板的容器中
	    	});
	    	 $("#templateBanner").hide();
	    }
	});
}

/** 获取商家与种类*/
function getMediAndSort(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"merchantsController/agriResourcesMerchants.do?bannerList",//
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
	    	//如果状态为真，则做如下赋值操作
	    	//农资类型
	    	var data = msg.map.classifyList;
	    	var href=activePath+"commodityController/toProductListView.do";
	    	var cropHtml ="";
	    	$.each(data, function(i, n){
	    		var hrefCropType=href+"?typeId="+n.id;
	    		cropHtml =cropHtml + '<a href="'+hrefCropType+'"><img src="'+basePath+n.logo+'" onerror="javascript:this.src=\''+errorImg+'\'"/><span>'+n.name+'</span></a>';
	    	});
	    	$("#cropTemplateDiv").html(cropHtml);
	    	 
	    	//合作商家
	    	var datamerc = msg.map.mercList;
	    	var mercHref=activePath+"merchantsController/toMerchantsInfo.do";
	    	var merHtml ="";
	    	$.each(datamerc, function(i, n){
	    		merHtml =merHtml + '<a href="'+mercHref+'?merchantsId='+n.id+'">'+
				'<img src="'+n.logo+'" onerror="javascript:this.src=\''+errorImg+'\'"/></a>';
	    	});
	    	$("#merchTemplateDiv").html(merHtml);
	    }
	});
}

/**获得头条信息*/
function getHeadInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"indexController/headlinesList.do",//
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
	    	var data = msg.map.data;
	    	//如果状态为真，则做如下赋值操作
	    	var data = msg.map.data;
	    	var href=activePath+"commodityController/toCommodityDetail.do";
	    	var row=null;
	    	$("#headerlineTemp").show();
	    	$.each(data, function(i, n){
    			row = $("#headerlineTemp").clone();	
    			row.find("a").attr("href",href);
    			row.find("a").text(n.name);
	    		row.appendTo("#headerlineParent");//添加到模板的容器中
	    		/*if(i%2==0){
	    			row = "<li> <div>";	
	    			row = row+"<p>"+n.name+"</p>"
	    		}
		    	if(i%2==1){
		    		row = row+"<p>"+n.name+"</p>"
		    		row = row+"</div></li>";
		    	}*/
	    	});
	    	$("#headerlineTemp").hide();
	    	/*$("#demo1").html("<ul>"+row+"</ul>");
	    	var speed=100
		    demo2.innerHTML=demo1.innerHTML
		   function Marquee(){
			   if(demo2.offsetTop-demo.scrollTop<=0)
				   demo.scrollTop-=demo1.offsetHeight
			   else{
				   demo.scrollTop++
			   }
		   }
		   var MyMar=setInterval(Marquee,speed);
		   demo.onmouseover=function() {clearInterval(MyMar)}
		   demo.onmouseout=function() {MyMar=setInterval(Marquee,speed)}*/
	    }
	});
}

/**获得商品推荐信息*/
function getCommodity(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"commodityController/recommendList.do",//
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
	    	//如果状态为真，则做如下赋值操作
	    	$("#commodityTemplate").show();
	    	var data = msg.map.list;
	    	var href=activePath+"commodityController/toCommodityDetail.do";
	    	$.each(data, function(i, n){
    			var row = $("#commodityTemplate").clone();	    	
		    	row.find("#commodityHref").attr("href",href+"?commodityId="+n.id);
		    	row.find("#commodityImg").attr("src",basePath+n.imgPath);
		    	row.find("#commodityName").html(n.name);
		    	row.find("#fare").html(n.fare);
		    	row.find("#price").html(n.realPrice);
		    	/*row.find(".bl_oprice").html(n.price);*/
		    	row.find("#shopTime").html(n.productionDate);
		    	row.find("#shopName").html(n.manufacturers);
		    	/*row.find(".bl_price").html(n.realPrice);
		    	row.find(".bl_oprice").html(n.price);
		    	row.find(".bl_time").html(n.productionDate);
		    	row.find(".bl_mall").html(n.manufacturers);*/
		    	
		    	row.appendTo("#commodityParent");//添加到模板的容器中
	    	});
	    	 $("#commodityTemplate").hide();
	    }
	});
}

/*借款图片点击进入*/
$("#intoLoan").click(function(){
	location.href=activePath+"loanApplicationController/toApplyLoan.do";
	/*$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"loanApplicationController/findLoanApplicationDataBylast.do",//
	    data: {},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	var applySts=null;
	    	var id = null;
	    	var applyNo=null;
	    	if(msg.success){
	    		if(msg.attributes!=null){
	    			applySts=msg.attributes.applySts;
	    			id=msg.attributes.id;
	    			applyNo = msg.attributes.applyNo;
	    			var href=null;
	    			if(applySts==0){
			    		showAlert("未放款","未放款，请耐心等待~~~","success","查看详情","取消",href);
			    	}
			    	else if(applySts==1){
			    		showAlert("有未还借款","有未还借款，请将正在进行的借款还上，才可进行新的借款申请~~","fail","马上还款","取消",href);
			    	}
			    	else if(applySts==2){
			    		showAlert("借款逾期","借款已逾期，请亲速速还贷，以免影响您的个人信用额度~~~","fail","马上还款","取消",href);
			    	}
			    	else if(applySts==3){
			    		showAlert("不能借款","由于您在本平台存在坏账，所以不能进行任何的借款~~~","fail","查看详情","取消");
			    	}
			    	else{
			    		location.href=activePath+"loanApplicationController/toApplyLoan.do";
			    	}
	    		}
	    	}
	    	else{
	    		
	    	}
	    }
	});*/
});



/** 页面加载完成后直接加载方法 获得对应的数据 */
$(function(){
	//1.获得轮播图
	getBannerList();
	//2.获得农资类型与合作商信息
	getMediAndSort();
	//3.获得头条信息
	getHeadInfo();
	//4.获得推荐商品信息
	getCommodity();
});

