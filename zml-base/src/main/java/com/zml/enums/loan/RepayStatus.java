package com.zml.enums.loan;

//还款计划状态
public enum RepayStatus {
	SUCC("1", "成功"),
	FAIL("2", "失败"),
	APPROVE_ING("3", "审批中"),
	APPROVE_REJECT("4", "审批拒绝");
	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	RepayStatus(String statusValue, String statusName) {
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
	public static RepayStatus getRepayStatus(String val) {
		RepayStatus enums = null;
		for (RepayStatus tempEnums: RepayStatus.values()) {
		  if(val.equals(tempEnums.getStatusValue())){
		  	enums = tempEnums;
		  	break;
		  }
		}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getRepayStatusDesc(String val) {
		String enumsStr = null;
		for (RepayStatus tempEnums: RepayStatus.values()) {
		  if(val.equals(tempEnums.getStatusValue())){
		  	enumsStr = tempEnums.getStatusName();
		  	break;
		  }
		}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "RepayStatus {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}