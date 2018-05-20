<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserReleaseInfoList" checkbox="true" fitColumns="false" title="发布信息" actionUrl="zmlUserReleaseInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建人名称"  field="createName"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发布人"  field="userId"    queryMode="single" dictionary="zml_user,id,user_name"  width="120"></t:dgCol>
   <t:dgCol title="发布人名称"  field="userName"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发布人电话"  field="userPhone"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发布类型"  field="releaseType"   query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="封面照片"  field="coverImg"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量"  field="amount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="数量单位"  field="amountUnit"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="价格"  field="price"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="价格单位"  field="priceUnit"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效开始日期"  field="validStartDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效结束日期"  field="validEndDate" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="描述"  field="description"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remarks"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="状态"  field="status"   query="true" queryMode="single" dictionary="release_sts" width="120"></t:dgCol>
   <t:dgCol title="浏览数量"  field="viewCount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="收藏数量"  field="collectionCount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="关注数量"  field="attentionCount"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否审核"  field="isVerify"    queryMode="single" dictionary="is_verify" width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserReleaseInfoController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserReleaseInfoController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserReleaseInfoController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserReleaseInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserReleaseInfoController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
   <t:dgToolBar title="审批" icon="icon-edit" url="zmlUserReleaseInfoController.do?goApproval" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/release_info/zmlUserReleaseInfoList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserReleaseInfoListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseInfoListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseInfoListtb").find("input[name='validStartDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserReleaseInfoListtb").find("input[name='validEndDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserReleaseInfoController.do?upload', "zmlUserReleaseInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserReleaseInfoController.do?exportXls","zmlUserReleaseInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserReleaseInfoController.do?exportXlsByT","zmlUserReleaseInfoList");
}
 </script>