package com.zml.enums;
//头条 标识
public enum HeadLinesFlag {
	SYS_ANNOUNCEMENT("1", "系统公告"),
	OTHER("1", "是");
    
    
    /**
     * 状态名称
     */
    private final String statusName;
    /**
     * 状态值
     */
    private final String statusValue;

    HeadLinesFlag(String statusValue, String statusName) {
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
  	public HeadLinesFlag getHeadLinesFlag(String val) {
  		HeadLinesFlag enums = null;
  		for (HeadLinesFlag tempEnums: HeadLinesFlag.values()) {
              if(val.equals(tempEnums.getStatusValue())){
              	enums = tempEnums;
              	break;
              }
          }
  	    return enums;
  	}
  	
  	//根据 值获取对应枚举描述
  	public String getHeadLinesFlagDesc(String val) {
  		String enumsStr = null;
  		for (HeadLinesFlag tempEnums: HeadLinesFlag.values()) {
              if(val.equals(tempEnums.getStatusValue())){
              	enumsStr = tempEnums.getStatusName();
              	break;
              }
          }
  	    return enumsStr;
  	}
    @Override
    public String toString() {
        return "HeadLinesFlag {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
    }
}
