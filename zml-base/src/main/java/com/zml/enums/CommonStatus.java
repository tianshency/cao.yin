package com.zml.enums;
//公共状态
public enum CommonStatus {
	DEL("0", "删除"),
	NORMAL("1", "正常"),
	FAILURE("2", "失效");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	CommonStatus(String statusValue, String statusName) {
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
	public CommonStatus getCommonStatus(String val) {
		CommonStatus enums = null;
		for (CommonStatus tempEnums: CommonStatus.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getCommonStatusDesc(String val) {
		String enumsStr = null;
		for (CommonStatus tempEnums: CommonStatus.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "CommonStatus {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
