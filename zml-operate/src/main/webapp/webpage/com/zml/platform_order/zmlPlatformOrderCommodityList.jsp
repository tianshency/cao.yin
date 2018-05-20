<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlPlatformOrderCommodityBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlPlatformOrderCommodityBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlPlatformOrderCommodityBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlPlatformOrderCommodity_table_template tr").clone();
	 	 $("#add_zmlPlatformOrderCommodity_table").append(tr);
	 	 resetTrNum('add_zmlPlatformOrderCommodity_table');
	 	 return false;
    });  
	$('#delZmlPlatformOrderCommodityBtn').bind('click', function(){   
      	$("#add_zmlPlatformOrderCommodity_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlPlatformOrderCommodity_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlPlatformOrderCommodity_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlPlatformOrderCommodityBtn" href="#">添加</a> <a id="delZmlPlatformOrderCommodityBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlPlatformOrderCommodity_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						商品
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						数量
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						单价
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						金额
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						商品型号
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						详情
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						物流费
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						备注
				  </td>
	</tr>
	<tbody id="add_zmlPlatformOrderCommodity_table">
	<c:if test="${fn:length(zmlPlatformOrderCommodityList)  > 0 }">
		<c:forEach items="${zmlPlatformOrderCommodityList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].sysOrgCode" type="hidden" value="${poVal.sysOrgCode }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].sysCompanyCode" type="hidden" value="${poVal.sysCompanyCode }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlPlatformOrderCommodityList[${stuts.index }].porderNum" type="hidden" value="${poVal.porderNum }"/>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].commodityId" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.commodityId }">
					  <label class="Validform_label" style="display: none;">商品</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].amount" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.amount }">
					  <label class="Validform_label" style="display: none;">数量</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].price" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.price }">
					  <label class="Validform_label" style="display: none;">单价</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].allAmount" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.allAmount }">
					  <label class="Validform_label" style="display: none;">金额</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].commodityModel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.commodityModel }">
					  <label class="Validform_label" style="display: none;">商品型号</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].commodityAttributes" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.commodityAttributes }">
					  <label class="Validform_label" style="display: none;">详情</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].logisticsFee" maxlength="8" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.logisticsFee }">
					  <label class="Validform_label" style="display: none;">物流费</label>
				   </td>
				   <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[${stuts.index }].remarks" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.remarks }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
