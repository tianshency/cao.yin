<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addAutoFormDbFieldForTableBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delAutoFormDbFieldForTableBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addAutoFormDbFieldForTableBtn').bind('click', function(){   
 		 var tr =  $("#add_autoFormDbFieldForTable_table_template tr").clone();
	 	 $("#add_autoFormDbFieldForTable_table").append(tr);
	 	 resetTrNum('add_autoFormDbFieldForTable_table');
	 	 return false;
    });  
	$('#delAutoFormDbFieldForTableBtn').bind('click', function(){   
      	$("#add_autoFormDbFieldForTable_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_autoFormDbFieldForTable_table'); 
        return false;
    });
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			//update by jg_renjie at:2015/12/15 for: #776 【表单数据源】查看模式下可以点击删除按钮
			$("a").each(function(){
				$(this).css("cursor", "default");
				$(this).removeAttr("href");
				$(this).removeAttr("onclick");
			});
			//update by jg_renjie at:2015/12/15 for: #776 【表单数据源】查看模式下可以点击删除按钮
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#autoFormDbFieldForTable_table").createhftable({
	    	height:'400px',
			fixFooter:false
			});
    });
    function deleteOne(obj){
    	var tableId = $(obj).parent().parent().parent().parent().attr("id");
    	$(obj).parent().parent().parent().remove();
    	resetTrNum($("input[name='dbType']:checked").val()+"_div #"+tableId);
    }
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addAutoFormDbFieldForTableBtn" href="#"><t:mutiLang langKey="common.add"/></a> <a id="delAutoFormDbFieldForTableBtn" href="#"><t:mutiLang langKey="common.batch.delete"/></a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="autoFormDbFieldForTable_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" width="40px" bgcolor="#EEEEEE"><t:mutiLang langKey="common.code"/></td>
		<td align="center" width="40px" bgcolor="#EEEEEE">选择</td>
	  	<td align="left" width="120px" bgcolor="#EEEEEE"><t:mutiLang langKey="form.field.name"/></td>
		<td align="left" width="120px" bgcolor="#EEEEEE"><t:mutiLang langKey="form.field.content"/></td>
	  	<td align="center" width="50px" bgcolor="#EEEEEE"><t:mutiLang langKey="common.operation"/></td>
	</tr>
	<tbody id="add_autoFormDbFieldForTable_table">
	<!-- 
	<c:if test="${fn:length(autoFormDbFieldList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 40px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
					<input name="autoFormDbFieldList[0].id" type="hidden"/>
					<input name="autoFormDbFieldList[0].createName" type="hidden"/>
					<input name="autoFormDbFieldList[0].createBy" type="hidden"/>
					<input name="autoFormDbFieldList[0].createDate" type="hidden"/>
					<input name="autoFormDbFieldList[0].updateName" type="hidden"/>
					<input name="autoFormDbFieldList[0].updateBy" type="hidden"/>
					<input name="autoFormDbFieldList[0].updateDate" type="hidden"/>
					<input name="autoFormDbFieldList[0].sysOrgCode" type="hidden"/>
					<input name="autoFormDbFieldList[0].sysCompanyCode" type="hidden"/>
					<input name="autoFormDbFieldList[0].autoFormDbId" type="hidden"/>
				  <td align="left">
					  	<input name="autoFormDbFieldList[0].fieldName" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;">
					</td>
					<td align="center">
						<div style="width: 50px;" align="center">[<a name="delAutoFormDbFieldForTableOneBtn" href="javascript:void(0)" onclick="deleteOne(this)"><t:mutiLang langKey="common.delete"/></a>]</div>
					</td>
   			</tr>
	</c:if>
	 -->
	<c:if test="${fn:length(autoFormDbFieldList)  > 0 }">
		<c:forEach items="${autoFormDbFieldList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 40px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:40px;"  type="checkbox" name="ck" /></td>
					<input name="autoFormDbFieldList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="autoFormDbFieldList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="autoFormDbFieldList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="autoFormDbFieldList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="autoFormDbFieldList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="autoFormDbFieldList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="autoFormDbFieldList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="autoFormDbFieldList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="autoFormDbFieldList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="autoFormDbFieldList[${stuts.index }].autoFormDbId" type="hidden" value="${poVal.autoFormDbId }"/>
				   <td align="left">
					  	<input name="autoFormDbFieldList[${stuts.index }].fieldName" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" value="${poVal.fieldName }">
				   </td>
				<td align="left">
					<input name="autoFormDbFieldList[${stuts.index }].fieldText" maxlength="50"
						   type="text" class="inputxt"  style="width:120px;" value="${poVal.fieldText }">
				</td>
				   <td align="center">
						<div style="width: 50px;" align="center">[<a name="delAutoFormDbFieldForTableOneBtn" href="javascript:void(0)" onclick="deleteOne(this)"><t:mutiLang langKey="common.delete"/></a>]</div>
					</td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
