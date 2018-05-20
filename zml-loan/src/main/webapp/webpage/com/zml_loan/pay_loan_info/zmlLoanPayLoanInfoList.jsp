<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlLoanPayLoanInfoList" checkbox="true" fitColumns="false" title="放款信息" actionUrl="zmlLoanPayLoanInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同"  field="contractId"    queryMode="single" dictionary="zml_loan_contract,id,contract_no"  width="120"></t:dgCol>
   <t:dgCol title="申请"  field="applId"    queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"    queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="放款编号"  field="payLoanNum"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="产品"  field="productId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作人ID"  field="operatorId"    queryMode="single" dictionary="t_s_base_user,id,realname"  width="120"></t:dgCol>
   <t:dgCol title="币种"  field="currency"    queryMode="single" dictionary="currency" width="120"></t:dgCol>
   <t:dgCol title="放款日"  field="payDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="到期日"  field="arriveDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="利率"  field="contractRate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="逾期利率"  field="overduerate"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="合同金额"  field="contractAmt"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="服务费"  field="fee"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="放款金额"  field="payAmt"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"    queryMode="single" dictionary="pay_sts" width="120"></t:dgCol>
   <t:dgCol title="放款序号"  field="payLoanIndex"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否上传借据"  field="isUpload"    queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="额外费用"  field="loanPremium"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="付款类型"  field="paymentType"    queryMode="single" dictionary="payment_type" width="120"></t:dgCol>
   <t:dgCol title="银行名称"  field="bankName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="银行省份"  field="bankAddressProvince"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="银行城市"  field="bankAddressCity"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="银行"  field="bankBranch"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账号"  field="accountNumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="交易流水号"  field="tradeNo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlLoanPayLoanInfoController.do?doDel&id={id}"  urlclass="ace_button" urlfont="fa-trash-o"/>
   <t:dgToolBar title="录入" icon="icon-add" url="zmlLoanPayLoanInfoController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlLoanPayLoanInfoController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlLoanPayLoanInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlLoanPayLoanInfoController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="放款审核" icon="icon-edit" url="zmlLoanPayLoanInfoController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/pay_loan_info/zmlLoanPayLoanInfoList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlLoanPayLoanInfoListtb").find("input[name='payDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlLoanPayLoanInfoListtb").find("input[name='arriveDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlLoanPayLoanInfoController.do?upload', "zmlLoanPayLoanInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlLoanPayLoanInfoController.do?exportXls","zmlLoanPayLoanInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlLoanPayLoanInfoController.do?exportXlsByT","zmlLoanPayLoanInfoList");
}
 </script>