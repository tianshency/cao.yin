<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="zmlUserRecordList" checkbox="true" fitColumns="false" title="用户信息记录" actionUrl="zmlUserRecordController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="创建日期"  field="createDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="更新日期"  field="updateDate" formatter="yyyy-MM-dd" hidden="true"  queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="版本"  field="version"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="申请"  field="applyId"    queryMode="single" dictionary="zml_loan_application,id,subject"  width="120"></t:dgCol>
   <t:dgCol title="用户"  field="userId"  hidden="true"  queryMode="single" dictionary="zml_user,id,real_name"  width="120"></t:dgCol>
   <t:dgCol title="真实姓名"  field="realName"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="用户类型"  field="type"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="电话"  field="phone"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="身份证号码"  field="identificationNumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="生日"  field="birthDay" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="年龄"  field="age"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="sex"    queryMode="single" dictionary="sex" width="120"></t:dgCol>
   <t:dgCol title="户口所在地"  field="accountLocation"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="发证机关"  field="issuingAuthority"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期起"  field="validStart" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="有效期至"  field="validEnd" formatter="yyyy-MM-dd"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="是否实名认证"  field="isVerified"    queryMode="single" dictionary="sf_yn" width="120"></t:dgCol>
   <t:dgCol title="实名认证方式"  field="verifiedMode"    queryMode="single" dictionary="verified_mode" width="120"></t:dgCol>
   <t:dgCol title="昵称"  field="wxNickname"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="性别"  field="wxSex"    queryMode="single" dictionary="sex" width="120"></t:dgCol>
   <t:dgCol title="国家"  field="wxCountry"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="省份"  field="wxProvince"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="城市"  field="wxCity"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="上次修改时间"  field="wxSubscribeTime" formatter="yyyy-MM-dd hh:mm:ss"   queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="列表"  field="wxTagidList"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="订阅"  field="wxSubscribe"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="语言"  field="wxLanguage"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="OpenId"  field="wxOpenid"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="组ID"  field="wxGroupid"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="头像"  field="wxHeadimgurl"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="备注"  field="wxRemark"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="账号"  field="accountNumber"    queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="zmlUserRecordController.do?doDel&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="zmlUserRecordController.do?goAdd" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="zmlUserRecordController.do?goUpdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="zmlUserRecordController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="zmlUserRecordController.do?goUpdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>
   <t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>
   <t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/zml_loan/zmlUserRecord/zmlUserRecordList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 		//给时间控件加上样式
 			$("#zmlUserRecordListtb").find("input[name='createDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserRecordListtb").find("input[name='updateDate']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserRecordListtb").find("input[name='birthDay']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserRecordListtb").find("input[name='validStart']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserRecordListtb").find("input[name='validEnd']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 			$("#zmlUserRecordListtb").find("input[name='wxSubscribeTime']").attr("class","Wdate").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'zmlUserRecordController.do?upload', "zmlUserRecordList");
}

//导出
function ExportXls() {
	JeecgExcelExport("zmlUserRecordController.do?exportXls","zmlUserRecordList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("zmlUserRecordController.do?exportXlsByT","zmlUserRecordList");
}
 </script>