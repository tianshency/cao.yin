<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>搜索历史</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="cfUserSearchHistoryController.do?doAdd" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${cfUserSearchHistoryPage.id }">
					<input id="createName" name="createName" type="hidden" value="${cfUserSearchHistoryPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${cfUserSearchHistoryPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${cfUserSearchHistoryPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${cfUserSearchHistoryPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${cfUserSearchHistoryPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${cfUserSearchHistoryPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${cfUserSearchHistoryPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${cfUserSearchHistoryPage.sysCompanyCode }">
					<input id="version" name="version" type="hidden" value="${cfUserSearchHistoryPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="cf_user" dictField="id" dictText="name" defaultVal="${cfUserSearchHistoryPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							搜索内容:
						</label>
					</td>
					<td class="value">
						  	 <textarea style="width:600px;" class="inputxt" rows="6" id="content" name="content"></textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">搜索内容</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="status" type="list"
									typeGroupCode="search_sts" defaultVal="${cfUserSearchHistoryPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
				<td align="right">
					<label class="Validform_label">
					</label>
				</td>
				<td class="value">
				</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml/user_find/cfUserSearchHistory.js"></script>		
