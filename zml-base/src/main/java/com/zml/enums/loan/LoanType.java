package com.zml.enums.loan;

//贷款类型
public enum LoanType {
	AGRICULTURE_LOAN("1", "农贷"),
	CONSUMPTION_LOAN("1", "消费贷"),
	SMALL_BUSINESS_LOAN("2", "小企业贷");
	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanType(String statusValue, String statusName) {
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
	public static LoanType getLoanType(String val) {
		LoanType enums = null;
		for (LoanType tempEnums: LoanType.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanTypeDesc(String val) {
		String enumsStr = null;
		for (LoanType tempEnums: LoanType.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
