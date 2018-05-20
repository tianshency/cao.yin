package com.zml.loan_service.impl;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.loan.entity.ZmlLoanApproveEntity;
import com.zml.loan_service.ZmlLoanApproveServiceI;

@Service("zmlLoanApproveService")
@Transactional
public class ZmlLoanApproveServiceImpl extends CommonServiceImpl implements ZmlLoanApproveServiceI {

	
 	public void delete(ZmlLoanApproveEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanApproveEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanApproveEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanApproveEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanApproveEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanApproveEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanApproveEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("user_id", t.getUserId());
		map.put("appl_id", t.getApplId());
		map.put("credit_id", t.getCreditId());
		map.put("credit_amount", t.getCreditAmount());
		map.put("fee", t.getFee());
		map.put("approve_amount", t.getApproveAmount());
		map.put("interest_rate", t.getInterestRate());
		map.put("term", t.getTerm());
		map.put("term_unit", t.getTermUnit());
		map.put("argee_amount", t.getArgeeAmount());
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
 	public String replaceVal(String sql,ZmlLoanApproveEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{appl_id}",String.valueOf(t.getApplId()));
 		sql  = sql.replace("#{credit_id}",String.valueOf(t.getCreditId()));
 		sql  = sql.replace("#{credit_amount}",String.valueOf(t.getCreditAmount()));
 		sql  = sql.replace("#{fee}",String.valueOf(t.getFee()));
 		sql  = sql.replace("#{approve_amount}",String.valueOf(t.getApproveAmount()));
 		sql  = sql.replace("#{interest_rate}",String.valueOf(t.getInterestRate()));
 		sql  = sql.replace("#{term}",String.valueOf(t.getTerm()));
 		sql  = sql.replace("#{term_unit}",String.valueOf(t.getTermUnit()));
 		sql  = sql.replace("#{argee_amount}",String.valueOf(t.getArgeeAmount()));
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
	public List<Map<String, Object>> findByApplyId(String applyId, String sts) {
		String sqlWhere = " t.appl_id = '"+ applyId +"'";
		if(sts != null && !"".equals(sts)){
			sqlWhere += " and t.approval_flag = '"+sts+"'";
		}
		String sql = "select t.id,t.fee fee,t.approve_amount approveAmount, t.interest_rate interestRate,t.term, "
                    + "t.term_unit termUnit, t.appl_id applId, t.user_id userId "
		            + "from zml_loan_approve t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY t.create_date desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		return mapList;
	}
}