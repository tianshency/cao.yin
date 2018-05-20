<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addCfUserAddressBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delCfUserAddressBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addCfUserAddressBtn').bind('click', function(){   
 		 var tr =  $("#add_cfUserAddress_table_template tr").clone();
	 	 $("#add_cfUserAddress_table").append(tr);
	 	 resetTrNum('add_cfUserAddress_table');
	 	 return false;
    });  
	$('#delCfUserAddressBtn').bind('click', function(){   
      	$("#add_cfUserAddress_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_cfUserAddress_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#cfUserAddress_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addCfUserAddressBtn" href="#">添加</a> <a id="delCfUserAddressBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="cfUserAddress_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						姓名
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						电话
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						地址
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						是否默认
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						地址类型
				  </td>
	</tr>
	<tbody id="add_cfUserAddress_table">
	<c:if test="${fn:length(cfUserAddressList)  > 0 }">
		<c:forEach items="${cfUserAddressList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="cfUserAddressList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="cfUserAddressList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="cfUserAddressList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="cfUserAddressList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="cfUserAddressList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="cfUserAddressList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="cfUserAddressList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="cfUserAddressList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="cfUserAddressList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="cfUserAddressList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="cfUserAddressList[${stuts.index }].userId" type="hidden" value="${poVal.userId }"/>
				   <td align="left">
					  	<input name="cfUserAddressList[${stuts.index }].name" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="*" value="${poVal.name }">
					  <label class="Validform_label" style="display: none;">姓名</label>
				   </td>
				   <td align="left">
					  	<input name="cfUserAddressList[${stuts.index }].phone" maxlength="11" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="m" value="${poVal.phone }">
					  <label class="Validform_label" style="display: none;">电话</label>
				   </td>
				   <td align="left">
					  	<input name="cfUserAddressList[${stuts.index }].address" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="*" value="${poVal.address }">
					  <label class="Validform_label" style="display: none;">地址</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="cfUserAddressList[${stuts.index }].isDefault" type="list"
										typeGroupCode="is_default" defaultVal="${poVal.isDefault }" hasLabel="false"  title="是否默认"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否默认</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="cfUserAddressList[${stuts.index }].type" type="list"
										typeGroupCode="address_type" defaultVal="${poVal.type }" hasLabel="false"  title="地址类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">地址类型</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
