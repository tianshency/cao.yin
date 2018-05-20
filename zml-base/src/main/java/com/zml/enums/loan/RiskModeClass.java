package com.zml.enums.loan;

//风控模型分类
public enum RiskModeClass {
	BASIC("1", "基本信息"),
	LAN("2", "土地信息"),
	DEED("3", "房产信息"),
	HUKOU_SOURCE("4", "行驶证"),
	CONTACT_PERSON("5", "联系人"),
	RECOMMEND_PERSON("6", "推荐人");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	RiskModeClass(String statusValue, String statusName) {
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
	public static RiskModeClass getRiskModeClass(String val) {
		RiskModeClass enums = null;
		for (RiskModeClass tempEnums: RiskModeClass.values()) {
		    if(val.equals(tempEnums.getStatusValue())){
		    	enums = tempEnums;
		    	break;
		    }
		}
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public static String getRiskModeClassDesc(String val) {
		String enumsStr = null;
		for (RiskModeClass tempEnums: RiskModeClass.values()) {
		    if(val.equals(tempEnums.getStatusValue())){
		    	enumsStr = tempEnums.getStatusName();
		    	break;
		    }
		}
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "RiskModeClass {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}