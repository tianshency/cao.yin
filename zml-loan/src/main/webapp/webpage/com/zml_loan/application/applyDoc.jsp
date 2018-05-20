<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	<head>
	<meta charset="UTF-8">
  	<link rel="stylesheet" type="text/css" href="${webRoot }/plug-in/album/css/jquery.ad-gallery.css">
  	<link rel="stylesheet" type="text/css" href="${webRoot }/plug-in/album/css/imgSHOW.css"/>
 	<script src="${webRoot }/plug-in/album/js/jquery-1.11.3.min.js"></script>
  	<script type="text/javascript" src="${webRoot }/plug-in/album/js/jquery.ad-gallery.js?rand=995"></script>
  	<script type="text/javascript">

  	$(function() {
//  	$('img.image1').data('ad-desc', '啥也没有啊');
// 		$('img.image1').data('ad-title', 'dfdfdfdfdf');
//  	$('img.image4').data('ad-desc', '这是一张图片！');
//  	$('img.image5').data('ad-desc', '没错！是图片！');
    	var galleries = $('.ad-gallery').adGallery();
    	$('#switch-effect').change(
      	function() {
        	galleries[0].settings.effect = $(this).val();
       	 	return false;
      	}
    	);

    	$('#toggle-slideshow').click(
      	function() {
      	  galleries[0].slideshow.toggle();
      	  return false;
     	 }
   	 );
 	 });
  </script>
  <title>轮播</title>
  <style type="text/css">
  ${styleContainer}
  ${styleGallery}
  </style>
  </head>
  <body>
    <input type="hidden" id="applyId" value="${applyId }">
    <input type="hidden" id="userId" value="${userId }">
	<div class="examine-classify" id="docTypeDiv">
	${html1 }
	</div>
	${html2 }
  	<script type="text/javascript">
  		${scriptSb }
  		
  		function commitData(docType){
  			var url = null;
  			var data=null;
  			var formId = "form";
  			//身份证时
  			if(docType == '1'){
  				formId += "1";
  				url="${webRoot }/zmlLoanApplicationController.do?doIdNumberUpdate";
  				var identificationNumber = $("#identificationNumber").val();
  				if(identificationNumber == ''){
  					alert("身份证号必须入力！");
  					return false;
  				}
  			}else if(docType == '2'){//土地本
  				formId += "2";
  				url="${webRoot }/zmlLoanApplicationController.do?doApproveLan";
  			}else if(docType == '5'){//房产证
  				$("#approveDiv5").append(" <b>Hello world 555!</b>");
  			}else if(docType == '6'){//房子实体照片
  				$("#approveDiv6").append(" <b>Hello world 666!</b>");
  			}else if(docType == '7'){//驾驶证
  				$("#approveDiv7").append(" <b>Hello world 777!</b>");
  			}else if(docType == '8'){//户口本
  				$("#approveDiv8").append(" <b>Hello world 888!</b>");
  			}else if(docType == '9'){//支付宝 或 微信实名认证截图
  				$("#approveDiv9").append(" <b>Hello world 999!</b>");
  			}
  			$.ajax({
  			    type: "post",//使用get方法访问后台
  			    dataType: "json",//返回json格式的数据
  			    url: url,//
  			    data: $('#' +formId).serialize(),// 你的formid 要发送的数据
  			    //data: $('#form1').serialize(),// 你的formid 要发送的数据
  			    complete :function(){},//AJAX请求完成时隐藏loading提示
  			    success: function(data){//msg为返回的数据，在这里做数据绑定
  			    	//如果状态为假，则给出提示
  			    	if(!data.success){
                        alert("提交成功！");
                        //将土地ID 值存入 页面
                        if(docType == '2'){
                        	$("#id2").val(data.msg);
                        }
  			    	}
  			    	else{
  			    		alert(data.msg);
  			    	}
  			    }
  			});
  		}
  		
  		function insertControls(docType, userId, applyId){
  			//alert(docType);
  			//alert("insertControls==" + docType);
  			//$("#from" + docType);
  			//身份证时
  			var html = "<ul class='examine-choose'>";
  			if(docType == '1'){
  				html +="<li><input type='hidden' name='id' id='id' value='"+userId+"'/></li>";
  				html +="<li><span>姓名:</span><input type='text' name='realName' id='realName'/></li>";
  				html +="<li><span>身份证号:</span><input type='text' name='identificationNumber' id='identificationNumber'/></li>";
  				html +="<li><span>住址:</span><input type='text' name='address' id='address'/></li>";
  				html +="<li><span>发证机关:</span><input type='text' name='giveOrg' id='giveOrg'/></li>";
  				html +="<li><span>证件有效期:</span><input type='text' name='cardEndDate' id='cardEndDate'/></li>";
  				html+="</ul>";
  				$("#approveDiv1 h2").before(html);
  			}else if(docType == '2'){//土地本
  				html +="<li><input type='hidden' name='id' id='id2' value=''/></li>";
  				html +="<li><input type='hidden' name='userId' id='userId' value='"+userId+"'/></li>";
  				html +="<li><input type='hidden' name='applId' id='applId' value='"+applyId+"'/></li>";
  				html +="<li><span>土地类型:</span><input type='text' name='lanType' id='lanType'/></li>";
  				html +="<li><span>总面积:</span><input type='text' name='totalArea' id='totalArea'/></li>";
  				html +="<li><span>面积单位:</span><input type='text' name='areaUnit' id='areaUnit'/></li>";
  				html +="<li><span>位置:</span><input type='text' name='position' id='position'/></li>";
  				html +="<li><span>备注:</span><input type='text' name='remarks' id='remarks'/></li>";
  				html+="</ul>";
  				$("#approveDiv2 h2").before(html);
  			}else if(docType == '3'){//房产证
  				html +="<li><input type='hidden' name='id' id='id3' value=''/></li>";
  				html +="<li><input type='hidden' name='userId' id='userId' value='"+userId+"'/></li>";
  				html +="<li><input type='hidden' name='applId' id='applId' value='"+applyId+"'/></li>";
  				$("#approveDiv3").before(" <b>Hello world 555!</b>");
  			}else if(docType == '4'){//房子实体照片
  				html +="<li><input type='hidden' name='id' id='id4' value=''/></li>";
  				html +="<li><input type='hidden' name='userId' id='userId' value='"+userId+"'/></li>";
  				html +="<li><input type='hidden' name='applId' id='applId' value='"+applyId+"'/></li>";
  				$("#approveDiv4").before(" <b>Hello world 666!</b>");
  			}else if(docType == '5'){//驾驶证
  				html +="<li><input type='hidden' name='id' id='id5' value=''/></li>";
  				html +="<li><input type='hidden' name='userId' id='userId' value='"+userId+"'/></li>";
  				html +="<li><input type='hidden' name='applId' id='applId' value='"+applyId+"'/></li>";
  				$("#approveDiv5").before(" <b>Hello world 777!</b>");
  			}else if(docType == '6'){//户口本
  				html +="<li><input type='hidden' name='id' id='id6' value=''/></li>";
  				html +="<li><input type='hidden' name='userId' id='userId' value='"+userId+"'/></li>";
  				html +="<li><input type='hidden' name='applId' id='applId' value='"+applyId+"'/></li>";
  				$("#approveDiv6").before(" <b>Hello world 888!</b>");
  			}else if(docType == '7'){//支付宝 或 微信实名认证截图
  				html +="<li><input type='hidden' name='id' id='id7' value=''/></li>";
  				html +="<li><input type='hidden' name='userId' id='userId' value='"+userId+"'/></li>";
  				html +="<li><input type='hidden' name='applId' id='applId' value='"+applyId+"'/></li>";
  				$("#approveDiv7").before(" <b>Hello world 999!</b>");
  			}
  		}
	</script>
</body>
</html>