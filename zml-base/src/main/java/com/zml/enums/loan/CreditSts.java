package com.zml.enums.loan;

//授信状态
public enum CreditSts {
	APPROVE_ING("1", "审批中"),
	APPROVE_SUCC("2", "审批成功"),
	APPROVE_REJECT("3", "审批拒绝"),
	//COMMIT("4", "未提交"),
	APPLY_OVER("5", "申请结束");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	CreditSts(String statusValue, String statusName) {
	    this.statusName = statusName;
	    this.statusValue = statusValue;
	}

	public String getStatusName() {
	    return statusName;
	}

	public String getStatusValue() {
	    return statusValue;
	}
	//根据 值获取对应枚举类型
	public static CreditSts getCreditSts(String val) {
		CreditSts enums = null;
		for (CreditSts tempEnums: CreditSts.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enums = tempEnums;
      	break;
      }
  }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getCreditStsDesc(String val) {
		String enumsStr = null;
		for (CreditSts tempEnums: CreditSts.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enumsStr = tempEnums.getStatusName();
      	break;
      }
  }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "CreditSts {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}