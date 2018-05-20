package com.zml.loan_risk;

import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.loan.entity.ZmlLoanRiskCreditEntity;

public interface RiskServerService {
	//必须筛选项
	public ZmlLoanRiskCreditEntity mustFilterItem(String applyId, String userId);
	//基本信息筛选
	public int basicFilterItem(String applyId, String userId, ZmlUserEntity user);
	//联系人信息筛选
	public int contactsFilterItem(String applyId, String userId);
	//土地信息筛选
	public int lanFilterItem(String applyId, String userId);
	//房产信息筛选
	public int estateFilterItem(String applyId, String userId);
	
}
