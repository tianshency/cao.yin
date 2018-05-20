package com.zml.enums;
//订单状态
public enum OrderStatus {
	NO_PAY("0", "未付款"),
	PROCESSING("1", "处理中"),
	ME_Shipped("2", "商家已发货"),
	NO_PAY_BALANCE("3", "未付余款"),
	SUCCESS("4", "订单完成"),
	SYS_CANCEL("5", "系统取消"),
	USER_CANCEL("6", "用户取消"),
	USER_DEL("7", "用户删除");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	OrderStatus(String statusValue, String statusName) {
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
	public OrderStatus getOrderStatus(String val) {
		OrderStatus enums = null;
		for (OrderStatus tempEnums: OrderStatus.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getOrderStatusDesc(String val) {
		String enumsStr = null;
		for (OrderStatus tempEnums: OrderStatus.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "OrderStatus {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
	}