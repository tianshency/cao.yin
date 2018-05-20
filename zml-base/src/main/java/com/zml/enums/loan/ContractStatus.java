package com.zml.enums.loan;
//合同状态
public enum ContractStatus {
	NO_PAY("0", "未放款"),
	NORMAL("1", "正常"),
	OVERDUE("2", "逾期"),
	WRITE_OFF("3", "核销"),
	REPAY_APPROVE("4", "还款审批中"),
	CLOSED("5", "已结清");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	ContractStatus(String statusValue, String statusName) {
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
	public static ContractStatus getContractStatus(String val) {
		ContractStatus enums = null;
		for (ContractStatus tempEnums: ContractStatus.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enums = tempEnums;
      	break;
      }
  }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getContractStatusDesc(String val) {
		String enumsStr = null;
		for (ContractStatus tempEnums: ContractStatus.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enumsStr = tempEnums.getStatusName();
      	break;
      }
  }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ContractStatus {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
