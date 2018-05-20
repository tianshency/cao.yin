<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlOrderList" checkbox="true" fitColumns="false" title="订单" actionUrl="zmlOrderController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人登录名称"  field="createBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人名称"  field="updateName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新人登录名称"  field="updateBy"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属部门"  field="sysOrgCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所属公司"  field="sysCompanyCode"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="买家"  field="buyersId"    queryMode="single" dictionary="zml_user,id,user_name"  width="120"></t:dgCol>
   <t:dgCol title="商家"  field="merchantsId"    queryMode="single" dictionary="zml_merchants,id,merchants_name"  width="120"></t:dgCol>
   <t:dgCol title="订单编号"  field="orderNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付编号"  field="payId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发票类型"  field="invoiceType"    queryMode="single" dictionary="invoice_type" width="120"></t:dgCol>
   <t:dgCol title="发票抬头"  field="invoiceTitle"    queryMode="single" dictionary="invoice_title" width="120"></t:dgCol>
   <t:dgCol title="发票内容"  field="invoiceContent"    queryMode="single" dictionary="invoice_content" width="120"></t:dgCol>
   <t:dgCol title="订单状态"  field="status"    queryMode="single" dictionary="u_order_sts" width="120"></t:dgCol>
   <t:dgCol title="平台备注"  field="platformRemarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订单金额"  field="allAmount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实际付款金额"  field="payAmout"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否退货"  field="isReturns"    queryMode="single" dictionary="is_returns" width="120"></t:dgCol>
   <t:dgCol title="退货原因"  field="returnReason"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="下单时间"  field="orderTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户备注"  field="userRemarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收货地址"  field="addressId"    queryMode="single" dictionary="zml_user_address,id,address"  width="120"></t:dgCol>
   <t:dgCol title="物流公司"  field="logisticsCompany"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流单号"  field="logisticsNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流费"  field="logisticsFee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流联系人"  field="logisticsLinkPerson"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="物流联系人电话"  field="logisticsLinkPhnoe"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlOrderController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlOrderController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlOrderController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlOrderController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlOrderController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/user_order/zmlOrderList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlOrderListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlOrderListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlOrderListtb").find("input[name='orderTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlOrderController.do?upload', "zmlOrderList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlOrderController.do?exportXls","zmlOrderList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlOrderController.do?exportXlsByT","zmlOrderList");
}
 </script>