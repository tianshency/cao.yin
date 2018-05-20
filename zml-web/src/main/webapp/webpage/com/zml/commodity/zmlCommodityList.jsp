<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlCommodityList" checkbox="true" fitColumns="false" title="商品" actionUrl="zmlCommodityController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="商户"  field="merchantsId"  hidden="true" query="true" queryMode="single" dictionary="t_s_depart,id,departname"  width="120"></t:dgCol>
   <t:dgCol title="商户名称"  field="manufacturers"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商品名称"  field="name"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="批次号"  field="batchNumber"  hidden="true" query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="介绍"  field="introduction"  hidden="true"  queryMode="single"  width="800"></t:dgCol>
   <t:dgCol title="总数量"  field="totalAmount"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量单位"  field="amountUnit"    queryMode="single" dictionary="amount_unit" width="120"></t:dgCol>
   <t:dgCol title="商品规格"  field="commercialSpecification"    queryMode="single" dictionary="specification" width="120"></t:dgCol>
   <t:dgCol title="规格重量"  field="specificationWeight"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="库存数量"  field="reserveAmount"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="原价格"  field="price"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="实际价格"  field="realPrice"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生产日期"  field="productionDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期"  field="periodOfValidity"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期单位"  field="povUnit"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否推荐"  field="isRecommend"   query="true" queryMode="single" dictionary="is_default" width="120"></t:dgCol>
   <t:dgCol title="是否热卖"  field="isHot"   query="true" queryMode="single" dictionary="is_default" width="120"></t:dgCol>
   <t:dgCol title="一级分类"  field="classifyOneLevel"    queryMode="single" dictionary="zml_commodity_classify,id,name"  width="120"></t:dgCol>
   <t:dgCol title="二级分类"  field="classifyTwoLevel"    queryMode="single" dictionary="zml_commodity_classify,id,name"  width="120"></t:dgCol>
   <t:dgCol title="商品描述"  field="details"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="commodity_sts" width="120"></t:dgCol>
   <t:dgCol title="运费"  field="fare"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlCommodityController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlCommodityController.do?goAdd" funname="add" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlCommodityController.do?goUpdate" funname="update" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlCommodityController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlCommodityController.do?goUpdate" funname="detail" width="100%" height="100%"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/commodity/zmlCommodityList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlCommodityListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlCommodityListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlCommodityListtb").find("input[name='productionDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlCommodityController.do?upload', "zmlCommodityList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlCommodityController.do?exportXls","zmlCommodityList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlCommodityController.do?exportXlsByT","zmlCommodityList");
}
 </script>