<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addZmlOrderCommodityBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delZmlOrderCommodityBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addZmlOrderCommodityBtn').bind('click', function(){   
 		 var tr =  $("#add_zmlOrderCommodity_table_template tr").clone();
	 	 $("#add_zmlOrderCommodity_table").append(tr);
	 	 resetTrNum('add_zmlOrderCommodity_table');
	 	 return false;
    });  
	$('#delZmlOrderCommodityBtn').bind('click', function(){   
      	$("#add_zmlOrderCommodity_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_zmlOrderCommodity_table'); 
        return false;
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    	if(location.href.indexOf("load=detail")!=-1){
			$(":input").attr("disabled","true");
			$(".datagrid-toolbar").hide();
		}
		//将表格的表头固定
	    $("#zmlOrderCommodity_table").createhftable({
	    	height:'300px',
			width:'auto',
			fixFooter:false
			});
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addZmlOrderCommodityBtn" href="#">添加</a> <a id="delZmlOrderCommodityBtn" href="#">删除</a> 
</div>
<table border="0" cellpadding="2" cellspacing="0" id="zmlOrderCommodity_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">序号</td>
		<td align="center" bgcolor="#EEEEEE" style="width: 25px;">操作</td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						商品
				  </td>
				  <td align="left" bgcolor="#EEEEEE" style="width: 126px;">
						商品名称
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
						商品类型
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
	<tbody id="add_zmlOrderCommodity_table">
	<c:if test="${fn:length(zmlOrderCommodityList)  > 0 }">
		<c:forEach items="${zmlOrderCommodityList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="zmlOrderCommodityList[${stuts.index }].id" type="hidden" value="${poVal.id }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].createName" type="hidden" value="${poVal.createName }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].createBy" type="hidden" value="${poVal.createBy }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].createDate" type="hidden" value="${poVal.createDate }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].updateName" type="hidden" value="${poVal.updateName }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].updateBy" type="hidden" value="${poVal.updateBy }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].updateDate" type="hidden" value="${poVal.updateDate }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].version" type="hidden" value="${poVal.version }"/>
					<input name="zmlOrderCommodityList[${stuts.index }].orderNum" type="hidden" value="${poVal.orderNum }"/>
				   <td align="left">
							<t:dictSelect field="zmlOrderCommodityList[${stuts.index }].commodityId" type="list"
										dictTable="zml_commodity" dictField="id" dictText="name" defaultVal="${poVal.commodityId }" hasLabel="false"  title="商品"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">商品</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].commodityName" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.commodityName }">
					  <label class="Validform_label" style="display: none;">商品名称</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].allCount" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.allCount }">
					  <label class="Validform_label" style="display: none;">数量</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].price" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.price }">
					  <label class="Validform_label" style="display: none;">单价</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].allAmount" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.allAmount }">
					  <label class="Validform_label" style="display: none;">金额</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].commodityType" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.commodityType }">
					  <label class="Validform_label" style="display: none;">商品类型</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].commodityAttributes" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.commodityAttributes }">
					  <label class="Validform_label" style="display: none;">详情</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].logisticsFee" maxlength="8" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n" value="${poVal.logisticsFee }">
					  <label class="Validform_label" style="display: none;">物流费</label>
				   </td>
				   <td align="left">
					  	<input name="zmlOrderCommodityList[${stuts.index }].remarks" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"  value="${poVal.remarks }">
					  <label class="Validform_label" style="display: none;">备注</label>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
