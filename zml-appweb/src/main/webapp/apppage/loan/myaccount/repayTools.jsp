<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>现在还款</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<link rel="stylesheet" href="${ctxContents }css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${ctxContents }css/Loan.css"/>
		<link rel="stylesheet" type="text/css" href="${ctxContents }css/media.css"/>
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents }js/alerttip/sweet-alert.js"></script>
  		<link rel="stylesheet" href="${ctxContents }css/alertTip/sweet-alert.css">	
		<style type="text/css">
			html,body{
				min-height: 100%;
			}
			.invoiceCompany-content li>a{
				display: inline-block;
				width: 100%;
			}
			.mui-popover .mui-popover-arrow{
				display: none;
			}
			.sweet-overlay{
				background-color: rgb(0, 0, 0);
			}
			.sweet-alert p{
				font-size: 18px;
				color: #f00;
			}
			.sweet-alert button{
				background:#4c447d!important;
			}
		</style>
	</head>
	<body class="Invoice-body">
		<section class="invoiceCompany-content">
			<p class="Invoice-loan-p">
				还款金额:&nbsp;<input type="text" id="contractBalance" value="${contractEntity.contractBalance }" maxlength="9"/>&nbsp;元
				<a class="invoice-loan-prev" href="Repayment.html">返回</a>
			</p>
			<div class="invoice-loan-href">
				
			</div>
			<ul>
				<li class="show-li invoicePersonal-li1">
					<i class="Company-weixin"></i>
					<span>微信</span>
				</li>
				<li class="show-li invoicePersonal-li2">
					<i class="Company-zhifubao"></i>
					<span>支付宝</span>
				</li>
				<li class="show-li invoicePersonal-li3">
					<i class="Company-yinhang"></i>
					<span>银行账户</span>
				</li>
			</ul>
			<div id="preview">
    			<img id="imghead" border="0" src="${ctxContents }img/pingzheng.png" onClick="$('#previewImg').click();">
			</div>
			<input type="file" onChange="previewImage(this)" style="display: none;" name="upload" id="previewImg">
						
			<div class="Invoice-loan-btn">
				<input type="button" class="Invoice-loan-input" id="repay" value="提交"/>
			</div>
		</section>
		<div id="weixin-code" class="fixed-code fixed-code-sq mui-popover">
			<div class="fixed-code-img">
				<img src="${ctxContents }img/personal-weixin.jpg"/>
			</div>
		</div>
		<div class="invoice-fixed invoicePersonal">
			<div class="invoicePersonal-main">
				<h2>个人汇款账户</h2>
				<ul>
					<li>
						<span>开户名称：</span>
						<textarea readonly="readonly">刘国杰</textarea>
					</li>
					<li>
						<span>开户行：</span>
						<textarea readonly="readonly">浦发银行北京分行知春路支行</textarea>
					</li>
					<li>
						<span>账号：</span>
						<textarea readonly="readonly">6217920604233770</textarea>
					</li>
				</ul>
				<i></i>
			</div>
		</div>
		<div class="invoice-fixed invoiceWeixin">
			<div class="fixed-code-img">
				<img src="${ctxContents }img/personal-weixin.jpg"/>
				<i></i>
			</div>
		</div>
		<div class="invoice-fixed invoiceZhifubao">
			<div class="invoiceZhifubao-main">
				<textarea readonly="readonly">zhifu@beijingyilian.com</textarea>
				<i></i>
			</div>
		</div>		
		<script src="${ctxContents }js/mui.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxContents }js/load/repay.js" type="text/javascript" charset="utf-8"></script>
		<script src="${ctxContents }js/ajaxfileupload.js" type="text/javascript" charset="utf-8"></script>
		<script type="text/javascript">
			var contractId="${contractEntity.id}";
			var repayChannel=null;
			$(function(){
				$(".show-li").click(function(){
					swal({
					title: "小提示!",
					text: " 转账完成后，请截图或拍照并上传凭证！",
					timer: 5000
					});
				});
				$(".invoicePersonal-li2").click(function(){
					$(".invoiceZhifubao").show();
					repayChannel= 2;
				});
				$(".invoiceZhifubao i").click(function(){
					$(".invoiceZhifubao").hide();
				})
				$(".invoicePersonal-li3").click(function(){
					$(".invoicePersonal").show();
					repayChannel= 1;
				});
				$(".invoicePersonal i").click(function(){
					$(".invoicePersonal").hide();
				});
				$(".invoicePersonal-li1").click(function(){
					$(".invoiceWeixin").show();
					repayChannel= 3;
				});
				$(".invoiceWeixin i").click(function(){
					$(".invoiceWeixin").hide();
				})					
			})
		</script>
<script>
 //图片上传预览    IE是用了滤镜。
    function previewImage(file) {
        var MAXWIDTH = 90;
        var MAXHEIGHT = 90;
        var div = document.getElementById('preview');
        if (file.files && file.files[0]) {
            div.innerHTML = '<img id=imghead onclick=$("#previewImg").click()>';
            var img = document.getElementById('imghead');
            img.onload = function() {
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width = rect.width;
                img.height = rect.height;
                //                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top + 'px';
            }
            var reader = new FileReader();
            reader.onload = function(evt) {
                img.src = evt.target.result;
            }
            reader.readAsDataURL(file.files[0]);
        } else //兼容IE
        {
            var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead>';
            var img = document.getElementById('imghead');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
            div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
        }
    }
    function clacImgZoomParam(maxWidth, maxHeight, width, height) {
        var param = {
            top: 0,
            left: 0,
            width: width,
            height: height
        };
        if (width > maxWidth || height > maxHeight) {
            rateWidth = width / maxWidth;
            rateHeight = height / maxHeight;

            if (rateWidth > rateHeight) {
                param.width = maxWidth;
                param.height = Math.round(height / rateWidth);
            } else {
                param.width = Math.round(width / rateHeight);
                param.height = maxHeight;
            }
        }
        param.left = Math.round((maxWidth - param.width) / 2);
        param.top = Math.round((maxHeight - param.height) / 2);
        return param;
    }
</script>
<script type="text/javascript">
			$('.Invoice-loan-p input').keyup(function(event) {
				if(!/^-?\d+\.?\d{0,2}$/.test(this.value)){
					alert('请输入数字,只能保留两位小数！');
					this.value='';
				}
				var txtVal=$(this).val();
				if (txtVal==='') {
	  				$('.Invoice-loan-input').attr('disabled',true);   
				}
				else{
  					$('.Invoice-loan-input').attr('disabled',false);  	
				}
			});
		</script>
	</body>
	
</html>
