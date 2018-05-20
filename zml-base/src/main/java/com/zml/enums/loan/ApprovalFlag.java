package com.zml.enums.loan;

//审批标识
public enum ApprovalFlag {
	NO_APPROVE("0", "未审批"),
	APPROVE_PAST("1", "审批通过"),
	APPROVE_REJECT("2", "审批拒绝");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	ApprovalFlag(String statusValue, String statusName) {
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
	public static ApprovalFlag getApprovalFlag(String val) {
		ApprovalFlag enums = null;
		for (ApprovalFlag tempEnums: ApprovalFlag.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enums = tempEnums;
      	break;
      }
  }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getApprovalFlagDesc(String val) {
		String enumsStr = null;
		for (ApprovalFlag tempEnums: ApprovalFlag.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enumsStr = tempEnums.getStatusName();
      	break;
      }
  }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ApprovalFlag {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}