<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlPorderToCorderBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlPorderToCorderBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlPorderToCorderBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlPorderToCorder_table_template tr").clone();
	 	 $("#add_zmlPorderToCorder_table").append(tr);
	 	 resetTrNum('add_zmlPorderToCorder_table');
	 	 return false;
    });  
	$('#delZmlPorderToCorderBtn').bind('click', function(){   
      	$("#add_zmlPorderToCorder_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlPorderToCorder_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlPorderToCorder_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlPorderToCorderBtn" href="#">添加</a> <a id="delZmlPorderToCorderBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlPorderToCorder_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						用户订单编号
				  </td>
	</tr>
	<tbody id="add_zmlPorderToCorder_table">
	<c:if test="${fn:length(zmlPorderToCorderList)  > 0 }">
		<c:forEach items="${zmlPorderToCorderList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlPorderToCorderList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlPorderToCorderList[${stuts.index }].porderNum" type="hidden" value="${poVal.porderNum }"/>
				   <td align="left">
					  	<input name="zmlPorderToCorderList[${stuts.index }].corderNum" maxlength="30" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.corderNum }">
					  <label class="Validform_label" style="display: none;">用户订单编号</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
