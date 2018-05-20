package com.zml.enums;

//收藏类型
public enum CollectionType {
	COMMODITY("1", "商品"),
	MERCHANTS("2", "商家"),
	ARTICLE("3", "文章"),
	RELEASE_TERRITORY("30", "发布土地"),
	RELEASE_FOOD("31", "发布粮食"),
	RELEASE_MACHINE("32", "发布机车"),
	RELEASE_OTHER("99", "发布其他");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	CollectionType(String statusValue, String statusName) {
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
	public CollectionType getCollectionType(String val) {
		CollectionType enums = null;
		for (CollectionType tempEnums: CollectionType.values()) {
        if(val.equals(tempEnums.getStatusValue())){
        	enums = tempEnums;
        	break;
        }
    }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getCollectionTypeDesc(String val) {
		String enumsStr = null;
		for (CollectionType tempEnums: CollectionType.values()) {
        if(val.equals(tempEnums.getStatusValue())){
        	enumsStr = tempEnums.getStatusName();
        	break;
        }
    }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "CollectionType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}