<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlSysOpinionFeedbackInfoList" checkbox="true" fitColumns="false" title="意见反馈" actionUrl="zmlSysOpinionFeedbackInfoController.do?datagrid" idField="id" fit="true" queryMode="group">
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
   <t:dgCol title="用户ID"  field="userId"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="标题"  field="title"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="phone"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="内容"  field="content"    queryMode="single"  width="500"></t:dgCol>
   <t:dgCol title="图片一"  field="imgOne"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图片二"  field="imgTwo"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="图片三"  field="imgThree"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlSysOpinionFeedbackInfoController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlSysOpinionFeedbackInfoController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlSysOpinionFeedbackInfoController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlSysOpinionFeedbackInfoController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlSysOpinionFeedbackInfoController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml/opinion_feedback/zmlSysOpinionFeedbackInfoList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlSysOpinionFeedbackInfoListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlSysOpinionFeedbackInfoListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlSysOpinionFeedbackInfoController.do?upload', "zmlSysOpinionFeedbackInfoList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlSysOpinionFeedbackInfoController.do?exportXls","zmlSysOpinionFeedbackInfoList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlSysOpinionFeedbackInfoController.do?exportXlsByT","zmlSysOpinionFeedbackInfoList");
}
 </script>