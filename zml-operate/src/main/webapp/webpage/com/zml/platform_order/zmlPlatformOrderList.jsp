<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlPlatformOrderList" checkbox="true" fitColumns="false" title="平台订单" actionUrl="zmlPlatformOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商家"  field="sellerId"   query="true" queryMode="single" dictionary="zml_merchants,id,merchants_name"  width="120"></t:dgCol>
   <t:dgCol title="订单编号"  field="porderNum"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单金额"  field="allAmount"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="支付方式"  field="payWay"    queryMode="single" dictionary="p_pay_way" width="120"></t:dgCol>
   <t:dgCol title="付款凭证"  field="payVoucher"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发票类型"  field="invoiceType"    queryMode="single" dictionary="invoice_type" width="120"></t:dgCol>
   <t:dgCol title="发票抬头"  field="invoiceTitle"    queryMode="single" dictionary="invoice_title" width="120"></t:dgCol>
   <t:dgCol title="发票内容"  field="invoiceContent"    queryMode="single" dictionary="invoice_content" width="120"></t:dgCol>
   <t:dgCol title="订单状态"  field="status"   query="true" queryMode="single" dictionary="p_order_sts" width="120"></t:dgCol>
   <t:dgCol title="是否退货"  field="isReturns"   query="true" queryMode="single" dictionary="is_returns" width="120"></t:dgCol>
   <t:dgCol title="退货原因"  field="returnReason"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="下单时间"  field="orderTime" formatter="yyyy-MM-dd"  query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="付款时间"  field="payTime" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货地址"  field="address"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流公司"  field="logisticsCompany"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流单号"  field="logisticsNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流费"  field="logisticsFee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流联系人"  field="logisticsLinkPerson"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流联系人电话"  field="logisticsLinkPhnoe"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlPlatformOrderController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlPlatformOrderController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlPlatformOrderController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlPlatformOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlPlatformOrderController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/platform_order/zmlPlatformOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlPlatformOrderListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlPlatformOrderListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlPlatformOrderListtb").find("input[name='orderTime_begin']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlPlatformOrderListtb").find("input[name='orderTime_end']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlPlatformOrderListtb").find("input[name='payTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlPlatformOrderController.do?upload', "zmlPlatformOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlPlatformOrderController.do?exportXls","zmlPlatformOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlPlatformOrderController.do?exportXlsByT","zmlPlatformOrderList");
}
 </script>