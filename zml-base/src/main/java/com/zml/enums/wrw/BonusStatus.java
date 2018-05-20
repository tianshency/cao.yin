package com.zml.enums.wrw;

//奖金状态
public enum BonusStatus {
	ADD("1", "加"),
	SUB("2", "减");
  
  
  /**
   * 名称
   */
  private final String name;
  /**
   * 值
   */
  private final String value;

  BonusStatus(String value, String name) {
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
	public BonusStatus getBonusStatus(String val) {
		BonusStatus enums = null;
		for (BonusStatus tempEnums: BonusStatus.values()) {
            if(tempEnums.getValue().equals(val)){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getBonusStatusDesc(String val) {
		String enumsStr = null;
		for (BonusStatus tempEnums: BonusStatus.values()) {
            if(tempEnums.getValue().equals(val)){
            	enumsStr = tempEnums.getName();
            	break;
            }
        }
	    return enumsStr;
	}
  @Override
  public String toString() {
      return "BonusStatus {" + "statusName=" + name + ", statusValue=" + value + '}';
  }
}