<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>黑名单</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanBlackListController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlLoanBlackListPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanBlackListPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanBlackListPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanBlackListPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanBlackListPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							类型:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="type" type="list"
									typeGroupCode="black_list_type" defaultVal="${zmlLoanBlackListPage.type}" hasLabel="false"  title="类型"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">类型</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							来源:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="source" type="list"
									typeGroupCode="black_list_source" defaultVal="${zmlLoanBlackListPage.source}" hasLabel="false"  title="来源"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">来源</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							数据:
						</label>
					</td>
					<td class="value">
					     	 <input id="data" name="data" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">数据</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="remarks" name="remarks"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
				</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/black_list/zmlLoanBlackList.js"></script>		
