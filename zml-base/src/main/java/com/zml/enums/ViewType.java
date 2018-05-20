package com.zml.enums;

//用户浏览类型
public enum ViewType {
	RELEASE_TERRITORY("1", "发布土地"),
	RELEASE_FOOD("2", "发布粮食"),
	RELEASE_MACHINE("3", "发布机车"),
	COMMODITY("4", "商品"),
	RELEASE_INFO("5", "发布综合信息"),
	RELEASE_OTHER("99", "发布其他");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	ViewType(String statusValue, String statusName) {
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
	public ViewType getViewType(String val) {
		ViewType enums = null;
		for (ViewType tempEnums: ViewType.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enums = tempEnums;
          	break;
          }
      }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getViewTypeDesc(String val) {
		String enumsStr = null;
		for (ViewType tempEnums: ViewType.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enumsStr = tempEnums.getStatusName();
          	break;
          }
      }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "ViewType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
