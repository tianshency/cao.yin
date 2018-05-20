package com.zml.enums.wrw;
//注册状态

public enum RegeditSts {
	R_ING("1", "注册中"),
	R_SUCC("2", "成功"),
	R_FLASE("3", "注册失败"),
	R_OLD("4", "历史注册");
    
    
	/**
     * 名称
     */
    private final String name;
    /**
     * 值
     */
    private final String value;

    RegeditSts(String value, String name) {
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
  	public RegeditSts getRegeditSts(String val) {
  		RegeditSts enums = null;
  		for (RegeditSts tempEnums: RegeditSts.values()) {
              if(tempEnums.getValue().equals(val)){
              	enums = tempEnums;
              	break;
              }
          }
  	    return enums;
  	}
  	
  	//根据 值获取对应枚举描述
  	public String getRegeditStsDesc(String val) {
  		String enumsStr = null;
  		for (RegeditSts tempEnums: RegeditSts.values()) {
              if(tempEnums.getValue().equals(val)){
              	enumsStr = tempEnums.getName();
              	break;
              }
          }
  	    return enumsStr;
  	}
    @Override
    public String toString() {
        return "RegeditSts {" + "statusName=" + name + ", statusValue=" + value + '}';
    }
}