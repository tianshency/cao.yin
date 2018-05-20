package com.zml.loan_risk.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.type.YesNoType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.loan.entity.ZmlLoanRiskCreditEntity;
import com.zml.common.LoanConstant;
import com.zml.enums.YesOrNo;
import com.zml.enums.loan.ApprovalFlag;
import com.zml.enums.loan.CreditSts;
import com.zml.loan_risk.RiskServerService;
import com.zml.util.DateUtil;
import com.zml.util.IdCardUtils;
@Service("riskServerService")
@Transactional
public class RiskServerServiceImpl extends CommonServiceImpl implements RiskServerService {

	@Override
	public ZmlLoanRiskCreditEntity mustFilterItem(String applyId, String userId) {
		String sqlWhere = "";
		if(userId != null && !"".equals(userId)){
			sqlWhere += " t.userId = '"+ userId +"'";
		}
		String sql = null;
		//查询 用户信息
		/*String sql = "select t.id,t.real_name realName,t.identification_number identificationNumber,t.phone,t.age, t.sex ,t.is_verified isVerified,t.issuing_authority issuingAuthority "
                    + "t.registration_agreement registrationAgreement, t.accountLocation , t.valid_start validStart, t.valid_end validEnd  "
		            + "from zml_user t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		Map<String, Object> map = findOneForJdbc(sql, null);
		*/
		ZmlUserEntity user = getEntity(ZmlUserEntity.class, userId);
		//MyBeanUtils.copyMap2Bean(bean, map);
		ZmlLoanRiskCreditEntity riskCredit = new ZmlLoanRiskCreditEntity ();
		riskCredit.setStatus(CreditSts.APPROVE_ING.getStatusValue());
		riskCredit.setCreateDate(new Date());
		riskCredit.setUserId(userId);
		riskCredit.setApplId(applyId);
		if(IdCardUtils.validateCard(user.getIdentificationNumber())){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("身份证不合法:" + user.getIsVerified());
			return riskCredit;
		}else if(YesOrNo.NO.getStatusValue().equals(user.getIsVerified())){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("用户没有实名认证");
			return riskCredit;
		}else if(user.getAge()> 60 || user.getAge() <18){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("年龄不符合:" + user.getAge());
			return riskCredit;
		}else if(user.getAge()> 60 || user.getAge() <18){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("年龄不符合:" + user.getAge());
			return riskCredit;
		}
		//根据用户查询 申请信息
		sql = "select apply_sts applySts,update_date updateDate,phone from zml_loan_application";
		sql += "where user_id = '"+userId+"'";
		sql += "and id != '"+applyId+"'";
		sql += "and status between 0 and 3 ";
		List<Map<String, Object>> list1 = findForJdbc(sql, null);
		String phone = null;
		if(list1 != null && list1.size() > 0){
			boolean b1 = false;
			for (int i = 0; i < list1.size(); i++) {
				Map<String, Object> tempMap = list1.get(i);
				phone = (String)tempMap.get("phone");
				if("0".equals(tempMap.get("applySts"))
					||"1".equals(tempMap.get("applySts"))
					||"2".equals(tempMap.get("applySts"))){
					b1 = true;
					riskCredit.setApprovalOpinion("用户有申请中贷款！");
					break;
				}else if("4".equals(tempMap.get("applySts"))
						&& DateUtil.daysBetween((Date)tempMap.get("applySts"), new Date()) < LoanConstant.LOAN_APPLY_REREJECT_DAYS){
					riskCredit.setApprovalOpinion("用户上次申请贷款被拒绝小于"+LoanConstant.LOAN_APPLY_REREJECT_DAYS+"天！");
					break;
				}
			}
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			list1.clear();
			return riskCredit;
		}
		//根据用户查询 申请信息
		sql = "select count(1) countNum from zml_loan_application";
		sql += "where user_id != '"+userId+"'";
		sql += "and phone = '"+phone+"'";
	    Map<String, Object> rsMap = findOneForJdbc(sql, null);
	    if(rsMap != null && ((Long)rsMap.get("countNum")).intValue() >0){
	    	riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("手机号:"+phone+"其他用户贷过款！");
			return riskCredit;
	    }
		//根据用户ID查询 未结清 贷款
		sql = "select count(1) from zml_loan_contract";
		sql += "where user_id = '"+userId+"'";
		sql += "and status between 0 and 4 ";
	    list1 = findForJdbc(sql, null);
		if(list1 != null && list1.size() > 0){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("用户有未结清贷款！");
			list1.clear();
			return riskCredit;
		}
		//查询黑名单
		sql = "select count(1) from zml_loan_black_list";
		sql += "where user_id = '"+userId+"'";
		sql += "and approval_flag='1' ";
	    list1 = findForJdbc(sql, null);
		if(list1 != null && list1.size() > 0){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("用户是黑名单用户！");
			list1.clear();
			return riskCredit;
		}
		//查询联系人审批情况
		sql = "select count(1) from zml_loan_user_contacts";
		sql += "where user_id = '"+userId+"'";
		sql += "and appl_id = '"+userId+"'";
		sql += "and approval_flag !='1' ";
	    list1 = findForJdbc(sql, null);
		if(list1 != null && list1.size() > 0){
			riskCredit.setStatus(CreditSts.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalFlag(ApprovalFlag.APPROVE_REJECT.getStatusValue());
			riskCredit.setApprovalOpinion("用户联系人存在审批拒绝情况！");
			list1.clear();
			return riskCredit;
		}
		
		return riskCredit;
	}

	@Override
	public int basicFilterItem(String applyId, String userId,ZmlUserEntity user) {
		int rs = 0;
		//实名认证
		if(YesOrNo.YES.getStatusValue().equals(user.getIsVerified())){
			rs = 5;
		}
		//a1 手机归属地
		

		
		//a2 年龄
		if(user.getAge().intValue() < 18 || user.getAge().intValue() > 60){
			
		}
		//a3 性别

		
		
		return rs;
	}

	@Override
	public int contactsFilterItem(String applyId, String userId) {
		// TODO Auto-generated method stub
		int rs = 0;
		return rs;
	}

	@Override
	public int lanFilterItem(String applyId, String userId) {
		// TODO Auto-generated method stub
		int rs = 0;
		return rs;
	}

	@Override
	public int estateFilterItem(String applyId, String userId) {
		// TODO Auto-generated method stub
		int rs = 0;
		return rs;
	}

}
