package com.zml.enums.loan;

//审批标识
public enum LoanTaskType {
	LOAN_APPLY("1", "业务申请"),
	PAY("2", "放款"),
	REPAY("3", "还款"),
	COLLECTION_ONE("10", "催收"),
	COLLECTION_TWO("10", "催收");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanTaskType(String statusValue, String statusName) {
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
	public static LoanTaskType getLoanTaskType(String val) {
		LoanTaskType enums = null;
		for (LoanTaskType tempEnums: LoanTaskType.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enums = tempEnums;
    	break;
    }
}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanTaskTypeDesc(String val) {
		String enumsStr = null;
		for (LoanTaskType tempEnums: LoanTaskType.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enumsStr = tempEnums.getStatusName();
    	break;
    }
}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanTaskType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}