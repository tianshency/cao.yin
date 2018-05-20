$(function(){
	$("#imgUploadDiv").hide();
	$("#addContractInfoDiv").hide();
	$("#contractList").hide();
	//初始化期限
	findProductInfo();
	//提交申请基本信息
	$("#saveApply").click(function(){
		submitLoadApplyInfo();
	})
	//提交联系人信息
	$("#save_btn").click(function(){
		submitContractInfo();
	})
	
	//填写金额试算
	$("#amount").change(function(){
		calcRateInfo();
	});
	//填写期限试算
	$("#productId").change(function(){
		calcRateInfo();
	});
	//查询文档信息
	findDocInfo();
	//查找联系人信息
	findContractInfo();
	//点击获取难码
	$("#inputCheckCode").click(function(){
		getCheckCode(this);
	});
})

/**
 * 发送验证码
 */
var checkCodeServer=null;
function getCheckCode(obj){
	var tel=$("#phone").val();
	if(undefined==tel||tel==null||""==tel){
		mui.toast('请输入手机号~',{ duration:'long', type:'div' }); 
		return;
	}
	
	var mobile = /^(((1[3-9]{1}[0-9]{1})|(15[0-9]{1}))+\d{8})$/; 
	if(!mobile.test(tel)){
		 mui.toast('手机号格式不正确~');
		 return;
	}
	
	$(obj).attr("disabled" ,"true");
	$("#tel").attr("readonly",true);
	time(obj);
	
	$.ajax({
		async:false,
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"zmlCommonController/sendSmsCode.do",//要访问的后台地址
	    data: {phone:tel,smsType:"1"},//要发送的数据
	    complete :function(){$("#load").hide();},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	alert(msg.content);
	    	//checkCodeServer=msg.data;
	    }
	});
	
}

//每隔60秒取一次验证码
var wait = 60;
function time(btn) {
    if (wait == 0) {
    	$(btn).attr("disabled", false);
        $(btn).html("获取验证码");
        wait = 60;
        $("#tel").attr("readonly",false);
    } else {
    	$(btn).attr("disabled", true);
    	$(btn).html(wait + "秒后重发");
        wait--;
        setTimeout(function () {
            time(btn);
        },
        1000)
    }
}

/**
 * 查询期限信息 
 */
function findProductInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/loanApplicationController/findProductInfo.do",//
	    data: {},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	var data = msg.map.data;
	    	var row = $("#productId");
	    	var str ="";
	    	if(productId==null||productId==""){
    			str=str+"<option value='' selected>--请选择--</option>"
    		}
	    	$.each(data, function(i, n){
	    		if(productId==n.id){
	    			str=str+"<option value='"+n.id+","+n.interestRate+"' selected>"+n.proName+"</option>"
	    		}
	    		else{
	    			str=str+"<option value='"+n.id+","+n.interestRate+"'>"+n.proName+"</option>"
	    		}
	    	});
	    	row.html(str);
	    }
	});
}


/**
 * 试算接口
 */
function calcRateInfo(){
	var product = $("#productId").val();
	var amount = $("#amount").val();
	if(product==null||product==""||amount==null||amount=="")
		return;
	var arr = product.split(",");
	$("#interestRate").val(arr[1]);
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/loanApplicationController/loanCalc.do",//
	    data: {loanAmount:amount,productId:arr[0],startDate:null},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	var data = msg.map.data;
	    	if(data!=null && data.length>0){
		    	$("#interest").val(data[0].interest);
		    	if(product!=null && product.length>0){
		    		var proArr = product.split(",");
		    		$("#interestRate").val(proArr[1]);
		    	}
	    	}
	    	
	    }
	});
}

var backCheckCode=null;//后台返回验证码

/**
 * 校验必输信息
 */
function validateInfo(){
	var phone = $("#phone").val();
	var amount = $("#amount").val();
	var userNotice = $("#userNotice");
	var ds_host = $("#ds_host").val();
	var productId = $("#productId").val();
	//身份证信息
	var sfz_img = $("#sfz_img").attr("src");
	var sfb_img = $("#sfb_img").attr("src");
	var sf_img = $("#sf_img").attr("src");
	
	if (phone!= ""){  
        var p1 = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;  
        var me = false;  
        if (p1.test(phone)) me=true;  
        if (!me){  
        	$("#phone").value='';  
            mui.toast('请输入正确的手机号码~'); 
            $("#phone").focus();  
            return false;  
        }
	}
	
	if(amount==null || amount==""){
		 mui.toast('请输入借款金额~'); 
		 $("#amount").focus();  
         return false;  
	}
	if(ds_host==null || ds_host==""){
		 mui.toast('请输入短信验证码~'); 
		 $("#ds_host").focus();  
		 return false;  
	}
	if(backCheckCode!=ds_host && backCheckCode==null || backCheckCode==""){
		mui.toast('短信验证码错误~'); 
		$("#ds_host").focus();  
		return false;  
	}
	if(productId==null || productId==""){
		mui.toast('请选择利率信息~'); 
		$("#productId").focus();  
		return false;  
	}
	
	if(sfz_img.indexOf("idc_z.jpg")>0){
		mui.toast('请上传身份证正面照片~'); 
		return false;
	}
	if(sfz_img.indexOf("idc_b.jpg")>0){
		mui.toast('请上传身份证件反面照片~'); 
		return false;
	}
	if(sfz_img.indexOf("idc_q.jpg")>0){
		mui.toast('请上传个人手持身份证件照片~'); 
		return false;
	}
	//必上传的图片
	return true;
}

/**
 * 提交个人借款申请
 */
function submitLoadApplyInfo(){
	if(!validateInfo()){
		return;
	}
	var phone = $("#phone").val();
	var amount = $("#amount").val();
	var recommendUser = $("#recommendUser").val();
	var userNotice = $("#userNotice");
	productId = $("#productId").val();
	if(productId!=null && productId.length>0){
		var proArr = productId.split(",");
		productId =proArr[0]; 
	}
	
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/loanApplicationController/doAdd.do",//
	    data: {phone:phone,amount:amount,recommendUser:recommendUser,id:applyId,token:1,productId:productId},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		swal({
					title: "小提示!",
					text: "贷款申请保存失败，请稍候重试",
					timer: 5000
				});
	    	}
	    	else{
	    		swal({
					title: "小提示!",
					text: "贷款申请保存成功！",
					timer: 5000
				}
	    		,
	    		function(){
	    			location.href=activePath+"/loanApplicationController/toApplyLoan.do";
	    		});
	    	}
	    }
	});
}

var  editRow=null;
//编辑联系人
function editContractInfo(even){
	var data = even.data.data;
	editRow =  even.data.row;
	$("#r_phone").val(data.phone);
	$("#name").val(data.name);
	$("#relation").val(data.relation);
	$("#r_id").val(data.id);
}
//删除联系人
function delContractInfo(even){
	var row = even.data.row;
	var id = even.data.id;
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/loanApplicationController/doDelContacts.do",
	    data: {id:id},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	  $(row).remove();//rowIndex属性为tr的索引值，从0开始  
	    	  alert("删除成功！");
	    	  $("#r_phone").val("");
	    	  $("#name").val("");
	    	  $("#relation").val("");
	    	  $("#r_id").val("");
	    }
	});
}

/**
 * 保存联系人信息
 */
function submitContractInfo(){
	var phone = $("#r_phone").val();
	var name = $("#name").val();
	var relation = $("#relation").val();
	var r_id= $("#r_id").val();
	var url=activePath+"/loanApplicationController.do?doAddContacts";
	if(r_id!=null&&r_id!=undefined&&r_id!=""){
		url = activePath+"/loanApplicationController.do?doUpdateContacts";
	}
	$.ajax({
	    type: "post",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url:url,
	    data: {phone:phone,name:name,relation:relation,applyId:applyId,id:r_id,token:123},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为假，则给出提示
	    	if(!msg.success){
	    		
	    	}
	    	else{
	    		var result = JSON.parse(msg.jsonStr);
	    		var data = result.attributes.contacts;
	    		if(r_id==null || r_id==undefined || r_id==""){//增加
		    		var tbody = $("#tbody");
		    		var tr=$("<tr></tr>");
	    	        var td0=$("<td>"+data.name+"</td>");
	    	        var td1=$("<td>"+data.phone+"</td>");
	    	        var td2=$("<td>"+data.relation+"</td>");
	    	        var td3=$("<td><a id='edit'>编辑</a><a id='del'>删除</a></td>");
	    	        td3.find("#edit").bind("click",{data:data,row:tr},editContractInfo);
	    	        td3.find("#del").bind("click",{row:tr,id:data.id},delContractInfo);
	    	        td0.appendTo(tr);
	    	        td1.appendTo(tr);
	    	        td2.appendTo(tr);
	    	        td3.appendTo(tr);
		    		tr.appendTo(tbody);
	    		}
	    		else{//修改
	    			var tds = editRow.find("td");
	    			alert($(tds[0]).html());
	    			$(tds[0]).html(data.name); 
	    			$(tds[1]).html(data.phone);
	    			$(tds[2]).html(data.relation);
	    			$(tds[3]).html("<a id='edit'>编辑</a><a id='del'>删除</a></td>");
	    			$(tds[3]).find("#edit").bind("click",{data:data,row:editRow},editContractInfo);
	    			$(tds[3]).find("#del").bind("click",{row:editRow,id:data.id},delContractInfo);
	    		}
	    		$("#r_phone").val("");
	    		$("#name").val("");
	    		$("#relation").val("");
	    		$("#r_id").val("");
	    	}
	    }
	});
}

/**
 * 查找联系人信息
 */
function findContractInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/loanApplicationController/findContacts.do",
	    data: {applId:applyId,page:1,rows:10},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//如果状态为真，则做如下赋值操作
	    	$("#template").show();
	    	var data = msg.map.data; 
	    	$.each(data, function(i, n){
	    			var row = $("#template").clone();
	    			var tds=row.find("td");
			    	$(tds[0]).html(n.name);
			    	$(tds[1]).html(n.phone);
			    	$(tds[2]).html(n.relation);
            		row.find("#edit").bind("click",{data:n,row:row},editContractInfo);
            		row.find("#del").bind("click",{row:row,id:n.id},delContractInfo);
			    	row.appendTo("#tbody");//添加到模板的容器中
	    	});
	    	$("#template").hide();
	    }
	});
}


/**
 * 查询文档信息
 */
function findDocInfo(){
	$.ajax({
	    type: "get",//使用get方法访问后台
	    dataType: "json",//返回json格式的数据
	    url: activePath+"/loanApplicationController/findApplyDoc.do",
	    data: {applyId:applyId},//要发送的数据
	    complete :function(){},//AJAX请求完成时隐藏loading提示
	    success: function(msg){//msg为返回的数据，在这里做数据绑定
	    	//初始化图片
	    	var sfz=0;
	    	var fzh=0;
	    	var tdb=0;
	    	var hkb=0;
	    	var data = msg.map.data; 
	    	$.each(data, function(i, n){
	    		if(n.docType==1){
	    			if(sfz==0){
	    				$("#sfz_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(sfz==1){
	    				$("#sfb_img").attr("src",basePath+n.imagePath);
	    			}
	    			else{
	    				$("#sf_img").attr("src",basePath+n.imagePath);
	    			}
	    			sfz++;
	    		}
	    		else if(n.docType==2){
	    			if(tdb==0){
	    				$("#td1_img").attr("src",basePath+n.imagePath);
	    			}
	    			else{
	    				$("#td2_img").attr("src",basePath+n.imagePath);
	    			}
	    			tdb++;
	    		}
	    		else if(n.docType==3){
	    			
	    		}
	    		else if(n.docType==4){
	    			if(fzh==0){
	    				$("#fchz_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(fzh==1){
	    				$("#fchz1_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(fzh==2){
	    				$("#fchz2_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(fzh==3){
	    				$("#fchz3_img").attr("src",basePath+n.imagePath);
	    			}
	    			else{
	    				$("#fchz4_img").attr("src",basePath+n.imagePath);
	    			}
	    			fzh++;
	    		}
	    		else if(n.docType==5){
	    			
	    		}
	    		else if(n.docType==6){
	    			if(hkb==0){
	    				$("#hk1_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(hkb==1){
	    				$("#hk2_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(hkb==2){
	    				$("#fchz2_img").attr("src",basePath+n.imagePath);
	    			}
	    			else if(hkb==3){
	    				$("#hk3_img").attr("src",basePath+n.imagePath);
	    			}
	    			hkb++;
	    		}
	    	});
	    }
	});
}
//上一步
function lastStep(){
	if(step==3){
		$("#baseInfoDiv").hide();
		$("#imgUploadDiv").show();
		$("#addContractInfoDiv").hide();
		$("#contractList").hide();
		step--;
		$("#nextbtn").html("下一步");
	}
	else if(step==2){
		$("#baseInfoDiv").show();
		$("#imgUploadDiv").hide();
		$("#addContractInfoDiv").hide();
		$("#contractList").hide();
		step--;
	}
}
//下一步
function nextStep(){
	if(step==1){
		$("#baseInfoDiv").hide();
		$("#imgUploadDiv").show();
		$("#addContractInfoDiv").hide();
		$("#contractList").hide();
		step++;

	}
	else if(step==2){
		$("#baseInfoDiv").hide();
		$("#imgUploadDiv").hide();
		$("#addContractInfoDiv").show();
		$("#contractList").show();
		step++;
		$("#nextbtn").html("保存");
	}
	else if(step==3){
		var btnArray = ['否', '是'];
		mui.confirm('您确认提交贷款申请吗？', '确认框', btnArray, function(e) {
			if (e.index == 1) {
				submitLoadApplyInfo();
			} else {
			}
		})
	}
}



/*$('#openAccountForm').validate({
	errorElement: 'span',
	errorClass: 'help-inline',
	focusInvalid: false,
	rules: {
		name:{
			required: true,
		},
		tel:{
			required: true,
			isMobile: true
		},
		email:{
			required: true,
			email:true
		},
		qq:{
			required: true
		},
		openNotice:{
			required: true
		},
		checkCode:{
			//required: true,
			validCheckCode:true,
			requildCheckCode:true
		},
		sfz:{
			required: true,
		},
		bankOfDepositName:{
			required: true,
		}
	},
	messages: {
		name: {
			required: "此项为必填项！"
		},
		tel: {
			required: "此项为必填项！"
		},
		email: {
			required: "此项为必填项！",
			email: "请输入正确格式的电子邮件"
		},
		qq: {
			required: "此项为必填项！"
		},
		openNotice:{
			required: "此项为必填项！"
		},
		checkCode:{
			required: "此项为必填项！"
		},
		sfz:{
			required: "此项为必填项"
		},
		bankOfDepositName:{
			required: "此项为必填项",
		}
	},
	invalidHandler: function (event, validator) { //display error alert on form submit   
		$('.alert-error', $('.login-form')).show();
	},
	highlight: function (e) {
		$(e).closest('.control-group').removeClass('info').addClass('error');
	},
	success: function (e) {
		$(e).closest('.control-group').removeClass('error').addClass('info');
		$(e).remove();
	},
	errorPlacement: function (error, element) {
		if(element.is(':checkbox') || element.is(':radio')) {
			var controls = element.closest('.controls');
			if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
			else error.insertAfter(element.nextAll('.lbl').eq(0));
		} 
		else if(element.is('.chzn-select')) {
			error.insertAfter(element.nextAll('[class*="chzn-container"]').eq(0));
		}
		else error.insertAfter(element);

	},
	submitHandler: function (form) {
	},
	invalidHandler: function (form) {
	}
});
*/