<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addWrwTaskUserRelationBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delWrwTaskUserRelationBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addWrwTaskUserRelationBtn').bind('click', function(){   
 		 var tr =  $("#add_wrwTaskUserRelation_table_template tr").clone();
	 	 $("#add_wrwTaskUserRelation_table").append(tr);
	 	 resetTrNum('add_wrwTaskUserRelation_table');
	 	 return false;
    });  
	$('#delWrwTaskUserRelationBtn').bind('click', function(){   
      	$("#add_wrwTaskUserRelation_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_wrwTaskUserRelation_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#wrwTaskUserRelation_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addWrwTaskUserRelationBtn" href="#">添加</a> <a id="delWrwTaskUserRelationBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="wrwTaskUserRelation_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						任务
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						用户
				  </td>
	</tr>
	<tbody id="add_wrwTaskUserRelation_table">
	<c:if test="${fn:length(wrwTaskUserRelationList)  > 0 }">
		<c:forEach items="${wrwTaskUserRelationList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="wrwTaskUserRelationList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="wrwTaskUserRelationList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
				   <td align="left">
							<t:dictSelect field="wrwTaskUserRelationList[${stuts.index }].taskId" type="list"
										dictTable="wrw_task_infno" dictField="id" dictText="name" defaultVal="${poVal.taskId }" hasLabel="false"  title="任务"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">任务</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="wrwTaskUserRelationList[${stuts.index }].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${poVal.userId }" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
