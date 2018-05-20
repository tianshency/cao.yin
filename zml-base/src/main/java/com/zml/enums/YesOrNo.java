package com.zml.enums;

public enum YesOrNo {
	NO(0, "否"),
	YES(1, "是");
    
    
    /**
     * 状态名称
     */
    private final String statusName;
    /**
     * 状态值
     */
    private final Integer statusValue;

    YesOrNo(Integer statusValue, String statusName) {
        this.statusName = statusName;
        this.statusValue = statusValue;
    }

    public String getStatusName() {
        return statusName;
    }

    public Integer getStatusValue() {
        return statusValue;
    }
   //根据 值获取对应枚举类型
  	public YesOrNo getYesOrNo(String val) {
  		YesOrNo enums = null;
  		for (YesOrNo tempEnums: YesOrNo.values()) {
              if(val.equals(tempEnums.getStatusValue())){
              	enums = tempEnums;
              	break;
              }
          }
  	    return enums;
  	}
  	
  	//根据 值获取对应枚举描述
  	public String getYesOrNoDesc(String val) {
  		String enumsStr = null;
  		for (YesOrNo tempEnums: YesOrNo.values()) {
              if(val.equals(tempEnums.getStatusValue())){
              	enumsStr = tempEnums.getStatusName();
              	break;
              }
          }
  	    return enumsStr;
  	}
    @Override
    public String toString() {
        return "YesOrNo {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
    }
}
