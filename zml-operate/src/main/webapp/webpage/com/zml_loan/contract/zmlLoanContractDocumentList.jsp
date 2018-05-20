<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlLoanContractDocumentBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlLoanContractDocumentBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlLoanContractDocumentBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlLoanContractDocument_table_template tr").clone();
	 	 $("#add_zmlLoanContractDocument_table").append(tr);
	 	 resetTrNum('add_zmlLoanContractDocument_table');
	 	 return false;
    });  
	$('#delZmlLoanContractDocumentBtn').bind('click', function(){   
      	$("#add_zmlLoanContractDocument_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlLoanContractDocument_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlLoanContractDocument_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlLoanContractDocumentBtn" href="#">添加</a> <a id="delZmlLoanContractDocumentBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlLoanContractDocument_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <!-- <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						用户
				  </td> -->
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						文档类型
				  </td>
				  <!-- <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						图片路径
				  </td> -->
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						文件
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						描述
				  </td>
				  <!-- <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						序号
				  </td> -->
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						档案编号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批标识
				  </td>
				  <!-- <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批人
				  </td> 
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批时间
				  </td>-->
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						审批意见
				  </td>
	</tr>
	<tbody id="add_zmlLoanContractDocument_table">
	<c:if test="${fn:length(zmlLoanContractDocumentList)  > 0 }">
		<c:forEach items="${zmlLoanContractDocumentList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlLoanContractDocumentList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlLoanContractDocumentList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlLoanContractDocumentList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlLoanContractDocumentList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlLoanContractDocumentList[${stuts.index }].contractId" type="hidden" value="${poVal.contractId }"/>
					<input name="zmlLoanContractDocumentList[${stuts.index }].contractId" type="hidden" value="${zmlLoanContractPage.userId }"/>
				   <%-- <td align="left">
							<t:dictSelect field="zmlLoanContractDocumentList[${stuts.index }].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${poVal.userId }" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				   </td> --%>
				   <td align="left">
							<t:dictSelect field="zmlLoanContractDocumentList[${stuts.index }].fileFlag" type="list"
										typeGroupCode="contract_doc_type" defaultVal="${poVal.docType }" hasLabel="false"  title="文档类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">文档类型</label>
				   </td>
				   <%-- <td align="left">
					  	<input name="zmlLoanContractDocumentList[${stuts.index }].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.imagePath }">
					  <label class="Validform_label" style="display: none;">图片路径</label>
				   </td> --%>
				   <td align="left">
					        <input type="hidden" id="zmlLoanContractDocumentList[${stuts.index }].filePath" name="zmlLoanContractDocumentList[${stuts.index }].filePath"  value="${poVal.filePath }"/>
							<c:if test="${empty poVal.filePath}">
								<a  target="_blank" id="zmlLoanContractDocumentList[${stuts.index }].filePath_href">未上传</a>
							</c:if>
							<c:if test="${!empty poVal.filePath}">
								<a  href="${poVal.filePath}"  target="_blank" id="zmlLoanContractDocumentList[${stuts.index }].filePath_href">下载</a>
							</c:if>
							<br>
						   <input class="ui-button" type="button" value="上传附件"
										onclick="commonUpload(commonUploadDefaultCallBack,'zmlLoanContractDocumentList\\[${stuts.index }\\]\\.filePath')"/> 
					  <label class="Validform_label" style="display: none;">文件路径</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanContractDocumentList[${stuts.index }].details" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.details }">
					  <label class="Validform_label" style="display: none;">描述</label>
				   </td>
				   <%-- <td align="left">
					  	<input name="zmlLoanContractDocumentList[${stuts.index }].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.seqNo }">
					  <label class="Validform_label" style="display: none;">序号</label>
				   </td> --%>
				   <td align="left">
					  	<input name="zmlLoanContractDocumentList[${stuts.index }].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.archivesNo }">
					  <label class="Validform_label" style="display: none;">档案编号</label>
				   </td>
				   <td align="left">
					<t:dictSelect field="zmlLoanContractDocumentList[${stuts.index }].approvalFlag" type="list"
						typeGroupCode="approval_flag" defaultVal="${poVal.approvalFlag }" hasLabel="false"  title="审批结果"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">审批结果</label>
				   </td>
				   <td align="left">
					  	<input name="zmlLoanContractDocumentList[${stuts.index }].approvalOpinion" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.approvalOpinion }">
					  <label class="Validform_label" style="display: none;">审批意见</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
