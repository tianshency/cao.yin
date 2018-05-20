package com.zml.enums.loan;

//贷款申请文档类型
public enum LoanApplyDocType {
	ID_CARD_POSITIVE("1", "身份证"),
	/*ID_CARD_NEGATIVE("2", "身份证反面"),
	ID_CARD_SELF("3", "身份证与本人合影"),*/
	LAND_SOURCE("2", "土地本"),
	DEED("3", "房产证"),
	HOUSE_IMG("4", "房子实体照片"),
	DRIVER_LICENSE("5", "驾驶证"),
	HUKOU_SOURCE("6", "行驶证"),
	REALNAME_CHECK("7", "实名认证"),
	OTHER_DOC("999", "其他");
	;
	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	LoanApplyDocType(String statusValue, String statusName) {
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
	public static LoanApplyDocType getLoanApplyDocType(String val) {
		LoanApplyDocType enums = null;
		for (LoanApplyDocType tempEnums: LoanApplyDocType.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enums = tempEnums;
      	break;
      }
  }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getLoanApplyDocTypeDesc(String val) {
		String enumsStr = null;
		for (LoanApplyDocType tempEnums: LoanApplyDocType.values()) {
      if(val.equals(tempEnums.getStatusValue())){
      	enumsStr = tempEnums.getStatusName();
      	break;
      }
  }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "LoanApplyDocType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}