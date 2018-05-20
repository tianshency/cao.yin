<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlLoanBlackListDocumentBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlLoanBlackListDocumentBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlLoanBlackListDocumentBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlLoanBlackListDocument_table_template tr").clone();
	 	 $("#add_zmlLoanBlackListDocument_table").append(tr);
	 	 resetTrNum('add_zmlLoanBlackListDocument_table');
	 	 return false;
    });  
	$('#delZmlLoanBlackListDocumentBtn').bind('click', function(){   
      	$("#add_zmlLoanBlackListDocument_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlLoanBlackListDocument_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlLoanBlackListDocument_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlLoanBlackListDocumentBtn" href="#">添加</a> <a id="delZmlLoanBlackListDocumentBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlLoanBlackListDocument_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						用户
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						标识
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						图片路径
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						文件路径
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						描述
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						文档序号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						档案编号
				  </td>
	</tr>
	<tbody id="add_zmlLoanBlackListDocument_table">
	<c:if test="${fn:length(zmlLoanBlackListDocumentList)  > 0 }">
		<c:forEach items="${zmlLoanBlackListDocumentList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlLoanBlackListDocumentList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlLoanBlackListDocumentList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlLoanBlackListDocumentList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlLoanBlackListDocumentList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlLoanBlackListDocumentList[${stuts.index }].blId" type="hidden" value="${poVal.blId }"/>
				   <td align="left">
							<t:dictSelect field="zmlLoanBlackListDocumentList[${stuts.index }].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${poVal.userId }" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlLoanBlackListDocumentList[${stuts.index }].fileFlag" type="list"
										typeGroupCode="file_flag" defaultVal="${poVal.fileFlag }" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[${stuts.index }].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.imagePath }">
					  <label class="Validform_label" style="display: none;">图片路径</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[${stuts.index }].filePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.filePath }">
					  <label class="Validform_label" style="display: none;">文件路径</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[${stuts.index }].details" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.details }">
					  <label class="Validform_label" style="display: none;">描述</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[${stuts.index }].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.seqNo }">
					  <label class="Validform_label" style="display: none;">文档序号</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[${stuts.index }].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.archivesNo }">
					  <label class="Validform_label" style="display: none;">档案编号</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
