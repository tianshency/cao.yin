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
	<div class="examine-classify" id="docTypeDiv">
	${html1 }
	</div>
	${html2 }
  	<script type="text/javascript">
  		${scriptSb }
  		
  		function commitData(docType){
  			$("#from" + docType);
  			//身份证时
  			if(docType == '1'){
  				
  			}else if(docType == '4'){//土地本
  				
  			}else if(docType == '5'){//房产证
  				
  			}else if(docType == '6'){//房子实体照片
  				
  			}else if(docType == '7'){//驾驶证
  				
  			}else if(docType == '8'){//户口本
  				
  			}else if(docType == '9'){//支付宝 或 微信实名认证截图
  				
  			}
  		}
	</script>
</body>
</html>