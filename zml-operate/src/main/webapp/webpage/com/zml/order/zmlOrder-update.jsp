<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>订单</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlOrderController.do?doUpdate" >
					<input id="id" name="id" type="hidden" value="${zmlOrderPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlOrderPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlOrderPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlOrderPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlOrderPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlOrderPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlOrderPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlOrderPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlOrderPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${zmlOrderPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">买家:</label>
			</td>
			<td class="value">
					<t:dictSelect field="buyersId" type="list"
						dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="${zmlOrderPage.buyersId}" hasLabel="false"  title="买家"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">买家</label>
			</td>
			<td align="right">
				<label class="Validform_label">商家:</label>
			</td>
			<td class="value">
					<t:dictSelect field="merchantsId" type="list"
						dictTable="t_s_depart" dictField="org_code" dictText="departname" defaultVal="${zmlOrderPage.merchantsId}" hasLabel="false"  title="商家"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">商家</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">订单编号:</label>
			</td>
			<td class="value">
		     	 <input id="orderNum" name="orderNum" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.orderNum}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单编号</label>
			</td>
			<td align="right">
				<label class="Validform_label">支付编号:</label>
			</td>
			<td class="value">
		     	 <input id="payId" name="payId" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.payId}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">支付编号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">发票类型:</label>
			</td>
			<td class="value">
					<t:dictSelect field="invoiceType" type="list"
						typeGroupCode="invoice_type" defaultVal="${zmlOrderPage.invoiceType}" hasLabel="false"  title="发票类型"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票类型</label>
			</td>
			<td align="right">
				<label class="Validform_label">发票抬头:</label>
			</td>
			<td class="value">
					<t:dictSelect field="invoiceTitle" type="list"
						typeGroupCode="invoice_title" defaultVal="${zmlOrderPage.invoiceTitle}" hasLabel="false"  title="发票抬头"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票抬头</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">发票内容:</label>
			</td>
			<td class="value">
					<t:dictSelect field="invoiceContent" type="list"
						typeGroupCode="invoice_content" defaultVal="${zmlOrderPage.invoiceContent}" hasLabel="false"  title="发票内容"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">发票内容</label>
			</td>
			<td align="right">
				<label class="Validform_label">订单状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="status" type="list"
						typeGroupCode="u_order_sts" defaultVal="${zmlOrderPage.status}" hasLabel="false"  title="订单状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单状态</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">平台备注:</label>
			</td>
			<td class="value">
		     	 <input id="platformRemarks" name="platformRemarks" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.platformRemarks}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">平台备注</label>
			</td>
			<td align="right">
				<label class="Validform_label">订单金额:</label>
			</td>
			<td class="value">
		     	 <input id="allAmount" name="allAmount" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.allAmount}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">订单金额</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">实际付款金额:</label>
			</td>
			<td class="value">
		     	 <input id="payAmout" name="payAmout" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.payAmout}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">实际付款金额</label>
			</td>
			<td align="right">
				<label class="Validform_label">是否退货:</label>
			</td>
			<td class="value">
					<t:dictSelect field="isReturns" type="list"
						typeGroupCode="is_returns" defaultVal="${zmlOrderPage.isReturns}" hasLabel="false"  title="是否退货"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否退货</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">退货原因:</label>
			</td>
			<td class="value">
		     	 <input id="returnReason" name="returnReason" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.returnReason}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">退货原因</label>
			</td>
			<td align="right">
				<label class="Validform_label">下单时间:</label>
			</td>
			<td class="value">
					  <input id="orderTime" name="orderTime" type="text" style="width: 150px" 
		      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  value='<fmt:formatDate value='${zmlOrderPage.orderTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">下单时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">用户备注:</label>
			</td>
			<td class="value">
		     	 <input id="userRemarks" name="userRemarks" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.userRemarks}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户备注</label>
			</td>
			<td align="right">
				<label class="Validform_label">收货地址:</label>
			</td>
			<td class="value">
					<t:dictSelect field="addressId" type="list"
						dictTable="zml_user_address" dictField="id" dictText="address" defaultVal="${zmlOrderPage.addressId}" hasLabel="false"  title="收货地址"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">收货地址</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物流公司:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsCompany" name="logisticsCompany" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.logisticsCompany}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流公司</label>
			</td>
			<td align="right">
				<label class="Validform_label">物流单号:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsNum" name="logisticsNum" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.logisticsNum}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流单号</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物流费:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsFee" name="logisticsFee" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.logisticsFee}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流费</label>
			</td>
			<td align="right">
				<label class="Validform_label">物流联系人:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsLinkPerson" name="logisticsLinkPerson" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.logisticsLinkPerson}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流联系人</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">物流联系人电话:</label>
			</td>
			<td class="value">
		     	 <input id="logisticsLinkPhnoe" name="logisticsLinkPhnoe" type="text" style="width: 150px" class="inputxt" value='${zmlOrderPage.logisticsLinkPhnoe}'>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">物流联系人电话</label>
			</td>
		</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="zmlOrderController.do?zmlOrderCommodityList&id=${zmlOrderPage.id}" icon="icon-search" title="商品明细" id="zmlOrderCommodity"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
		<table style="display:none">
		<tbody id="add_zmlOrderCommodity_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
							<t:dictSelect field="zmlOrderCommodityList[#index#].commodityId" type="list"
										dictTable="zml_commodity" dictField="id" dictText="name" defaultVal="" hasLabel="false"  title="商品"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">商品</label>
				  </td>
				  <td align="left">
					  	<input name="zmlOrderCommodityList[#index#].allCount" maxlength="10" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		 datatype="n">
					  <label class="Validform_label" style="display: none;">数量</label>
				  </td>
				  <td align="left">
					  	<input name="zmlOrderCommodityList[#index#].price" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		 datatype="n">
					  <label class="Validform_label" style="display: none;">单价</label>
				  </td>
				  <td align="left">
					  	<input name="zmlOrderCommodityList[#index#].allAmount" maxlength="14" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		 datatype="n">
					  <label class="Validform_label" style="display: none;">金额</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlOrderCommodityList[#index#].commodityType" type="list"
										dictTable="zml_commodity_classify" dictField="id" dictText="name" defaultVal="" hasLabel="false"  title="商品类型"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">商品类型</label>
				  </td>
				  <td align="left">
					  	<input name="zmlOrderCommodityList[#index#].commodityAttributes" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">详情</label>
				  </td>
				  <td align="left">
					  	<input name="zmlOrderCommodityList[#index#].logisticsFee" maxlength="8" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		 datatype="n">
					  <label class="Validform_label" style="display: none;">物流费</label>
				  </td>
				  <td align="left">
					  	<input name="zmlOrderCommodityList[#index#].remarks" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;"
					  		>
					  <label class="Validform_label" style="display: none;">备注</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml/order/zmlOrder.js"></script>	
