package com.zml.enums.loan;

//审批标识
public enum LoanTaskSts {
	UPCOMING("0", "待办"),
	PROCESSING("1", "处理中"),
	DONE("2", "已办"),
	NOT_ASSIGNED("3", "未分配");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanTaskSts(String statusValue, String statusName) {
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
	public static LoanTaskSts getLoanTaskSts(String val) {
		LoanTaskSts enums = null;
		for (LoanTaskSts tempEnums: LoanTaskSts.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enums = tempEnums;
    	break;
    }
}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanTaskStsDesc(String val) {
		String enumsStr = null;
		for (LoanTaskSts tempEnums: LoanTaskSts.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enumsStr = tempEnums.getStatusName();
    	break;
    }
}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanTaskSts {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}