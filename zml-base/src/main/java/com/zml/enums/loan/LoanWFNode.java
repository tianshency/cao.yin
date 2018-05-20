package com.zml.enums.loan;

//贷款申请流程状态
public enum LoanWFNode {
	BEGIN_APPROVE("begin", "初审"),
	AUTO_CREDIT("autocredit", "自动授信"),
	CHECK_APPROVE("check", "抽查"),
	END_APPROVE("end", "终审"),
	PAY_APPROVE("pay", "放款"),
	REPAY_APPROVE("repay", "还款");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanWFNode(String statusValue, String statusName) {
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
	public static LoanWFNode getLoanWFNode(String val) {
		LoanWFNode enums = null;
		for (LoanWFNode tempEnums: LoanWFNode.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enums = tempEnums;
    	break;
    }
}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanWFNodeDesc(String val) {
		String enumsStr = null;
		for (LoanWFNode tempEnums: LoanWFNode.values()) {
		      if(val.equals(tempEnums.getStatusValue())){
		      	enumsStr = tempEnums.getStatusName();
		      	break;
		      }
		  }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanWFNode {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
