package com.zml.enums.wrw;

//奖金类型
public enum BonusType {
	//类型1级、2级、3级、4数量奖金
	T_ONE("1", "一级推荐"),
	T_TWO("2", "二级推荐"),
	T_THREE("3", "三级推荐"),
	T_FOUR("4", "数量推荐"),
	T_SUB("-1", "减少");
    
    
    /**
     * 名称
     */
    private final String name;
    /**
     * 值
     */
    private final String value;

    BonusType(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
   //根据 值获取对应枚举类型
  	public BonusType getBonusType(String val) {
  		BonusType enums = null;
  		for (BonusType tempEnums: BonusType.values()) {
              if(tempEnums.getValue().equals(val)){
              	enums = tempEnums;
              	break;
              }
          }
  	    return enums;
  	}
  	
  	//根据 值获取对应枚举描述
  	public String getBonusTypeDesc(String val) {
  		String enumsStr = null;
  		for (BonusType tempEnums: BonusType.values()) {
              if(tempEnums.getValue().equals(val)){
              	enumsStr = tempEnums.getName();
              	break;
              }
          }
  	    return enumsStr;
  	}
    @Override
    public String toString() {
        return "BonusType {" + "statusName=" + name + ", statusValue=" + value + '}';
    }
}