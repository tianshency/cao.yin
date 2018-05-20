<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanProductList" checkbox="true" fitColumns="false" title="贷款产品" actionUrl="zmlLoanProductController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品名称"  field="proName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="期限"  field="periods"   query="true" queryMode="single" dictionary="periods_sel" width="120"></t:dgCol>
   <t:dgCol title="期限单位"  field="periodsUnit"   query="true" queryMode="single" dictionary="periods_unit" width="120"></t:dgCol>
   <t:dgCol title="利率"  field="interestRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="罚息率"  field="penaltyRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最小金额"  field="minAmount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="最大金额"  field="maxAmount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="流程ID"  field="wfId"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="details"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="还款方式"  field="repayment" query="true" queryMode="single" dictionary="repayment" width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="is_use" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanProductController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanProductController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanProductController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanProductController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanProductController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/product/zmlLoanProductList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanProductListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanProductListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanProductController.do?upload', "zmlLoanProductList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanProductController.do?exportXls","zmlLoanProductList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanProductController.do?exportXlsByT","zmlLoanProductList");
}
 </script>