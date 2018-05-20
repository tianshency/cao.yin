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
import com.zml.base.loan.entity.ZmlLoanProductEntity;
import com.zml.loan_service.ZmlLoanProductServiceI;

@Service("zmlLoanProductService")
@Transactional
public class ZmlLoanProductServiceImpl extends CommonServiceImpl implements ZmlLoanProductServiceI {

	
 	public void delete(ZmlLoanProductEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanProductEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanProductEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanProductEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanProductEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanProductEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanProductEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("pro_name", t.getProName());
		map.put("periods", t.getPeriods());
		map.put("periods_unit", t.getPeriodsUnit());
		map.put("interest_rate", t.getInterestRate());
		map.put("penalty_rate", t.getPenaltyRate());
		map.put("min_amount", t.getMinAmount());
		map.put("max_amount", t.getMaxAmount());
		map.put("wf_id", t.getWfId());
		map.put("details", t.getDetails());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanProductEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{pro_name}",String.valueOf(t.getProName()));
 		sql  = sql.replace("#{periods}",String.valueOf(t.getPeriods()));
 		sql  = sql.replace("#{periods_unit}",String.valueOf(t.getPeriodsUnit()));
 		sql  = sql.replace("#{interest_rate}",String.valueOf(t.getInterestRate()));
 		sql  = sql.replace("#{penalty_rate}",String.valueOf(t.getPenaltyRate()));
 		sql  = sql.replace("#{min_amount}",String.valueOf(t.getMinAmount()));
 		sql  = sql.replace("#{max_amount}",String.valueOf(t.getMaxAmount()));
 		sql  = sql.replace("#{wf_id}",String.valueOf(t.getWfId()));
 		sql  = sql.replace("#{details}",String.valueOf(t.getDetails()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
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
	public List<Map<String, Object>> findProduct(Map<String, String> param) {
		String sqlWhere = " ";
		if(param != null){
			if(param.get("id") != null){
				sqlWhere += " t.id = '"+ param.get("id") +"'";
			}
			if(param.get("status") != null){
				sqlWhere += " t.status = '"+ param.get("status") +"'";
			}
		}
		String sql = "select t.id,t.pro_name proName,periods,periods_unit periodsUnit,interest_rate interestRate,"+
		"penalty_rate penaltyRate,min_amount minAmount,max_amount maxAmount,repayment  "
		            + "from zml_loan_product t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY t.id desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		return mapList;
	}
}