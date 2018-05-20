package com.zml.loan_service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
import com.zml.base.loan.entity.ZmlLoanApplicationEntity;
import com.zml.base.loan.entity.ZmlLoanWfTaskEntity;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.LoanTaskSts;
import com.zml.enums.loan.LoanTaskType;
import com.zml.enums.loan.LoanWFNode;
import com.zml.loan_service.ZmlLoanApplicationServiceI;
import com.zml.util.SqStrParamlUtil;

@Service("zmlLoanApplicationService")
@Transactional
public class ZmlLoanApplicationServiceImpl extends CommonServiceImpl implements ZmlLoanApplicationServiceI {

	
 	public void delete(ZmlLoanApplicationEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanApplicationEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanApplicationEntity t) throws Exception{
 		super.saveOrUpdate(t);
 		
 		//执行更新操作增强业务
 		this.doUpdateBus(t);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanApplicationEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanApplicationEntity t) throws Exception{
		/*String approvalUserId = "";
		ZmlLoanWfTaskEntity task = new ZmlLoanWfTaskEntity();
		task.setUserId(t.getUserId());
		task.setCreateDate(new Date());
		task.setBizId(t.getId());
		task.setTaskType(LoanTaskType.LOAN_APPLY.getStatusValue());
		task.setStatus(LoanTaskSts.UPCOMING.getStatusValue());
		task.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
		task.setTaskSubject(t.getSubject());
		task.setApprovalUserId(approvalUserId);
		task.setIsNotic(YesOrNo.NO.getStatusValue());
		super.save(task);*/
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanApplicationEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanApplicationEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("bpm_status", t.getBpmStatus());
		map.put("user_id", t.getUserId());
		map.put("apply_no", t.getApplyNo());
		map.put("loan_type", t.getLoanType());
		map.put("name", t.getName());
		map.put("profession", t.getProfession());
		map.put("phone", t.getPhone());
		map.put("loan_reason", t.getLoanReason());
		map.put("amount", t.getAmount());
		map.put("periods", t.getPeriods());
		map.put("periods_unit", t.getPeriodsUnit());
		map.put("interest_rate", t.getInterestRate());
		map.put("penalty_rate", t.getPenaltyRate());
		map.put("apply_sts", t.getApplySts());
		map.put("subject", t.getSubject());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanApplicationEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{bpm_status}",String.valueOf(t.getBpmStatus()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{apply_no}",String.valueOf(t.getApplyNo()));
 		sql  = sql.replace("#{loan_type}",String.valueOf(t.getLoanType()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{profession}",String.valueOf(t.getProfession()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{loan_reason}",String.valueOf(t.getLoanReason()));
 		sql  = sql.replace("#{amount}",String.valueOf(t.getAmount()));
 		sql  = sql.replace("#{periods}",String.valueOf(t.getPeriods()));
 		sql  = sql.replace("#{periods_unit}",String.valueOf(t.getPeriodsUnit()));
 		sql  = sql.replace("#{interest_rate}",String.valueOf(t.getInterestRate()));
 		sql  = sql.replace("#{penalty_rate}",String.valueOf(t.getPenaltyRate()));
 		sql  = sql.replace("#{apply_sts}",String.valueOf(t.getApplySts()));
 		sql  = sql.replace("#{subject}",String.valueOf(t.getSubject()));
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
	public List<Map<String, Object>> findLoanApplicationByUserIdAndSts(String userId, String status) {
		//userId = SqStrParamlUtil.transactSQLInjection(userId);
		//status = SqStrParamlUtil.transactSQLInjection(status);
		List<Map<String, Object>> rsList = new ArrayList();
		Map rsMap = new HashMap();
		String sql = "select id, apply_no applyNo, apply_sts applySts from zml_loan_application where user_id ='"+ userId +"'";
		
		if(status != null && !"".equals(status)){
			sql += " and status in ("+ status +")' order by create_date desc ";
		}
		
		List<Map<String, Object>> list = findForJdbc(sql, null);
		if( list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> temp = list.get(i);
				rsMap.put(temp.get("id"), temp.get("id"));
				rsMap.put(temp.get("applyNo"), temp.get("applyNo"));
				rsMap.put(temp.get("applySts"), temp.get("applySts"));
				rsList.add(temp);
			}		
		}
		return rsList;
	}

	@Override
	public void commitApply(ZmlLoanApplicationEntity t, String approvalUserId) throws Exception {
		super.saveOrUpdate(t);
		ZmlLoanWfTaskEntity task = new ZmlLoanWfTaskEntity();
		task.setUserId(t.getUserId());
		task.setCreateDate(new Date());
		task.setBizId(t.getId());
		task.setTaskType(LoanTaskType.LOAN_APPLY.getStatusValue());
		task.setStatus(LoanTaskSts.PROCESSING.getStatusValue());
		task.setApprovalFlag(ApprovalFlag.NO_APPROVE.getStatusValue());
		task.setTaskSubject(t.getSubject());
		task.setApprovalUserId(approvalUserId);
		task.setIsNotic(YesOrNo.NO.getStatusValue() + "");
		task.setBpmStatus(LoanWFNode.BEGIN_APPROVE.getStatusValue());
		task.setProductId(t.getProductId());
		//task.setStartTime(new Date());
		this.save(task);
	}
}