<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserPayTurnoverList" checkbox="true" fitColumns="false" title="用户支付流水" actionUrl="zmlUserPayTurnoverController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="订单编号"  field="orderNum"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="支付类型"  field="payType"   query="true" queryMode="single" dictionary="pay_type" width="120"></t:dgCol>
   <t:dgCol title="支付方式"  field="payWay"   query="true" queryMode="single" dictionary="pay_way" width="120"></t:dgCol>
   <t:dgCol title="支付方式名称"  field="payName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="总金额"  field="totalAmount"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="付款金额"  field="payAmout"   query="true" queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="pay_sts" width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserPayTurnoverController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserPayTurnoverController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserPayTurnoverController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserPayTurnoverController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserPayTurnoverController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/pay_turnover/zmlUserPayTurnoverList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserPayTurnoverListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserPayTurnoverListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserPayTurnoverController.do?upload', "zmlUserPayTurnoverList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserPayTurnoverController.do?exportXls","zmlUserPayTurnoverList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserPayTurnoverController.do?exportXlsByT","zmlUserPayTurnoverList");
}
 </script>