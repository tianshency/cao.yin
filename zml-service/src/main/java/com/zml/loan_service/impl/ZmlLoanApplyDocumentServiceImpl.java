package com.zml.loan_service.impl;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.loan.entity.ZmlLoanApplyDocumentEntity;
import com.zml.common.Constant;
import com.zml.enums.loan.LoanApplyDocType;
import com.zml.loan_service.ZmlLoanApplyDocumentServiceI;

@Service("zmlLoanApplyDocumentService")
@Transactional
public class ZmlLoanApplyDocumentServiceImpl extends CommonServiceImpl implements ZmlLoanApplyDocumentServiceI {

	
 	public void delete(ZmlLoanApplyDocumentEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanApplyDocumentEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanApplyDocumentEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanApplyDocumentEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanApplyDocumentEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanApplyDocumentEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanApplyDocumentEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("version", t.getVersion());
		map.put("update_date", t.getUpdateDate());
		map.put("user_id", t.getUserId());
		map.put("appl_id", t.getApplId());
		map.put("doc_type", t.getDocType());
		map.put("file_flag", t.getFileFlag());
		map.put("image_path", t.getImagePath());
		map.put("file_path", t.getFilePath());
		map.put("details", t.getDetails());
		map.put("seq_no", t.getSeqNo());
		map.put("approval_flag", t.getApprovalFlag());
		map.put("approval_user_id", t.getApprovalUserId());
		map.put("approval_date", t.getApprovalDate());
		map.put("approval_opinion", t.getApprovalOpinion());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanApplyDocumentEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{appl_id}",String.valueOf(t.getApplId()));
 		sql  = sql.replace("#{doc_type}",String.valueOf(t.getDocType()));
 		sql  = sql.replace("#{file_flag}",String.valueOf(t.getFileFlag()));
 		sql  = sql.replace("#{image_path}",String.valueOf(t.getImagePath()));
 		sql  = sql.replace("#{file_path}",String.valueOf(t.getFilePath()));
 		sql  = sql.replace("#{details}",String.valueOf(t.getDetails()));
 		sql  = sql.replace("#{seq_no}",String.valueOf(t.getSeqNo()));
 		sql  = sql.replace("#{approval_flag}",String.valueOf(t.getApprovalFlag()));
 		sql  = sql.replace("#{approval_user_id}",String.valueOf(t.getApprovalUserId()));
 		sql  = sql.replace("#{approval_date}",String.valueOf(t.getApprovalDate()));
 		sql  = sql.replace("#{approval_opinion}",String.valueOf(t.getApprovalOpinion()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}

	@Override
	public Map findDocByApplyUserId(String applyId, String userId) { 
		List<Map> rsList = new ArrayList();
		String sqlWhere = " appl_id = '"+ applyId +"'";
		String sql = "select t.doc_type docType,count(1) docCount "
		            + " from zml_loan_apply_document t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " group by doc_type ";
		//查询用户贷款申请都上传了哪些类型文档
		List<Map<String, Object>> typeList = findForJdbc(sql, null);
		//rsMap.put("docType", typeList);
		sql = "select t.doc_type docType,t.file_path filePath, t.seq_no seqNo, t.details"
				+ " ,t.approval_flag approvalFlag, t.approval_opinion approvalOpinion "
	          + " from zml_loan_apply_document t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " order by doc_type ";
		//查询用户贷款申请都上传的所有文档
		List<Map<String, Object>> docList = findForJdbc(sql, null);
		StringBuffer html1 = new StringBuffer();
		StringBuffer html2 = new StringBuffer();
		//int i = 0;
		//遍历所有类型
		for(Map<String, Object> typeMap : typeList){
			Map temp = new HashMap();
			String docType = (String)typeMap.get("docType");
			temp.put("typeCode", docType);
			temp.put("typeName", LoanApplyDocType.getLoanApplyDocTypeDesc(docType));
			temp.put("typeCount",(Long)typeMap.get("docCount"));
			List<Map<String, Object>> tempList = new ArrayList();
			for(Map<String, Object> docMap : docList){
				if(docType.equals((String)docMap.get("docType"))){
					tempList.add(docMap);
				}
			}
			temp.put("typeOneImg", tempList.get(0).get("filePath"));
			temp.put("docList", tempList);
			rsList.add(temp);
		}
		int rsSize = rsList.size();
		html1.append("<ul>");
		StringBuffer scriptSb = new StringBuffer();
		StringBuffer styleContainer = new StringBuffer();
		StringBuffer styleGallery = new StringBuffer();
		//循环所有类型，按照类型拼 html代码
		for(int i=0;i<rsSize;i++){
			Map temp = rsList.get(i);
			String tpyeCode = (String)temp.get("typeCode");
			//按照类型 查询 该类型 对应已经 保存的数据
			Map dataMap = findDocTypeData(tpyeCode, applyId, userId);
			html1.append("<li class='classify-li docType"+i+"'>");
    		html1.append("	<div>");
    		html1.append("		<img src='"+ Constant.STATIC_URL+temp.get("typeOneImg")+"' alt='' />");
    		html1.append("	</div>");
    		html1.append("	<p>"+temp.get("typeName")+"("+temp.get("typeCount")+")</p>");
    		html1.append("</li>");
    		html2.append("<form id='form"+tpyeCode+"' action=\"\">");
    		if(dataMap != null)
    			html2.append("<input type='hidden' id='id"+tpyeCode+"' name='id' value='" + dataMap.get("id") + "'>");
    		else
    			html2.append("<input type='hidden' id='id"+tpyeCode+"' name='id' value=''>");
    		html2.append("<div id='"+"container" +i+"'>");
    		html2.append("<div class='container-main'>");
    		html2.append("	<div id='gallery"+i+"' class='ad-gallery'>");
    		html2.append("		<div class='ad-image-wrapper'>");
    		html2.append("		</div>");
    		html2.append("		<div class='ad-controls'>");
    		html2.append("		</div>");
    		html2.append("		<div class='ad-nav'>");
    		html2.append("			<div class='ad-thumbs'>");
    		html2.append("				<ul class='ad-thumb-list'>");
    		List<Map<String, Object>> tempList = (List)temp.get("docList");
    		for (int j = 0; j < tempList.size(); j++) {
    			String imgPath = (String)tempList.get(j).get("filePath");
    			html2.append("<li>");
    			html2.append("	<a href='"+ Constant.STATIC_URL +imgPath+"'>");
    			html2.append("	<img src='"+ Constant.STATIC_URL + imgPath+"' title='' class='image6'>");
    			html2.append("	</a>");
    			html2.append("</li>");
			}
    		html2.append("</ul>");
    		html2.append("	</div>");
    		html2.append("</div>");
    		html2.append("</div>");
    		html2.append("<div class='img-btn'>");
    		html2.append("<div class='examine-result' id='approveDiv"+tpyeCode+"'>");
    		html2.append("<h2>审核结果：</h2>");
    		html2.append("<div>");
    		html2.append("		<input type='radio' name='examine' id='pass4' value='pass4'/>");
    		html2.append("		<label for='pass4'>通过</label>");
    		/*html2.append("		</div>");
    		html2.append("	<div>");*/
    		html2.append("		<input type='radio' name='examine' id='NoPass4' value='NoPass4' />");
    		html2.append("		<label for='NoPass4'>不通过</label>");
    		html2.append("	</div>");
    		html2.append("</div>");
    		html2.append("<div class='examine-opinion'>");
    		//html2.append("<h2>审核意见：</h2>");
    		html2.append("<textarea name='opinion' id='opinion' placeholder='审核意见：100字以内' maxlength='100'></textarea>");
    		html2.append("<p class='examine-opinion-submit'>");
    		html2.append("	<button type='button' onclick='commitData(\""+tpyeCode+"\")'>提  交</button>");
    		html2.append("</p>");
    		html2.append("</div>");
    		html2.append("<i class='close-img'></i>");
    		html2.append("</div>");
    		html2.append("	</div>");
    		html2.append("</div>");
    		html2.append("</form>");
    		//增加点击事件
    		String docTypeClass = "docType" + i;
    		String docImgClass = "container" +i;
    		scriptSb.append("$(\"."+docTypeClass+"\").on(\"click\",function(){\n");
			for(int m=0;m<rsSize;m++){
				if(i == m){
					scriptSb.append("$(\"#" + docImgClass+"\").show();\n");
				}else{
					scriptSb.append("$(\"#container" + m+"\").hide();\n");
				}
			}
			scriptSb.append("$(\".examine-classify\").hide();\n");
			scriptSb.append("insertControls('"+tpyeCode+"','"+userId+"','"+applyId+"');\n");
			scriptSb.append("});\n");
    		scriptSb.append("$(\".close-img\").click(function(){\n");
    		scriptSb.append("$(\"#"+ docImgClass +"\").hide();\n");
    		scriptSb.append("$(\".examine-classify\").show();\n");
    		scriptSb.append("});\n");
    		if(i == rsSize -1){
    			styleContainer.append("#" + docImgClass + "{\n");
    			styleGallery.append("#gallery" + i + "{\n");
    		}else{
    			styleContainer.append("#" + docImgClass + ",\n");
    			styleGallery.append("#gallery" + i + ",\n");
    		}
		}
		html1.append("</ul>");
		
		/*for (int k = 0; k < rsSize; k++) {
			String docTypeClass = "docType" + k;
    		String docImgClass = "container" +k;
    		scriptSb.append("$(\"."+docTypeClass+"\").on(\"click\",function(){\n");
			for(int m=0;m<rsSize;m++){
				if(k == m){
					scriptSb.append("$(\"#" + docImgClass+"\").show();\n");
				}else{
					scriptSb.append("$(\"#container" + m+"\").hide();\n");
				}
			}
			scriptSb.append("$(\".examine-classify\").hide();\n");
			//scriptSb.append("alert('"+docTypeClass+"');\n");
			scriptSb.append("});\n");
    		scriptSb.append("$(\".close-img\").click(function(){\n");
    		scriptSb.append("$(\"#"+ docImgClass +"\").hide();\n");
    		scriptSb.append("$(\".examine-classify\").show();\n");
    		scriptSb.append("});\n");
    		if(k == rsSize -1){
    			styleContainer.append("#" + docImgClass + "{\n");
    			styleGallery.append("#gallery" + k + "{\n");
    		}else{
    			styleContainer.append("#" + docImgClass + ",\n");
    			styleGallery.append("#gallery" + k + ",\n");
    		}
		}*/
		styleContainer.append("width: 100%;\n");
		styleContainer.append("background: #000;\n");
		styleContainer.append("height: 700px;\n");
		styleContainer.append("position: fixed;\n");
		styleContainer.append("top: 0;\n");
		styleContainer.append("left: 0;\n");
		styleContainer.append("bottom: 0;\n");
		styleContainer.append("z-index: 1000;\n");
		styleContainer.append("}\n");
		
		styleGallery.append("width: 60%;\n");
		styleGallery.append("height: 400px;\n");
		styleGallery.append(" float: left;\n");
		styleGallery.append("margin-left: 2.5%;\n");
		styleGallery.append("}\n");
		
		Map rsMap = new HashMap();
		rsMap.put("html1", html1.toString());
		rsMap.put("html2", html2.toString());
		rsMap.put("scriptSb", scriptSb.toString());
		rsMap.put("styleContainer", styleContainer.toString());
		rsMap.put("styleGallery", styleGallery.toString());
		return rsMap;
	}
 	//
	public Map findDocTypeData(String docType, String applyId, String userId) {
		List<Map> rsList = new ArrayList();
		String sqlWhere = "";// " appl_id = '"+ applyId +"'";
		String sql = "";
		if(docType.equals(LoanApplyDocType.ID_CARD_POSITIVE.getStatusValue())){
			sql += "select * from zml_user where id ='"+userId+"'";
		}else if(docType.equals(LoanApplyDocType.LAND_SOURCE.getStatusValue())){
			sql += "select * from zml_loan_lan_info where user_id='"+userId+"' and appl_id ='"+applyId+"'";
		}else if(docType.equals(LoanApplyDocType.DEED.getStatusValue())){
			sql += "select * from zml_loan_estate_info where user_id='"+userId+"' and appl_id ='"+applyId+"'";
		}else if(docType.equals(LoanApplyDocType.DRIVER_LICENSE.getStatusValue())){
			
		}
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		//查询用户贷款申请都上传了哪些类型文档
		Map<String, Object> rsMap = null;
		if(sql != null && !"".equals(sql)){
			findOneForJdbc(sql, null);
		}
		return rsMap;
	}

	@Override
	public List<ZmlLoanApplyDocumentEntity> findDocByApplyId(String applyId) {
		String sqlWhere = " appl_id = '"+ applyId +"'";
		String sql = "select id,create_date createDate,update_date updateDate,version version,appl_id applId,user_id userId,doc_type docType,"
		+"file_flag fileFlag,image_path imagePath,file_path filePath,details details,seq_no seqNo from zml_loan_apply_document t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " order by create_date ";
		List<Map<String, Object>> list = findForJdbc(sql, null);
		ZmlLoanApplyDocumentEntity applyDoc = new ZmlLoanApplyDocumentEntity();
		List<ZmlLoanApplyDocumentEntity> docList = new ArrayList();
		try {
			if(list != null && list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					MyBeanUtils.copyMap2Bean(applyDoc, list.get(i));
					docList.add(applyDoc);
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return docList;
	}
}