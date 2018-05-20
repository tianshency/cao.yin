<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>实际还款记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
		<link rel="stylesheet" href="plug-in/uploadify/css/uploadify.css" type="text/css" />
		<script type="text/javascript" src="plug-in/uploadify/jquery.uploadify-3.1.js"></script>
  <script type="text/javascript">
  //编写自定义JS代码
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="zmlLoanRepayRecordController.do?doAdd" tiptype="1" callback="jeecgFormFileCallBack@Override">
					<input id="id" name="id" type="hidden" value="${zmlLoanRepayRecordPage.id }">
					<input id="createDate" name="createDate" type="hidden" value="${zmlLoanRepayRecordPage.createDate }">
					<input id="updateDate" name="updateDate" type="hidden" value="${zmlLoanRepayRecordPage.updateDate }">
					<input id="version" name="version" type="hidden" value="${zmlLoanRepayRecordPage.version }">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							合同:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="contractId" type="list"
									dictTable="zml_loan_contract" dictField="id" dictText="contract_no" defaultVal="${zmlLoanRepayRecordPage.contractId}" hasLabel="false"  title="合同"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">合同</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							用户:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="userId" type="list"
									dictTable="zml_user" dictField="id" dictText="real_name" defaultVal="${zmlLoanRepayRecordPage.userId}" hasLabel="false"  title="用户"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">用户</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							还款计划:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="repayPlanDetailId" type="list"
									dictTable="zml_loan_repay_plan_detail" dictField="id" dictText="profit_period" defaultVal="${zmlLoanRepayRecordPage.repayPlanDetailId}" hasLabel="false"  title="还款计划"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款计划</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							还款时间:
						</label>
					</td>
					<td class="value">
							   <input id="repayTime" name="repayTime" type="text" style="width: 150px" 
					      						 class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款时间</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							实还本金:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayPrincipal" name="repayPrincipal" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实还本金</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							实还利息:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayInterest" name="repayInterest" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实还利息</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							实还罚息:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayPenalty" name="repayPenalty" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实还罚息</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							实还滞纳金:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayNonpayment" name="repayNonpayment" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实还滞纳金</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							实还其他费用:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayOtherFee" name="repayOtherFee" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">实还其他费用</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							还款金额:
						</label>
					</td>
					<td class="value">
					     	 <input id="repayAmt" name="repayAmt" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款金额</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							还款渠道:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="repayChannel" type="list"
									typeGroupCode="repay_channel" defaultVal="${zmlLoanRepayRecordPage.repayChannel}" hasLabel="false"  title="还款渠道"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款渠道</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							还款凭证:
						</label>
					</td>
					<td class="value">
								<table></table>
								<div class="form jeecgDetail"> 
									<script type="text/javascript">
										var serverMsg="";
										$(function(){
											$('#repayCertificate').uploadify({
												buttonText:'添加文件',
												auto:false,
												progressData:'speed',
												multi:true,
												height:25,
												overrideEvents:['onDialogClose'],
												fileTypeDesc:'文件格式:',
												queueID:'filediv_file',
												fileTypeExts:'*.rar;*.zip;*.doc;*.docx;*.txt;*.ppt;*.xls;*.xlsx;*.html;*.htm;*.pdf;*.jpg;*.gif;*.png',
												fileSizeLimit:'15MB',
												swf:'plug-in/uploadify/uploadify.swf',	
												uploader:'cgUploadController.do?saveFiles&jsessionid='+$("#sessionUID").val()+'',
												onUploadStart : function(file) { 
													var cgFormId=$("input[name='id']").val();
													$('#repayCertificate').uploadify("settings", "formData", {
														'cgFormId':cgFormId,
														'cgFormName':'zml_loan_repay_record',
														'cgFormField':'REPAY_CERTIFICATE'
													});
												} ,
												onQueueComplete : function(queueData) {
													 var win = frameElement.api.opener;
													 win.reloadTable();
													 win.tip(serverMsg);
													 frameElement.api.close();
												},
												onUploadSuccess : function(file, data, response) {
													var d=$.parseJSON(data);
													if(d.success){
														var win = frameElement.api.opener;
														serverMsg = d.msg;
													}
												},
												onFallback: function() {
								                    tip("您未安装FLASH控件，无法上传图片！请安装FLASH控件后再试")
								                },
								                onSelectError: function(file, errorCode, errorMsg) {
								                    switch (errorCode) {
								                    case - 100 : tip("上传的文件数量已经超出系统限制的" + $('#file').uploadify('settings', 'queueSizeLimit') + "个文件！");
								                        break;
								                    case - 110 : tip("文件 [" + file.name + "] 大小超出系统限制的" + $('#file').uploadify('settings', 'fileSizeLimit') + "大小！");
								                        break;
								                    case - 120 : tip("文件 [" + file.name + "] 大小异常！");
								                        break;
								                    case - 130 : tip("文件 [" + file.name + "] 类型不正确！");
								                        break;
								                    }
								                },
								                onUploadProgress: function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {}
											});
										});
									</script>
									<span id="file_uploadspan"><input type="file" name="repayCertificate" id="repayCertificate" /></span> 
								</div> 
								<div class="form" id="filediv_file"></div>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">还款凭证</label>
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
									typeGroupCode="repay_sts" defaultVal="${zmlLoanRepayRecordPage.status}" hasLabel="false"  title="状态"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">状态</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
					     	 <input id="remarks" name="remarks" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">备注</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审批标识:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="approvalFlag" type="list"
									typeGroupCode="approval_flag" defaultVal="${zmlLoanRepayRecordPage.approvalFlag}" hasLabel="false"  title="审批标识"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批标识</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							审批人:
						</label>
					</td>
					<td class="value">
							  <t:dictSelect field="approvalUserId" type="list"
									dictTable="t_s_base_user" dictField="id" dictText="realname" defaultVal="${zmlLoanRepayRecordPage.approvalUserId}" hasLabel="false"  title="审批人"></t:dictSelect>     
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批人</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							审批时间:
						</label>
					</td>
					<td class="value">
					     	 <input id="approvalDate" name="approvalDate" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批时间</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							审批意见:
						</label>
					</td>
					<td class="value">
					     	 <input id="approvalOpinion" name="approvalOpinion" type="text" style="width: 150px" class="inputxt" >
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">审批意见</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/zml_loan/repay_record/zmlLoanRepayRecord.js"></script>		
	  	<script type="text/javascript">
	  		function jeecgFormFileCallBack(data){
	  			if (data.success == true) {
					uploadFile(data);
				} else {
					if (data.responseText == '' || data.responseText == undefined) {
						$.messager.alert('错误', data.msg);
						$.Hidemsg();
					} else {
						try {
							var emsg = data.responseText.substring(data.responseText.indexOf('错误描述'), data.responseText.indexOf('错误信息'));
							$.messager.alert('错误', emsg);
							$.Hidemsg();
						} catch(ex) {
							$.messager.alert('错误', data.responseText + '');
						}
					}
					return false;
				}
				if (!neibuClickFlag) {
					var win = frameElement.api.opener;
					win.reloadTable();
				}
	  		}
	  		function upload() {
				$('#repayCertificate').uploadify('upload', '*');		
			}
			
			var neibuClickFlag = false;
			function neibuClick() {
				neibuClickFlag = true; 
				$('#btn_sub').trigger('click');
			}
			function cancel() {
				$('#repayCertificate').uploadify('cancel', '*');
			}
			function uploadFile(data){
				if(!$("input[name='id']").val()){
					if(data.obj!=null && data.obj!='undefined'){
						$("input[name='id']").val(data.obj.id);
					}
				}
				if($(".uploadify-queue-item").length>0){
					upload();
				}else{
					if (neibuClickFlag){
						alert(data.msg);
						neibuClickFlag = false;
					}else {
						var win = frameElement.api.opener;
						win.reloadTable();
						win.tip(data.msg);
						frameElement.api.close();
					}
				}
			}
	  	</script>
