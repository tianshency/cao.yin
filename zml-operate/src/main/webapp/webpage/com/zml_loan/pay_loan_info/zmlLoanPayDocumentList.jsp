<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlLoanPayDocumentBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlLoanPayDocumentBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlLoanPayDocumentBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlLoanPayDocument_table_template tr").clone();
	 	 $("#add_zmlLoanPayDocument_table").append(tr);
	 	 resetTrNum('add_zmlLoanPayDocument_table');
	 	 return false;
    });  
	$('#delZmlLoanPayDocumentBtn').bind('click', function(){   
      	$("#add_zmlLoanPayDocument_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlLoanPayDocument_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlLoanPayDocument_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlLoanPayDocumentBtn" href="#">添加</a> <a id="delZmlLoanPayDocumentBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlLoanPayDocument_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						用户
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						文档类型
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
						序号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						档案编号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批标识
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批人
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批时间
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批意见
				  </td>
	</tr>
	<tbody id="add_zmlLoanPayDocument_table">
	<c:if test="${fn:length(zmlLoanPayDocumentList)  > 0 }">
		<c:forEach items="${zmlLoanPayDocumentList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlLoanPayDocumentList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlLoanPayDocumentList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlLoanPayDocumentList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlLoanPayDocumentList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlLoanPayDocumentList[${stuts.index }].payId" type="hidden" value="${poVal.payId }"/>
				   <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[${stuts.index }].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${poVal.userId }" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[${stuts.index }].docType" type="list"
										typeGroupCode="pay_doc_type" defaultVal="${poVal.docType }" hasLabel="false"  title="文档类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">文档类型</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[${stuts.index }].fileFlag" type="list"
										typeGroupCode="file_flag" defaultVal="${poVal.fileFlag }" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanPayDocumentList[${stuts.index }].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.imagePath }">
					  <label class="Validform_label" style="display: none;">图片路径</label>
				   </td>
				   <td align="left">
					        <input type="hidden" id="zmlLoanPayDocumentList[${stuts.index }].filePath" name="zmlLoanPayDocumentList[${stuts.index }].filePath"  value="${poVal.filePath }"/>
										<c:if test="${empty poVal.filePath}">
											<a  target="_blank" id="zmlLoanPayDocumentList[${stuts.index }].filePath_href">未上传</a>
										</c:if>
										<c:if test="${!empty poVal.filePath}">
											<a  href="${poVal.filePath}"  target="_blank" id="zmlLoanPayDocumentList[${stuts.index }].filePath_href">下载</a>
										</c:if>
										<br>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(commonUploadDefaultCallBack,'zmlLoanPayDocumentList\\[${stuts.index }\\]\\.filePath')"/> 
					  <label class="Validform_label" style="display: none;">文件路径</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanPayDocumentList[${stuts.index }].details" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.details }">
					  <label class="Validform_label" style="display: none;">描述</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanPayDocumentList[${stuts.index }].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.seqNo }">
					  <label class="Validform_label" style="display: none;">序号</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanPayDocumentList[${stuts.index }].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.archivesNo }">
					  <label class="Validform_label" style="display: none;">档案编号</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[${stuts.index }].approvalFlag" type="list"
										typeGroupCode="approval_flag" defaultVal="${poVal.approvalFlag }" hasLabel="false"  title="审批标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批标识</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlLoanPayDocumentList[${stuts.index }].approvalUserId" type="list"
										dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${poVal.approvalUserId }" hasLabel="false"  title="审批人"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批人</label>
				   </td>
				   <td align="left">
					      	<input name="zmlLoanPayDocumentList[${stuts.index }].approvalDate" maxlength="20" 
						  		type="text"  class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width:120px;"   value="<fmt:formatDate value='${poVal.approvalDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
					  <label class="Validform_label" style="display: none;">审批时间</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanPayDocumentList[${stuts.index }].approvalOpinion" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.approvalOpinion }">
					  <label class="Validform_label" style="display: none;">审批意见</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
