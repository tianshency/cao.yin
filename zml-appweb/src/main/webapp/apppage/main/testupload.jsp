<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>测试文件上传</title>
</head>
<body>
	<form action="${ctx}/uploadController/uploadReleaseFile.do"  method="POST" enctype="multipart/form-data">
	    <input type="text" name="docDir" value="release_territory">
		<input type="file" name="imageFile">
		<input type="submit">
	</form>
</body>
<script type="text/javascript">
	/* function release(){
				var title=$("textarea[name='title']").val();
				if(!title){
					layer.msg('请填写标题！',{icon:2});
					return;
				}
				var content=$("textarea[name='content']").val();
				if(!content){
					layer.msg('请填写内容！',{icon:2});
					return;
				}
				var content=$("input[name='temp_end_date']").val();
				if(!content){
					layer.msg('请选择结束时间！',{icon:2});
					return;
				}else{
					$("#endDate").val(content);
				}
				var file=$("#file_input").val();
				if(!file){
					layer.msg('请选择封面图片！！',{icon:2});
					return;
				}
				var items = new Array();
				var item = new Object();
				var details = new Array();
				item.type=$('#itemtype').val();
				item.title=$('#voteTitle').val();
				$('.vote-main li').each(function(i,val){
					var detail = new Object();
					detail.title = $(val).find('textarea').val();
					detail.url = $(val).find('#url').val();
					details.push(detail);
				});
				item.details = details;
				items.push(item);
				var jsonStr = JSON.stringify(items);
				$('#json').val(jsonStr);
				//var form=$('#voteForm').submit();
				var form=$('#voteForm');
				form.ajaxSubmit({
		    		url: '../../../app/main/headline/saveVote.do',
		    		type: "POST",
		    		dataType: "json",
					success: function(data) {
						if(data.success){
							//layer.msg('信息保存成功！',{icon:1});
							//window.location.reload(); 
							parent.location.href="../../../app/main/headline/manageHeadlines.do";
						}else{
							layer.msg(data.content,{icon:2});
						}
		    		},
		    		error: function() {}
				});
			}
	*/
</script>
</html>