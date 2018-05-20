<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlCommodityDocBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlCommodityDocBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlCommodityDocBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlCommodityDoc_table_template tr").clone();
	 	 $("#add_zmlCommodityDoc_table").append(tr);
	 	 resetTrNum('add_zmlCommodityDoc_table');
	 	 return false;
    });  
	$('#delZmlCommodityDocBtn').bind('click', function(){   
      	$("#add_zmlCommodityDoc_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlCommodityDoc_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlCommodityDoc_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlCommodityDocBtn" href="#">添加</a> <a id="delZmlCommodityDocBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlCommodityDoc_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						标识
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						类型
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
	</tr>
	<tbody id="add_zmlCommodityDoc_table">
	<c:if test="${fn:length(zmlCommodityDocList)  > 0 }">
		<c:forEach items="${zmlCommodityDocList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlCommodityDocList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlCommodityDocList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zmlCommodityDocList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zmlCommodityDocList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlCommodityDocList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zmlCommodityDocList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zmlCommodityDocList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlCommodityDocList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zmlCommodityDocList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zmlCommodityDocList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlCommodityDocList[${stuts.index }].commodityId" type="hidden" value="${poVal.commodityId }"/>
				   <td align="left">
							<t:dictSelect field="zmlCommodityDocList[${stuts.index }].flag" type="list"
										typeGroupCode="doc_sts" defaultVal="${poVal.flag }" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlCommodityDocList[${stuts.index }].type" type="list"
										typeGroupCode="type_sts" defaultVal="${poVal.type }" hasLabel="false"  title="类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">类型</label>
				   </td>
				   <td align="left">
					       	<input name="zmlCommodityDocList[${stuts.index }].imagePath" maxlength="200" 
						  		type="text" class="inputxt"  style="width:120px;"   value="${poVal.imagePath }">
					  <label class="Validform_label" style="display: none;">图片路径</label>
				   </td>
				   <td align="left">
					        <input type="hidden" id="zmlCommodityDocList[${stuts.index }].filePath" name="zmlCommodityDocList[${stuts.index }].filePath"  value="${poVal.filePath }"/>
										<c:if test="${empty poVal.filePath}">
											<a  target="_blank" id="zmlCommodityDocList[${stuts.index }].filePath_href">未上传</a>
										</c:if>
										<c:if test="${!empty poVal.filePath}">
											<a  href="${poVal.filePath}"  target="_blank" id="zmlCommodityDocList[${stuts.index }].filePath_href">下载</a>
										</c:if>
										<br>
									   <input class="ui-button" type="button" value="上传附件"
													onclick="commonUpload(commonUploadDefaultCallBack,'zmlCommodityDocList\\[${stuts.index }\\]\\.filePath')"/> 
					  <label class="Validform_label" style="display: none;">文件路径</label>
				   </td>
				   <td align="left">
					  	<input name="zmlCommodityDocList[${stuts.index }].details" maxlength="100" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.details }">
					  <label class="Validform_label" style="display: none;">描述</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
