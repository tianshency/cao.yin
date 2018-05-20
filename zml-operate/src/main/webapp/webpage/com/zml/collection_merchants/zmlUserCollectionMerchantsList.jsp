<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserCollectionMerchantsList" checkbox="true" fitColumns="false" title="我的收藏商家" actionUrl="zmlUserCollectionMerchantsController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="用户ID"  field="userId"   query="true" queryMode="single" dictionary="zml_user,id,user_name"  width="120"></t:dgCol>
   <t:dgCol title="标签"  field="label"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商家类型"  field="collectionType"   query="true" queryMode="single" dictionary="crop_type" width="120"></t:dgCol>
   <t:dgCol title="商家ID"  field="merchantsId"   query="true" queryMode="single" dictionary="t_s_depart,org_code,departname"  width="120"></t:dgCol>
   <t:dgCol title="商家名称"  field="merchantsName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="商家logo"  field="merchantsLogo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="负责人"  field="principal"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="所在地"  field="address"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="网址"  field="url"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="collection_sts" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserCollectionMerchantsController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserCollectionMerchantsController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserCollectionMerchantsController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserCollectionMerchantsController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserCollectionMerchantsController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/collection_merchants/zmlUserCollectionMerchantsList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserCollectionMerchantsListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserCollectionMerchantsListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserCollectionMerchantsController.do?upload', "zmlUserCollectionMerchantsList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserCollectionMerchantsController.do?exportXls","zmlUserCollectionMerchantsList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserCollectionMerchantsController.do?exportXlsByT","zmlUserCollectionMerchantsList");
}
 </script>