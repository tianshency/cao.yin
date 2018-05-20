package com.zml.loan_service.impl;
import java.io.Serializable;
import java.math.BigDecimal;
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
import com.zml.base.loan.entity.ZmlLoanRepayPlanDetailEntity;
import com.zml.loan_service.ZmlLoanRepayPlanDetailServiceI;
import com.zml.util.DateUtil;

@Service("zmlLoanRepayPlanDetailService")
@Transactional
public class ZmlLoanRepayPlanDetailServiceImpl extends CommonServiceImpl implements ZmlLoanRepayPlanDetailServiceI {

	
 	public void delete(ZmlLoanRepayPlanDetailEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlLoanRepayPlanDetailEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlLoanRepayPlanDetailEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlLoanRepayPlanDetailEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlLoanRepayPlanDetailEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlLoanRepayPlanDetailEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlLoanRepayPlanDetailEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_date", t.getCreateDate());
		map.put("update_date", t.getUpdateDate());
		map.put("version", t.getVersion());
		map.put("contract_id", t.getContractId());
		map.put("user_id", t.getUserId());
		map.put("profit_period", t.getProfitPeriod());
		map.put("profit_start_date", t.getProfitStartDate());
		map.put("profit_end_date", t.getProfitEndDate());
		map.put("profit_principal", t.getProfitPrincipal());
		map.put("profit_interest", t.getProfitInterest());
		map.put("profit_penalty", t.getProfitPenalty());
		map.put("profit_nonpayment", t.getProfitNonpayment());
		map.put("profit_other_fee", t.getProfitOtherFee());
		map.put("profit_repay_sum", t.getProfitRepaySum());
		map.put("repay_principal", t.getRepayPrincipal());
		map.put("repay_interest", t.getRepayInterest());
		map.put("repay_penalty", t.getRepayPenalty());
		map.put("repay_nonpayment", t.getRepayNonpayment());
		map.put("repay_other_fee", t.getRepayOtherFee());
		map.put("repay_sum", t.getRepaySum());
		map.put("last_repay_date", t.getLastRepayDate());
		map.put("end_current_principalbalance", t.getEndCurrentPrincipalbalance());
		map.put("rate", t.getRate());
		map.put("overdue_rate", t.getOverdueRate());
		map.put("overdue_days", t.getOverdueDays());
		map.put("status", t.getStatus());
		map.put("adjustdate", t.getAdjustdate());
		map.put("recently_repay_time", t.getRecentlyRepayTime());
		map.put("remarks", t.getRemarks());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlLoanRepayPlanDetailEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{contract_id}",String.valueOf(t.getContractId()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{profit_period}",String.valueOf(t.getProfitPeriod()));
 		sql  = sql.replace("#{profit_start_date}",String.valueOf(t.getProfitStartDate()));
 		sql  = sql.replace("#{profit_end_date}",String.valueOf(t.getProfitEndDate()));
 		sql  = sql.replace("#{profit_principal}",String.valueOf(t.getProfitPrincipal()));
 		sql  = sql.replace("#{profit_interest}",String.valueOf(t.getProfitInterest()));
 		sql  = sql.replace("#{profit_penalty}",String.valueOf(t.getProfitPenalty()));
 		sql  = sql.replace("#{profit_nonpayment}",String.valueOf(t.getProfitNonpayment()));
 		sql  = sql.replace("#{profit_other_fee}",String.valueOf(t.getProfitOtherFee()));
 		sql  = sql.replace("#{profit_repay_sum}",String.valueOf(t.getProfitRepaySum()));
 		sql  = sql.replace("#{repay_principal}",String.valueOf(t.getRepayPrincipal()));
 		sql  = sql.replace("#{repay_interest}",String.valueOf(t.getRepayInterest()));
 		sql  = sql.replace("#{repay_penalty}",String.valueOf(t.getRepayPenalty()));
 		sql  = sql.replace("#{repay_nonpayment}",String.valueOf(t.getRepayNonpayment()));
 		sql  = sql.replace("#{repay_other_fee}",String.valueOf(t.getRepayOtherFee()));
 		sql  = sql.replace("#{repay_sum}",String.valueOf(t.getRepaySum()));
 		sql  = sql.replace("#{last_repay_date}",String.valueOf(t.getLastRepayDate()));
 		sql  = sql.replace("#{end_current_principalbalance}",String.valueOf(t.getEndCurrentPrincipalbalance()));
 		sql  = sql.replace("#{rate}",String.valueOf(t.getRate()));
 		sql  = sql.replace("#{overdue_rate}",String.valueOf(t.getOverdueRate()));
 		sql  = sql.replace("#{overdue_days}",String.valueOf(t.getOverdueDays()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{adjustdate}",String.valueOf(t.getAdjustdate()));
 		sql  = sql.replace("#{recently_repay_time}",String.valueOf(t.getRecentlyRepayTime()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
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
 	//查询合同最近一笔未还款 的还款计划 num=1 查最近一笔未还， num=-1 查询全部, num=0 查询所有未还
	@Override
	public Map<String, Object> findContractNoRepayPlan(String contractId, String num) {
		String sqlWhere = " contract_id = '"+ contractId +"'";
		String sql = "select id,create_date createDate,update_date updateDate,version version,user_id userId,contract_id contractId,profit_period profitPeriod,profit_start_date profitStartDate,profit_end_date profitEndDate,profit_principal profitPrincipal,profit_interest profitInterest,profit_penalty profitPenalty,profit_nonpayment profitNonpayment,profit_other_fee profitOtherFee,profit_repay_sum profitRepaySum,repay_principal repayPrincipal,repay_interest repayInterest,repay_penalty repayPenalty "
                    + " ,repay_nonpayment repayNonpayment,repay_other_fee repayOtherFee,repay_sum repaySum,last_repay_date lastRepayDate,end_current_principalbalance endCurrentPrincipalbalance,rate rate,overdue_rate overdueRate,overdue_days overdueDays,status,adjustdate adjustdate,recently_repay_time recentlyRepayTime,remarks "
		            + " from zml_loan_repay_plan_detail t ";
		
		//查询未还 状态
		if("0".equals(num))
			sqlWhere += " and status !='1' ";
		
		if("1".equals(num))
			sqlWhere += " and status !='1' and profit_end_date <= '"+DateUtil.getSysCurFmtDate()+"' ";
		
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY profit_period asc";
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		List<ZmlLoanRepayPlanDetailEntity> rsList = new ArrayList();
		if(mapList != null && mapList.size() > 0 ){
			for(Map<String, Object> map : mapList){
				ZmlLoanRepayPlanDetailEntity entity = new ZmlLoanRepayPlanDetailEntity();
				entity.setId((String)map.get("id"));
				entity.setCreateDate((Date)map.get("createDate"));
				entity.setUpdateDate((Date)map.get("updateDate"));
				entity.setVersion((Integer)map.get("version"));
				entity.setUserId((String)map.get("userId"));
				entity.setContractId((String)map.get("contractId"));
				entity.setProfitPeriod((Integer)map.get("profitPeriod"));
				entity.setProfitStartDate((Date)map.get("profitStartDate"));
				entity.setProfitEndDate((Date)map.get("profitEndDate"));
				entity.setProfitPrincipal((BigDecimal)(map.get("profitPrincipal")));
				entity.setProfitInterest((BigDecimal)map.get("profitInterest"));
				entity.setProfitPenalty((BigDecimal)map.get("profitPenalty"));
				entity.setProfitNonpayment((BigDecimal)map.get("profitNonpayment"));
				entity.setProfitOtherFee((BigDecimal)map.get("profitOtherFee"));
				entity.setProfitRepaySum((BigDecimal)map.get("profitRepaySum"));
				entity.setRepayPrincipal((BigDecimal)map.get("repayPrincipal"));
				entity.setRepayInterest((BigDecimal)map.get("repayInterest"));
				entity.setRepayPenalty((BigDecimal)map.get("repayPenalty"));
				entity.setRepayNonpayment((BigDecimal)map.get("repayNonpayment"));
				entity.setRepayOtherFee((BigDecimal)map.get("repayOtherFee"));
				entity.setRepaySum((BigDecimal)map.get("repaySum"));
				entity.setLastRepayDate((Date)map.get("lastRepayDate"));
				entity.setEndCurrentPrincipalbalance((BigDecimal)map.get("endCurrentPrincipalbalance"));
				entity.setRate((BigDecimal)map.get("rate"));
				entity.setOverdueRate((BigDecimal)map.get("overdueRate"));
				entity.setOverdueDays((Integer)map.get("overdueDays"));
				entity.setStatus((String)map.get("status"));
				entity.setAdjustdate((Date)map.get("adjustdate"));
				entity.setRecentlyRepayTime((Date)map.get("recentlyRepayTime"));
				entity.setRemarks((String)map.get("remarks"));
				rsList.add(entity);
			}
		}
		//查询汇总 金额 
		String sqlGroup = "select sum(profit_principal) profitPrincipal,sum(profit_interest) profitInterest,sum(profit_penalty) profitPenalty,"
				+ " sum(profit_nonpayment) profitNonpayment,sum(profit_other_fee) profitOtherFee,sum(profit_repay_sum) profitRepaySum,"
				+ "sum(repay_principal) repayPrincipal,sum(repay_interest) repayInterest,sum(repay_penalty) repayPenalty, " 
				+ "sum(repay_nonpayment) repayNonpayment,sum(repay_other_fee) repayOtherFee,sum(repay_sum) repaySum from zml_loan_repay_plan_detail ";
		sqlGroup  += " where" + sqlWhere;
		Map<String, Object> rsMap = findOneForJdbc(sqlGroup, null);
		Map<String, Object>  reMap = new HashMap();
		reMap.put("rsList", rsList);
		reMap.put("rsMap", rsMap);
		return reMap;
	}

	@Override
	public Map<String, Object> findContractNoRepayPlan(String contractId) {
		String sqlWhere = " contract_id = '"+ contractId +"'";
		String sql = "select id,create_date createDate,update_date updateDate,version version,user_id userId,contract_id contractId,profit_period profitPeriod,profit_start_date profitStartDate,profit_end_date profitEndDate,profit_principal profitPrincipal,profit_interest profitInterest,profit_penalty profitPenalty,profit_nonpayment profitNonpayment,profit_other_fee profitOtherFee,profit_repay_sum profitRepaySum,repay_principal repayPrincipal,repay_interest repayInterest,repay_penalty repayPenalty "
                    + " ,repay_nonpayment repayNonpayment,repay_other_fee repayOtherFee,repay_sum repaySum,last_repay_date lastRepayDate,end_current_principalbalance endCurrentPrincipalbalance,rate rate,overdue_rate overdueRate,overdue_days overdueDays,status,adjustdate adjustdate,recently_repay_time recentlyRepayTime,remarks "
		            + " from zml_loan_repay_plan_detail t ";
		
		//查询未还 状态
		sqlWhere += " and status !='1' ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		sql += " ORDER BY profit_period asc limit 1";
		Map<String, Object> rsMap = findOneForJdbc(sql, null);
		return rsMap;
	}
}