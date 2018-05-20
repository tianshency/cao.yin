<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!doctype html>
<html lang="en" class="feedback">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>问题反馈000</title>
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="${ctxContents}css/feedback.css" />
	</head>

	<body>
		<div class="question-feedback">
			<div class="mui-content-padded">
				<div class="mui-inline">问题和意见</div>
				<a class="mui-pull-right mui-inline" href="#popover">
					快捷输入
					<span class="mui-icon mui-icon-arrowdown"></span>
				</a>
				<!--快捷输入具体内容，开发者可自己替换常用语-->
				<div id="popover" class="mui-popover">
					<div class="mui-popover-arrow"></div>
					<div class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<ul class="mui-table-view">
								<!--仅流应用环境下显示-->
								<li class="mui-table-view-cell stream">
									<a href="#">桌面快捷方式创建失败</a>
								</li>
								<li class="mui-table-view-cell"><a href="#">商家回复信息太慢！</a></li>
								<li class="mui-table-view-cell"><a href="#">网页加载缓慢！</a></li>
								<li class="mui-table-view-cell"><a href="#">产品实际价格与标价不符！</a></li>
								<li class="mui-table-view-cell"><a href="#">发货太慢了！</a></li>
							</ul>
						</div>
					</div>

				</div>
			</div>
			<div class="row mui-input-row">
				<textarea id='content'  class="mui-input-clear question" placeholder="请详细描述你的问题和意见..."></textarea>
			</div>
			<p>图片(选填,提供问题截图,总大小10M以下)</p>
			<!--<div id='image-list' class="row image-list"></div>-->
           	<!--照片添加  -->
   			<div class="feedback-img">
        		<div class="z_photo">
            		<div class="z_file">
                		<input type="file" id="upload1" style="display: none;" name="upload" onchange="document.getElementById('headImg1').src=getFullPath(this);" >
        				<img src="${ctxContents}img/feedback.png" name="showImg" id="headImg1" onclick="clickFile(this,'upload1');">
            		</div>
            		<div class="z_file">
                		<input type="file" id="upload2" name="upload" style="display: none;" onchange="document.getElementById('headImg2').src=getFullPath(this);" >
        				<img src="${ctxContents}img/feedback.png" name="showImg" id="headImg2" onclick="clickFile(this,'upload2');">
            		</div>
            		<div class="z_file">
                		<input type="file" id="upload3" name="upload" style="display: none;" onchange="document.getElementById('headImg3').src=getFullPath(this);" >
        				<img src="${ctxContents}img/feedback.png" name="showImg" id="headImg3" onclick="clickFile(this,'upload3');">
            		</div>
        		</div>
        		<div class="z_mask">
            		<div class="z_alert">
                		<p>确定要删除这张图片吗？</p>
                		<p>
                   		 	<span class="z_cancel">取消</span>
                    		<span class="z_sure">确定</span>
                		</p>
            		</div>
        		</div>
    		</div>			
			<p>QQ/邮箱</p>
			<div class="mui-input-row">
				<input id='contact' type="text" class="mui-input-clear contact" placeholder="(选填,方便我们联系你 )" />
			</div>
			<p>联系电话</p>
			<div class="mui-input-row">
				<input type="text" id="phone" class="mui-input-clear contact" maxlength="11" placeholder="(选填,方便我们联系你 )" />
			</div>			
		</div>
		
		<div class="footer_info">
			<button type="button" class="mui-btn mui-btn-primary" id="submitBtn">提交</button>
			<button type="button" class="mui-btn mui-btn-primary mui-btn-outlined" onclick="window.history.go(-1);">返回</button>
		</div>
		
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script src="${ctxContents}js/ajaxfileuploads.js"></script>
		<script type="text/javascript">
		mui.init();
		mui('.mui-scroll-wrapper').scroll();
		
		var staticImgSrc="${ctxContents}img/feedback.png";
		function clickFile(obj,fileId){
			if($(obj).attr("src")!=staticImgSrc){
				  var mask = document.getElementsByClassName("z_mask")[0];
		          var cancel = document.getElementsByClassName("z_cancel")[0];
		          var sure = document.getElementsByClassName("z_sure")[0];
		          mask.style.display = "block";
                  cancel.onclick = function() {
                      mask.style.display = "none";
                  };
                  sure.onclick = function() {
                      mask.style.display = "none";
                      $(obj).attr("src",staticImgSrc);
                  };
			}
			else{
				$("#"+fileId).click();
			}
		}
		 
		var showPic="";
	    //上传图片时浏览图片
		function getFullPath(obj) {
			if (obj) {
				if(showPic.indexOf(obj.id) < 0)  showPic=showPic+","+obj.id;
				if (obj.files) {
					return window.URL.createObjectURL(obj.files[0]);
				}
				return obj.value;
			}
		}
	    
		//处理上传文件的顺序
		function dealPicByNo(){
			var arr = showPic.split(",");
			var str = 'upload1,upload2,upload3';
			var strarr = str.split(",");
			var result="";
			for(var j=0;j<strarr.length;j++){
				for(var i=0;i<arr.length;i++){
					if (arr[i]==strarr[j]){
						if(result==""){
							result=arr[i];
						}
						else{
							result=result+","+arr[i];
						}
					}
				}
			}
			return result;
		}

	    
	    //保存意见反馈信息
	    function saveInfo(){
    		var content=$("#content").val();
    		var phone=$("#phone").val();
    		showPic = dealPicByNo();
    		$.ajaxFileUpload({
    			url : "${ctx}feedBackController/addFeedBackInfo.do",
    			data : {content:content,phone:phone,showPic:showPic},
    			type : "post",
    			secureuri : false, //是否启用安全提交,默认为false
    			fileElementId : ["upload1",'upload2','upload3'], //文件选择框的id属性
    			dataType : "json",
    			success : function(data, s) {
    				alert("上传成功！");
    				location.href="${ctx}personalController/toPersonalPage.do";
    			},
    			error : function(data, s, e) {
    				alert("上传失败，请重试！");
    			}
    		});
    	}
		  
	    
	    $(function(){
	    	$("#submitBtn").click(function(){
	    		saveInfo();
	    	})
	    })
        /* //px转换为rem
        (function(doc, win) {
            var docEl = doc.documentElement,
                resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                recalc = function() {
                    var clientWidth = docEl.clientWidth;
                    if (!clientWidth) return;
                    if (clientWidth >= 640) {
                        docEl.style.fontSize = '100px';
                    } else {
                        docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                    }
                };

            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
            doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window); */

      /*   function imgChange(obj1, obj2) {
            //获取点击的文本框
            var file = document.getElementById("file");
            //存放图片的父级元素
            var imgContainer = document.getElementsByClassName(obj1)[0];
            //获取的图片文件
            var fileList = file.files;
            //文本框的父级元素
            var input = document.getElementsByClassName(obj2)[0];
            var imgArr = [];
            //遍历获取到得图片文件
            for (var i = 0; i < fileList.length; i++) {
                var imgUrl = window.URL.createObjectURL(file.files[i]);
                imgArr.push(imgUrl);
                var img = document.createElement("img");
                img.setAttribute("src", imgArr[i]);
                var imgAdd = document.createElement("div");
                imgAdd.setAttribute("class", "z_addImg");
                imgAdd.appendChild(img);
                imgContainer.appendChild(imgAdd);
            };
            imgRemove();
        }; */

        function imgRemove() {
            var imgList = document.getElementsByClassName("z_addImg");
            var mask = document.getElementsByClassName("z_mask")[0];
            var cancel = document.getElementsByClassName("z_cancel")[0];
            var sure = document.getElementsByClassName("z_sure")[0];
            for (var j = 0; j < imgList.length; j++) {
                imgList[j].index = j;
                imgList[j].onclick = function() {
                    var t = this;
                    mask.style.display = "block";
                    cancel.onclick = function() {
                        mask.style.display = "none";
                    };
                    sure.onclick = function() {
                        mask.style.display = "none";
                        t.style.display = "none";
                    };

                }
            };
        };
    </script>		
	</body>

</html>