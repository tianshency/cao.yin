<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>黑名单表</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="zmlLoanBlackListController.do?doAdd" >
					<input id="id" name="id" type="hidden" value="${zmlLoanBlackListPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanBlackListPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanBlackListPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanBlackListPage.version }">
	<table cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">用户:</label>
			</td>
			<td class="value">
					<t:dictSelect field="userId" type="list"
						dictTable="zml_user" dictField="id" dictText="real_name"  hasLabel="false"  title="用户"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">用户</label>
			</td>
			<td align="right">
				<label class="Validform_label">类型:</label>
			</td>
			<td class="value">
					<t:dictSelect field="type" type="list"
						typeGroupCode="black_list_type"  hasLabel="false"  title="类型"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">来源:</label>
			</td>
			<td class="value">
					<t:dictSelect field="source" type="list"
						typeGroupCode="black_list_source"  hasLabel="false"  title="来源"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">来源</label>
			</td>
			<td align="right">
				<label class="Validform_label">数据:</label>
			</td>
			<td class="value">
		     	 <input id="data" name="data" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">数据</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">备注:</label>
			</td>
			<td class="value">
				 <textarea id="remarks" style="width:600px;" class="inputxt" rows="6" name="remarks"></textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
			<td align="right">
				<label class="Validform_label">审批标识:</label>
			</td>
			<td class="value">
					<t:dictSelect field="approvalFlag" type="list"
						typeGroupCode="approval_flag"  hasLabel="false"  title="审批标识"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审批标识</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">审批人:</label>
			</td>
			<td class="value">
					<t:dictSelect field="approvalUserId" type="list"
						dictTable="t_s_base_user" dictField="id" dictText="realname"  hasLabel="false"  title="审批人"></t:dictSelect>     
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审批人</label>
			</td>
			<td align="right">
				<label class="Validform_label">审批时间:</label>
			</td>
			<td class="value">
					  <input id="approvalDate" name="approvalDate" type="text" style="width: 150px" 
		      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" >
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审批时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">审批意见:</label>
			</td>
			<td class="value">
		     	 <input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt">
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">审批意见</label>
			</td>
		</tr>
	</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:800px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="zmlLoanBlackListController.do?zmlLoanBlackListDocumentList&id=${zmlLoanBlackListPage.id}" icon="icon-search" title="黑名单文档" id="zmlLoanBlackListDocument"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 附表明细 模版 -->
	<table style="display:none">
	<tbody id="add_zmlLoanBlackListDocument_table_template">
		<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left">
							<t:dictSelect field="zmlLoanBlackListDocumentList[#index#].userId" type="list"
										dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="" hasLabel="false"  title="用户"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">用户</label>
				  </td>
				  <td align="left">
							<t:dictSelect field="zmlLoanBlackListDocumentList[#index#].fileFlag" type="list"
										typeGroupCode="file_flag" defaultVal="" hasLabel="false"  title="标识"></t:dictSelect>     
					  <label class="Validform_label" style="display: none;">标识</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[#index#].imagePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">图片路径</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[#index#].filePath" maxlength="200" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">文件路径</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[#index#].details" maxlength="500" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">描述</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[#index#].seqNo" maxlength="4" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">文档序号</label>
				  </td>
				  <td align="left">
					  	<input name="zmlLoanBlackListDocumentList[#index#].archivesNo" maxlength="50" 
					  		type="text" class="inputxt"  style="width:120px;" >
					  <label class="Validform_label" style="display: none;">档案编号</label>
				  </td>
			</tr>
		 </tbody>
		</table>
 </body>
 <script src = "webpage/com/zml_loan/black_list/zmlLoanBlackList.js"></script>
	