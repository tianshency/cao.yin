package com.zml.enums.loan;

//贷款申请状态
public enum LoanDocumentDirName {
	APPLY_DIR("apply_dir", "业务申请"),
	CONTRACT_DIR("contract_dir", "合同"),
	PAY_DIR("pay_dir", "放款"),
	RE_PAY_DIR("repay_dir", "还款"),
	OTHER_DIR("other_doc", "其他");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanDocumentDirName(String statusValue, String statusName) {
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
	public static LoanDocumentDirName getLoanDocumentDirName(String val) {
		LoanDocumentDirName enums = null;
		for (LoanDocumentDirName tempEnums: LoanDocumentDirName.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enums = tempEnums;
      	break;
      }
  }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanDocumentDirNameDesc(String val) {
		String enumsStr = null;
		for (LoanDocumentDirName tempEnums: LoanDocumentDirName.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enumsStr = tempEnums.getStatusName();
      	break;
      }
  }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanDocumentDirName {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}