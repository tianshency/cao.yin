package com.zml.enums.loan;

//还款计划状态
public enum RepayPlanDetailStatus {
	NO_REPAY("0", "未还款"),
	REPAY("1", "已还款"),
	OVERDUE("2", "逾期"),
	OVERDUE_REPAY("3", "逾期已还款"),
	PART_REPAY("4", "部分还款");
	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	RepayPlanDetailStatus(String statusValue, String statusName) {
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
	public static RepayPlanDetailStatus getRepayPlanDetailStatus(String val) {
		RepayPlanDetailStatus enums = null;
		for (RepayPlanDetailStatus tempEnums: RepayPlanDetailStatus.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enums = tempEnums;
    	break;
    }
}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getRepayPlanDetailStatusDesc(String val) {
		String enumsStr = null;
		for (RepayPlanDetailStatus tempEnums: RepayPlanDetailStatus.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enumsStr = tempEnums.getStatusName();
    	break;
    }
}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "RepayPlanDetailStatus {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}