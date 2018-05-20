package com.zml.enums;

//发布信息文档 类型
public enum ReleaseDocType {
	TERRITORY("1", "土地"),
	FOOD("2", "粮食"),
	ENGINE("3", "机车"),
	RECRUITMENT("4", "招工"),
	INTEGRATED("5", "综合");
    
    
    /**
     * 状态名称
     */
    private final String statusName;
    /**
     * 状态值
     */
    private final String statusValue;

    ReleaseDocType(String statusValue, String statusName) {
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
  	public ReleaseDocType getReleaseDocType(String val) {
  		ReleaseDocType enums = null;
  		for (ReleaseDocType tempEnums: ReleaseDocType.values()) {
              if(val.equals(tempEnums.getStatusValue())){
              	enums = tempEnums;
              	break;
              }
          }
  	    return enums;
  	}
  	
  	//根据 值获取对应枚举描述
  	public String getReleaseDocTypeDesc(String val) {
  		String enumsStr = null;
  		for (ReleaseDocType tempEnums: ReleaseDocType.values()) {
              if(val.equals(tempEnums.getStatusValue())){
              	enumsStr = tempEnums.getStatusName();
              	break;
              }
          }
  	    return enumsStr;
  	}
    @Override
    public String toString() {
        return "ReleaseDocType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
    }
}
