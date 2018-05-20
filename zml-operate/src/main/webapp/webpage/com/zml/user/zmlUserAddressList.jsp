<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlUserAddressBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlUserAddressBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlUserAddressBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlUserAddress_table_template tr").clone();
	 	 $("#add_zmlUserAddress_table").append(tr);
	 	 resetTrNum('add_zmlUserAddress_table');
	 	 return false;
    });  
	$('#delZmlUserAddressBtn').bind('click', function(){   
      	$("#add_zmlUserAddress_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlUserAddress_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlUserAddress_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlUserAddressBtn" href="#">添加</a> <a id="delZmlUserAddressBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlUserAddress_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						收货人
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
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						地址标识
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						精度
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						纬度
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						地点名称
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						省份
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						市级
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						县城
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						村子
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						屯组
				  </td>
	</tr>
	<tbody id="add_zmlUserAddress_table">
	<c:if test="${fn:length(zmlUserAddressList)  > 0 }">
		<c:forEach items="${zmlUserAddressList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlUserAddressList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlUserAddressList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zmlUserAddressList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zmlUserAddressList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlUserAddressList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zmlUserAddressList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zmlUserAddressList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlUserAddressList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zmlUserAddressList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zmlUserAddressList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlUserAddressList[${stuts.index }].openId" type="hidden" value="${poVal.openId }"/>
					<input name="zmlUserAddressList[${stuts.index }].userId" type="hidden" value="${poVal.userId }"/>
				   <td align="left">
					  	<input name="zmlUserAddressList[${stuts.index }].consignee" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.consignee }">
					  <label class="Validform_label" style="display: none;">收货人</label>
				   </td>
				   <td align="left">
					  	<input name="zmlUserAddressList[${stuts.index }].phone" maxlength="11" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="m" value="${poVal.phone }">
					  <label class="Validform_label" style="display: none;">电话</label>
				   </td>
				   <td align="left">
					  	<input name="zmlUserAddressList[${stuts.index }].address" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="*" value="${poVal.address }">
					  <label class="Validform_label" style="display: none;">地址</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].isDefault" type="list"
										typeGroupCode="is_default" defaultVal="${poVal.isDefault }" hasLabel="false"  title="是否默认"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">是否默认</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].type" type="list"
										typeGroupCode="address_type" defaultVal="${poVal.type }" hasLabel="false"  title="地址类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">地址类型</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].flag" type="list"
										typeGroupCode="addr_flag" defaultVal="${poVal.flag }" hasLabel="false"  title="地址标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">地址标识</label>
				   </td>
				   <td align="left">
					  	<input name="zmlUserAddressList[${stuts.index }].addrPrecision" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.addrPrecision }">
					  <label class="Validform_label" style="display: none;">精度</label>
				   </td>
				   <td align="left">
					  	<input name="zmlUserAddressList[${stuts.index }].addrLatitude" maxlength="20" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.addrLatitude }">
					  <label class="Validform_label" style="display: none;">纬度</label>
				   </td>
				   <td align="left">
					  	<input name="zmlUserAddressList[${stuts.index }].addressName" maxlength="300" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.addressName }">
					  <label class="Validform_label" style="display: none;">地点名称</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].province" type="list"
										typeGroupCode="" defaultVal="${poVal.province }" hasLabel="false"  title="省份"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">省份</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].city" type="list"
										typeGroupCode="" defaultVal="${poVal.city }" hasLabel="false"  title="市级"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">市级</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].county" type="list"
										typeGroupCode="" defaultVal="${poVal.county }" hasLabel="false"  title="县城"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">县城</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].village" type="list"
										typeGroupCode="" defaultVal="${poVal.village }" hasLabel="false"  title="村子"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">村子</label>
				   </td>
				   <td align="left">
							<t:dictSelect field="zmlUserAddressList[${stuts.index }].tuen" type="list"
										typeGroupCode="" defaultVal="${poVal.tuen }" hasLabel="false"  title="屯组"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">屯组</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
