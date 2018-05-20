package com.zml.enums;

public enum YesOrNoStr {
	NO("N", "否"),
	YES("Y", "是");
    
    
    /**
     * 状态名称
     */
    private final String statusName;
    /**
     * 状态值
     */
    private final String statusValue;

    YesOrNoStr(String statusValue, String statusName) {
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
  	public YesOrNoStr getYesOrNo(String val) {
  		YesOrNoStr enums = null;
  		for (YesOrNoStr tempEnums: YesOrNoStr.values()) {
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
  		for (YesOrNoStr tempEnums: YesOrNoStr.values()) {
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
