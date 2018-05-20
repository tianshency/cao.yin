<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlMerchantsList" checkbox="true" fitColumns="false" title="商户信息" actionUrl="zmlMerchantsController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="商户名称"  field="merchantsName" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商家地址"  field="merchantsAdress"  query="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商家描述"  field="merchantsDescription"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="营业执照"  field="businesslicense"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标志"  field="logo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="封面图片"  field="coverImg"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="客服电话"  field="consumerHotline"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="负责人"  field="leader"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="负责人电话"  field="leaderTelephone"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="评级"  field="grade"  query="true"  queryMode="single" dictionary="merchants_grade" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlMerchantsController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlMerchantsController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlMerchantsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlMerchantsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlMerchantsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/merchants/zmlMerchantsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlMerchantsListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlMerchantsListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlMerchantsController.do?upload', "zmlMerchantsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlMerchantsController.do?exportXls","zmlMerchantsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlMerchantsController.do?exportXlsByT","zmlMerchantsList");
}
 </script>