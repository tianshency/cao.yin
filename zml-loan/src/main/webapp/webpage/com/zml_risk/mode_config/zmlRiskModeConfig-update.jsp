<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>模型配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlRiskModeConfigController.do?doUpdate" tiptype="1" >
					<input id="id" name="id" type="hidden" value="${zmlRiskModeConfigPage.id }">
					<input id="createName" name="createName" type="hidden" value="${zmlRiskModeConfigPage.createName }">
					<input id="createBy" name="createBy" type="hidden" value="${zmlRiskModeConfigPage.createBy }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlRiskModeConfigPage.createDate }">
					<input id="updateName" name="updateName" type="hidden" value="${zmlRiskModeConfigPage.updateName }">
					<input id="updateBy" name="updateBy" type="hidden" value="${zmlRiskModeConfigPage.updateBy }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlRiskModeConfigPage.updateDate }">
					<input id="sysOrgCode" name="sysOrgCode" type="hidden" value="${zmlRiskModeConfigPage.sysOrgCode }">
					<input id="sysCompanyCode" name="sysCompanyCode" type="hidden" value="${zmlRiskModeConfigPage.sysCompanyCode }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
					<tr>
						<td align="right">
							<label class="Validform_label">
								分类:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="classification" type="list"
										typeGroupCode="mode_class" defaultVal="${zmlRiskModeConfigPage.classification}" hasLabel="false"  title="分类"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分类</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								细分:
							</label>
						</td>
						<td class="value">
									<t:dictSelect field="subdivided" type="list"
										typeGroupCode="mode_subd" defaultVal="${zmlRiskModeConfigPage.subdivided}" hasLabel="false"  title="细分"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">细分</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								取值:
							</label>
						</td>
						<td class="value">
						     	 <input id="ranges" name="ranges" type="text" style="width: 150px" class="inputxt"  value='${zmlRiskModeConfigPage.ranges}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">取值</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								分值:
							</label>
						</td>
						<td class="value">
						     	 <input id="score" name="score" type="text" style="width: 150px" class="inputxt"  value='${zmlRiskModeConfigPage.score}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">分值</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								权重:
							</label>
						</td>
						<td class="value">
						     	 <input id="weight" name="weight" type="text" style="width: 150px" class="inputxt"  value='${zmlRiskModeConfigPage.weight}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">权重</label>
						</td>
					<tr>
						<td align="right">
							<label class="Validform_label">
								备注:
							</label>
						</td>
						<td class="value">
						  	 	<textarea id="remarkes" style="width:600px;" class="inputxt" rows="6" name="remarkes">${zmlRiskModeConfigPage.remarkes}</textarea>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								名称:
							</label>
						</td>
						<td class="value">
						     	 <input id="name" name="name" type="text" style="width: 150px" class="inputxt"  value='${zmlRiskModeConfigPage.name}'>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">名称</label>
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
  <script src = "webpage/com/zml_loan/mode_config/zmlRiskModeConfig.js"></script>		
