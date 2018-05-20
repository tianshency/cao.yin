<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlCommodityStandardBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlCommodityStandardBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlCommodityStandardBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlCommodityStandard_table_template tr").clone();
	 	 $("#add_zmlCommodityStandard_table").append(tr);
	 	 resetTrNum('add_zmlCommodityStandard_table');
	 	 return false;
    });  
	$('#delZmlCommodityStandardBtn').bind('click', function(){   
      	$("#add_zmlCommodityStandard_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlCommodityStandard_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlCommodityStandard_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlCommodityStandardBtn" href="#">添加</a> <a id="delZmlCommodityStandardBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlCommodityStandard_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						农作物种类
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						亩用量起
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						亩用量至
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						用量单位
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						使用说明
				  </td>
	</tr>
	<tbody id="add_zmlCommodityStandard_table">
	<c:if test="${fn:length(zmlCommodityStandardList)  > 0 }">
		<c:forEach items="${zmlCommodityStandardList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlCommodityStandardList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].commodityId" type="hidden" value="${poVal.commodityId }"/>
					<input name="zmlCommodityStandardList[${stuts.index }].standardMu" type="hidden" value="${poVal.standardMu }"/>
				   <td align="left">
							<t:dictSelect field="zmlCommodityStandardList[${stuts.index }].corpId" type="list"
										dictTable="zml_crop_type" dictField="id" dictText="name" defaultVal="${poVal.corpId }" hasLabel="false"  title="农作物种类"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">农作物种类</label>
				   </td>
				   <td align="left">
					  	<input name="zmlCommodityStandardList[${stuts.index }].dosageStart" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.dosageStart }">
					  <label class="Validform_label" style="display: none;">亩用量起</label>
				   </td>
				   <td align="left">
					  	<input name="zmlCommodityStandardList[${stuts.index }].dosageEnd" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.dosageEnd }">
					  <label class="Validform_label" style="display: none;">亩用量至</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlCommodityStandardList[${stuts.index }].dosageUnit" type="list"
										typeGroupCode="specification" defaultVal="${poVal.dosageUnit }" hasLabel="false"  title="用量单位"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用量单位</label>
				   </td>
				   <td align="left">
					       	<input name="zmlCommodityStandardList[${stuts.index }].useDescription" maxlength="500" 
						  		type="text" class="inputxt"  style="width:120px;"   value="${poVal.useDescription }">
					  <label class="Validform_label" style="display: none;">使用说明</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
