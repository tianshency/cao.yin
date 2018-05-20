<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>借款申请</title>
		<!--标准mui.css-->
		<link rel="stylesheet" href="${ctxContents}css/mui.min.css">
		<link rel="stylesheet" href="${ctxContents}css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctxContents}css/bootstrap-theme.min.css" />
		<link rel="stylesheet" href="${ctxContents}css/common.css" />
		<link rel="stylesheet" href="${ctxContents}css/personImgUpload.css" />
  		<link rel="stylesheet" href="${ctxContents }css/alertTip/sweet-alert.css">	
		<script type="text/javascript" src="${ctxContents }/js/common/jquery-1.11.3.min.js"></script>
		<script type="text/javascript" src="${ctxContents}js/bootstrap.min.js"></script>
		<script src="${ctxContents }js/alerttip/sweet-alert.js"></script>
		<style type="text/css">
			.top_div{
				margin-bottom:46px;
				height:95%;
				overflow-y:auto;
			}	
			a {
			    color: #d9edf7;
			    text-decoration: none;
			}
			.form-group:nth-child(2) input {
			    display: block;
			    width: 50%;
			    float: left;
			}	
		</style>
	</head>

	<body>
		<div class="top_div">
			<div class="mui-content header_car">
				<div>
					<div id="segmentedControl" class="mui-segmented-control">
						<a class="mui-control-item mui-active" href="#item1">基本信息</a>
						<a class="mui-control-item" href="#item2">联系人</a>
					</div>
				</div>
			</div>
			<div class="mui-control-content mui-active" id="item1">
						<div class="content_main">
							<form class="form-horizontal" role="form">
								<div class="main_detail">
									<div class="form-group">
										<label class="control-label" for="phone"><span class="text-muted">*</span>手&nbsp; 机&nbsp; 号:</label>
										<div class="col-sm-4">
											<input class="form-control" id="phone" type="tel" value="${loanApplication.phone }" placeholder="请输入手机号，方便我们为您服务" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="ds_host"><span class="text-muted">*</span>验&nbsp; 证 &nbsp;码:</label>
										<div class="col-sm-4">
											<input class="form-control" id="ds_host" type="text" placeholder="请输入验证码" /><button>60秒后重发</button>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="amount"><span class="text-muted">*</span>借款金额:</label>
										<div class="col-sm-4">
											<input class="form-control" id="amount" value="${loanApplication.amount }" type="number" placeholder="请输入借款金额" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="amount"><span class="text-muted">*</span>借款期限:</label>
										<div class="col-sm-4">
											<select class="form-control" id="productId">
										      <option value="">默认选择</option>
										       <option value="1">1</option>
										    </select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="amount"><span class="text-muted">*</span>利率:</label>
										<div class="col-sm-4">
											<select class="form-control" id="rate">
										      <option value="">默认选择</option>
										       <option value="1">1</option>
										    </select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="amount"><span class="text-muted">*</span>利息:</label>
										<div class="col-sm-4">
											<select class="form-control" id="rate_money">
										      <option value="">默认选择</option>
										       <option value="1">1</option>
										    </select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label">&nbsp;&nbsp;友情提示:</label>
										<div class="col-sm-4">
											所有上传图片请保证效果清晰，无反光，证件的四边包括存放证件的塑料壳全部需拍摄在图片内，不能有缺边缺角的现象。
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="sfz"><span class="text-muted">*</span>身份证号:</label>
										<div class="col-sm-4 controls">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="sfz" name="upload" class="fileclass" onchange="document.getElementById('sfz_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/idc_z.jpg" id="sfz_img" class="fileimg" onclick="clickFile(this,1,'sfz',0);">
										</div>
										<div class="col-sm-2 controls">
											<input type="file" id="sfb" name="upload" class="fileclass" onchange="document.getElementById('sfb_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/idc_b.jpg" id="sfb_img" class="fileimg" onclick="clickFile(this,2,'sfb',0);">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="sf" name="upload" class="fileclass" onchange="document.getElementById('sf_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/idc_z.jpg" id="sf_img" class="fileimg" onclick="clickFile(this,3,'sf',0);">
										</div>
									</div>
									<%-- <div class="form-group">
										<label class="control-label" for="bankOfDepositName"><span class="text-muted">*</span>开户支行:</label>
										<div class="col-sm-4 controls">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="bank_z" class="fileclass">
											<img src="${ctxContents}img/bank_z.jpg" class="fileimg" onclick="clickFile(1,'sfz');">
										</div>
										<div class="col-sm-2 controls">
											<input type="file" id="bank_b" name="upload" class="fileclass" onclick="clickFile(1,'sfz');">
											<img src="${ctxContents}img/bank_b.jpg" class="fileimg">
										</div>
									</div> --%>
									<div class="form-group">
										<label class="control-label" for="bankOfDepositName"><span class="text-muted">*</span>房照上传:</label>
										<div class="col-sm-4 controls">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="fchz" name="upload" class="fileclass" onchange="document.getElementById('fchz_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/jzh_z.jpg" id="fchz_img" class="fileimg" onclick="clickFile(this,4,'fchz',0);">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="fchz1" name="upload" class="fileclass" onchange="document.getElementById('fchz1_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/jzh_z.jpg" id="fchz1_img" class="fileimg" onclick="clickFile(this,4,'fchz1',1);">
										</div>
										<div class="col-sm-2 controls">
											<input type="file" id="fchz2" name="upload" class="fileclass" onchange="document.getElementById('fchz2_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/jzh_z.jpg" id="fchz2_img" class="fileimg" onclick="clickFile(this,4,'fchz2',2);">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="fchz3" name="upload" class="fileclass" onchange="document.getElementById('fchz3_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/jzh_z.jpg" id="fchz3_img" class="fileimg" onclick="clickFile(this,4,'fchz3',3);">
										</div>
										<div class="col-sm-2 controls">
											<input type="file" id="fchz4" name="upload" class="fileclass">
											<img src="${ctxContents}img/jzh_z.jpg" class="fileimg" onclick="clickFile(this,4,'fchz4',4);">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="bankOfDepositName"><span class="text-muted">*</span>土地本上传:</label>
										<div class="col-sm-4 controls">
										</div>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="tdb1" name="upload" class="fileclass" onchange="document.getElementById('td1_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/jzh_z.jpg" id="td1_img" class="fileimg" onclick="clickFile(this,4,'tdb1',0);">
										</div>
										<div class="col-sm-2 controls">
											<input type="file" id="tdb2" name="upload" class="fileclass" onchange="document.getElementById('td2_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/jzh_b.jpg" id="td2_img" class="fileimg" onclick="clickFile(this,4,'tdb2',0);">
										</div>
									</div>
									<div class="form-group">
										<div class="hk_upload">户口本上传:</div>
									</div>
									<div class="form-group_hk formclass">
										<span>户口本第一页注意事项页和户主页一起拍摄提交;个人信息页和住址变动登记页一起拍摄提交，三页户口的照片背景需一致。</span>
									</div>
									<div class="form-group_hk">
										<div class="col-sm-2 controls">
											<input type="file" id="hk1" name="upload" class="fileclass"  onchange="document.getElementById('hk1_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/hk-1.jpg" id="hk1_img" class="fileimg" onclick="clickFile(this,4,'hk1',0);">
										</div>
										<div class="col-sm-2 controls">
											<input type="file" id="hk2" name="upload" class="fileclass" onchange="document.getElementById('hk2_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/hk-2.jpg" id="hk2_img" class="fileimg" onclick="clickFile(this,4,'hk2',0);">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-2 controls">
											<input type="file" id="hk3" name="upload" class="fileclass" onchange="document.getElementById('hk3_img').src=getFullPath(this);" style="display:none;">
											<img src="${ctxContents}img/hk-3.jpg" id="hk3_img" class="fileimg" onclick="clickFile(4,'hk3',0);">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label" for="recommendUser">推荐人手机号:</label>
										<div class="col-sm-4">
											<input class="form-control" id="recommendUser" type="text" placeholder="如果有人推荐,请填写推荐人手机号码" />
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<div class="checkbox">
												<label>
													<input type="checkbox" name="userNotice" id="userNotice"> 已阅读并同意   <a>开户须知</a>
												</label>
											</div>
										</div>
									</div>
								</div>
							</form>
							
						</div>
			</div>
			<div class="mui-control-content" id="item2">
				<div class="content_main">
					<form class="form-horizontal" role="form">
						<input type="hidden" id="r_id"/>
						<div class="main_detail">
							<div class="form-group">
								<label class="control-label" for="name"><span class="text-muted">*</span>姓&nbsp; &nbsp; 名:</label>
								<div class="col-sm-4">
									<input class="form-control" id="name" type="text" placeholder="请输入联系人姓名" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label" for="r_phone"><span class="text-muted">*</span>手机号:</label>
								<div class="col-sm-4">
									<input class="form-control" id="r_phone" type="text" placeholder="请输入联系人手机号" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label" for="relation"><span class="text-muted">*</span>关&nbsp; &nbsp; 系:</label>
								<div class="col-sm-4">
									<input class="form-control" id="relation" type="text" placeholder="请选择与借款人关系" />
								</div>
							</div>
							<div class="form-group btn_other" style="display:none;">
								<button id="save_btn" type="button">保             存</button>
							</div>
						</div>
					</form>
				</div>
				<div class="table-responsive">
				  <table class="table table-hover" id="contractTab">
				    <caption>联系人列表</caption>
				    <thead>
							<tr>
								<th>姓名</th>
								<th>手机号</th>
								<th>与借款人关系</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="tbody">
							<tr id="template" style="display:none;">
								<td>1</td>
								<td>Anna</td>
								<td>父母</td>
								<td><a id="edit">编缉</a>  <a id="del">删除</a></td>
							</tr>
						</tbody>
				  </table>
				</div>
			</div>
		</div>
		<footer class="WaitingLoan-footer">
			<a href="javascript:void(-1);" onclick="submitLoadApplyInfo();">提交</a>
		</footer>
		<script src="${ctxContents}js/mui.min.js"></script>
		<script type="text/javascript" src="${ctxContents }/js/ajaxfileuploads.js" ></script>
		<script>
			mui.init();
			var applyId = "${loanApplication.id}";
			var listDoc = "${listDoc}";
			var productId = "${loanApplication.productId}";
			
		</script>
		<script src="${ctxContents}js/load/loadapply.js"></script>
		<script src="${ctxContents}js/load/uploadFile.js"></script>
	</body>

</html>