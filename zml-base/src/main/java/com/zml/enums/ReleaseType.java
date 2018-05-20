package com.zml.enums;
//发布类型
public enum ReleaseType {
	FOOD("2", "粮食"),
	TERRITORY("1", "土地"),
	MACHINE("3", "机车"),
	WORK("5", "工作"),
	OTHER("99", "其他");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	ReleaseType(String statusValue, String statusName) {
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
	public ReleaseType getReleaseType(String val) {
		ReleaseType enums = null;
		for (ReleaseType tempEnums: ReleaseType.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getReleaseTypeDesc(String val) {
		String enumsStr = null;
		for (ReleaseType tempEnums: ReleaseType.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ReleaseType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}