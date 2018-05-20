package com.zml.enums;

//发布内容类型
public enum ReleaseContentType {
	RENT("1", "出租"),
	FOR_RENT("2", "求租"),
	SELL("3", "出售"),
	BUYING("4", "求购");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	ReleaseContentType(String statusValue, String statusName) {
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
	public ReleaseContentType getReleaseBaseType(String val) {
		ReleaseContentType enums = null;
		for (ReleaseContentType tempEnums: ReleaseContentType.values()) {
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
		for (ReleaseContentType tempEnums: ReleaseContentType.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enumsStr = tempEnums.getStatusName();
          	break;
          }
      }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ReleaseContentType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}