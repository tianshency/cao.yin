package com.zml.enums;
//购物车状态
public enum CartsStatus {
	DEL("0", "删除"),
	NORMAL("1", "正常"),
	NO_IN_STOCK("2", "无库存"),
	SUCCESS("8", "完成");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	CartsStatus(String statusValue, String statusName) {
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
	public CartsStatus getCartsStatus(String val) {
		CartsStatus enums = null;
		for (CartsStatus tempEnums: CartsStatus.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getCartsStatusDesc(String val) {
		String enumsStr = null;
		for (CartsStatus tempEnums: CartsStatus.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "CartsStatus {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}
