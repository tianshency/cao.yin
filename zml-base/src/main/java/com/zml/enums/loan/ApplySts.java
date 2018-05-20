package com.zml.enums.loan;
//贷款申请状态
public enum ApplySts {
	NO_COMMIT("0", "未提交"),
	APPROVE_ING("1", "审批中"),
	APPROVE_PAST("2", "审批通过"),
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

	ApplySts(String statusValue, String statusName) {
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
	public static ApplySts getApplySts(String val) {
		ApplySts enums = null;
		for (ApplySts tempEnums: ApplySts.values()) {
        if(val.equals(tempEnums.getStatusValue())){
        	enums = tempEnums;
        	break;
        }
    }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getApplyStsDesc(String val) {
		String enumsStr = null;
		for (ApplySts tempEnums: ApplySts.values()) {
        if(val.equals(tempEnums.getStatusValue())){
        	enumsStr = tempEnums.getStatusName();
        	break;
        }
    }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ApplySts {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}