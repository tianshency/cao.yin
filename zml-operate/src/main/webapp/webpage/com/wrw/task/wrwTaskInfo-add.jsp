<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>任务表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  $(document).ready(function(){
	$('#tt').tabs({
	   onSelect:function(title){
	       $('#tt .panel-body').css('width','auto');
		}
	});
	$(".tabs-wrap").css('width','100%');
  });
 </script>
 </head>
 <body style="overflow-x: hidden;">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="wrwTaskInfoController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${wrwTaskInfoPage.id }">
					<input id="createName" name="createName" type="hidden" value="${wrwTaskInfoPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${wrwTaskInfoPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${wrwTaskInfoPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${wrwTaskInfoPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${wrwTaskInfoPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${wrwTaskInfoPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${wrwTaskInfoPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${wrwTaskInfoPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${wrwTaskInfoPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">任务名称:</label>
			</td>
			<td class="value">
		     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"datatype="*" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">任务名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">数量:</label>
			</td>
			<td class="value">
		     	 <input id="amout" name="amout" type="text" style="width: 150px" class="inputxt"datatype="*n" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数量</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">开始日期:</label>
			</td>
			<td class="value">
					  <input id="startDate" name="startDate" type="text" style="width: 150px" 
		      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  datatype="*">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">开始日期</label>
			</td>
			<td align="right">
				<label class="Validform_label">结束日期:</label>
			</td>
			<td class="value">
					  <input id="endDate" name="endDate" type="text" style="width: 150px" 
		      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  datatype="*">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">结束日期</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">状态:</label>
			</td>
			<td class="value">
					<t:dictSelect field="status" type="list"
						typeGroupCode=""  hasLabel="false"  title="状态"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">状态</label>
			</td>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
				 <textarea id="remark" style="width:600px;" class="inputxt" rows="6" name="remark"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">剩余数量:</label>
			</td>
			<td class="value">
		     	 <input id="laveAmout" name="laveAmout" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">剩余数量</label>
			</td>
			<td align="right">
				<label class="Validform_label">任务链接:</label>
			</td>
			<td class="value">
		     	 <input id="taskUrl" name="taskUrl" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">任务链接</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="wrwTaskInfoController.do?wrwTaskUserRelationList&id=${wrwTaskInfoPage.id}&id=${wrwTaskInfoPage.id}" icon="icon-search" title="任务人" id="wrwTaskUserRelation"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_wrwTaskUserRelation_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
							<t:dictSelect field="wrwTaskUserRelationList[#index#].taskId" type="list"
										dictTable="wrw_task_infno" dictField="id" dictText="name" defaultVal="" hasLabel="false"  title="任务"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">任务</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="wrwTaskUserRelationList[#index#].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="user_name" defaultVal="" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml_loan/wrw_task/wrwTaskInfo.js"></script>
	