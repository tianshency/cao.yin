package com.zml.enums.loan;
//五级分类
public enum FiveClassificationSts {
	NORMAL("1", "正常"),
	CONCERN("2", "关注"),
	SECONDARY("3", "次级"),
	SUSPICIOUS("3", "可疑"),
	LOSS("5", "损失");
	
	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	FiveClassificationSts(String statusValue, String statusName) {
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
	public static FiveClassificationSts getFiveClassification(String val) {
		FiveClassificationSts enums = null;
		for (FiveClassificationSts tempEnums: FiveClassificationSts.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enums = tempEnums;
    	break;
    }
}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getFiveClassificationDesc(String val) {
		String enumsStr = null;
		for (FiveClassificationSts tempEnums: FiveClassificationSts.values()) {
    if(val.equals(tempEnums.getStatusValue())){
    	enumsStr = tempEnums.getStatusName();
    	break;
    }
}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "FiveClassification {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
