package com.zml.enums.loan;
//贷款相关 号码 前缀
public enum LoanNoPrefix {
	APPLY_NO_PREF("A-", "申请编号"),
	MORTGAGE_NO_PREF("M-", "抵押编号"),
	Pledge_NO_PREF("P-", "质押编号"),
	GUARANTEE_NO_PREF("G-", "担保编号"),
	CONTRACT_NO_PREF("C-", "合同编号"),
	GC_NO_PREF("GC-", "担保合同编号"),
	PAY_NO_PREF("J-", "放款\\借据编号"),
	EXTENSION_NO_PREF("E-", "展期编号");
	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanNoPrefix(String statusValue, String statusName) {
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
	public static LoanNoPrefix getLoanNoPrefix(String val) {
		LoanNoPrefix enums = null;
		for (LoanNoPrefix tempEnums: LoanNoPrefix.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enums = tempEnums;
          	break;
          }
      }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanNoPrefixDesc(String val) {
		String enumsStr = null;
		for (LoanNoPrefix tempEnums: LoanNoPrefix.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enumsStr = tempEnums.getStatusName();
          	break;
          }
      }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanNoPrefix {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}