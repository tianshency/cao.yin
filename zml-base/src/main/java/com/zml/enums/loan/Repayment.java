package com.zml.enums.loan;

//还款方式
public enum Repayment {
	NORMAL("1", "按固定周期付息,到期还款"),
	ONE_TIME_REPAYMENT("2", "一次性付息还款"),
	//WRITE_OFF("3", "按固定周期付息,按还款计划还本"),
	EQUAL_PRIN_INTEREST("4", "等额本息"),
	EQUAL_PRINCIPAL("5", "等额本金");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	Repayment(String statusValue, String statusName) {
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
	public static Repayment getRepayment(String val) {
		Repayment enums = null;
		for (Repayment tempEnums: Repayment.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enums = tempEnums;
    	break;
    }
}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getRepaymentDesc(String val) {
		String enumsStr = null;
		for (Repayment tempEnums: Repayment.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enumsStr = tempEnums.getStatusName();
    	break;
    }
}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "Repayment {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
