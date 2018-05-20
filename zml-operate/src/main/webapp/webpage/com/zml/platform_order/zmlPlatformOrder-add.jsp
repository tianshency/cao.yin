<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>平台订单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlPlatformOrderController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${zmlPlatformOrderPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlPlatformOrderPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlPlatformOrderPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlPlatformOrderPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlPlatformOrderPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlPlatformOrderPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlPlatformOrderPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlPlatformOrderPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlPlatformOrderPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlPlatformOrderPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">商家:</label>
			</td>
			<td class="value">
					<t:dictSelect field="sellerId" type="list"
						dictTable="zml_merchants" dictField="id" dictText="merchants_name"  hasLabel="false"  title="商家"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">商家</label>
			</td>
			<td align="right">
				<label class="Validform_label">订单编号:</label>
			</td>
			<td class="value">
		     	 <input id="porderNum" name="porderNum" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单编号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">订单金额:</label>
			</td>
			<td class="value">
		     	 <input id="allAmount" name="allAmount" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单金额</label>
			</td>
			<td align="right">
				<label class="Validform_label">支付方式:</label>
			</td>
			<td class="value">
					<t:dictSelect field="payWay" type="list"
						typeGroupCode="p_pay_way"  hasLabel="false"  title="支付方式"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">支付方式</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">付款凭证:</label>
			</td>
			<td class="value">
		     	 <input id="payVoucher" name="payVoucher" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款凭证</label>
			</td>
			<td align="right">
				<label class="Validform_label">发票类型:</label>
			</td>
			<td class="value">
					<t:dictSelect field="invoiceType" type="list"
						typeGroupCode="invoice_type"  hasLabel="false"  title="发票类型"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">发票抬头:</label>
			</td>
			<td class="value">
					<t:dictSelect field="invoiceTitle" type="list"
						typeGroupCode="invoice_title"  hasLabel="false"  title="发票抬头"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票抬头</label>
			</td>
			<td align="right">
				<label class="Validform_label">发票内容:</label>
			</td>
			<td class="value">
					<t:dictSelect field="invoiceContent" type="list"
						typeGroupCode="invoice_content"  hasLabel="false"  title="发票内容"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票内容</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">订单状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="status" type="list"
						typeGroupCode="p_order_sts"  hasLabel="false"  title="订单状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否退货:</label>
			</td>
			<td class="value">
					<t:dictSelect field="isReturns" type="list"
						typeGroupCode="is_returns"  hasLabel="false"  title="是否退货"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否退货</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">退货原因:</label>
			</td>
			<td class="value">
		     	 <input id="returnReason" name="returnReason" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">退货原因</label>
			</td>
			<td align="right">
				<label class="Validform_label">下单时间:</label>
			</td>
			<td class="value">
					  <input id="orderTime" name="orderTime" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下单时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">付款时间:</label>
			</td>
			<td class="value">
					  <input id="payTime" name="payTime" type="text" style="width: 150px" 
							 class="Wdate" onClick="WdatePicker()" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">付款时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
		     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">收货地址:</label>
			</td>
			<td class="value">
		     	 <input id="address" name="address" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收货地址</label>
			</td>
			<td align="right">
				<label class="Validform_label">物流公司:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsCompany" name="logisticsCompany" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流公司</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物流单号:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsNum" name="logisticsNum" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流单号</label>
			</td>
			<td align="right">
				<label class="Validform_label">物流费:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsFee" name="logisticsFee" type="text" style="width: 150px" class="inputxt"datatype="n" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流费</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物流联系人:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsLinkPerson" name="logisticsLinkPerson" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流联系人</label>
			</td>
			<td align="right">
				<label class="Validform_label">物流联系人电话:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsLinkPhnoe" name="logisticsLinkPhnoe" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流联系人电话</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="zmlPlatformOrderController.do?zmlPlatformOrderCommodityList&pORDER_NUM=${zmlPlatformOrderPage.pORDER_NUM}" icon="icon-search" title="对应商品" id="zmlPlatformOrderCommodity"></t:tab>
				 <t:tab href="zmlPlatformOrderController.do?zmlPorderToCorderList&pORDER_NUM=${zmlPlatformOrderPage.pORDER_NUM}" icon="icon-search" title="对应用户订单" id="zmlPorderToCorder"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_zmlPlatformOrderCommodity_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].commodityId" maxlength="36" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">商品</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].amount" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n">
					  <label class="Validform_label" style="display: none;">数量</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].price" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n">
					  <label class="Validform_label" style="display: none;">单价</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].allAmount" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n">
					  <label class="Validform_label" style="display: none;">金额</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].commodityModel" maxlength="32" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">商品型号</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].commodityAttributes" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">详情</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].logisticsFee" maxlength="8" 
					  		type="text" class="inputxt"  style="width:120px;"  datatype="n">
					  <label class="Validform_label" style="display: none;">物流费</label>
				  </td>
				  <td align="left">
					  	<input name="zmlPlatformOrderCommodityList[#index#].remarks" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">备注</label>
				  </td>
			</tr>
		 </tbody>
	<tbody id="add_zmlPorderToCorder_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
					  	<input name="zmlPorderToCorderList[#index#].corderNum" maxlength="30" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">用户订单编号</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml/platform_order/zmlPlatformOrder.js"></script>
	