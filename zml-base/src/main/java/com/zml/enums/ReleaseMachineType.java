package com.zml.enums;

//发布机车类型
public enum ReleaseMachineType {
	RENT("1", "出租"),
	FOR_RENT("2", "求租"),
	SELL("3", "出售"),
	BUYING("4", "求购"),
	WORK_MACHINE("5", "出车干活"),
	EMPLOY_MACHINE("6", "雇车"),
	OTHER("99", "雇车");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	ReleaseMachineType(String statusValue, String statusName) {
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
	public ReleaseMachineType getReleaseBaseType(String val) {
		ReleaseMachineType enums = null;
		for (ReleaseMachineType tempEnums: ReleaseMachineType.values()) {
        if(val.equals(tempEnums.getStatusValue())){
        	enums = tempEnums;
        	break;
        }
    }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getReleaseBaseTypeDesc(String val) {
		String enumsStr = null;
		for (ReleaseMachineType tempEnums: ReleaseMachineType.values()) {
        if(val.equals(tempEnums.getStatusValue())){
        	enumsStr = tempEnums.getStatusName();
        	break;
        }
    }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ReleaseMachineType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}