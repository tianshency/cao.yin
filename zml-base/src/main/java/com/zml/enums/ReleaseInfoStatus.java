package com.zml.enums;

//发布信息 状态
public enum ReleaseInfoStatus {
	NO_APPROVAL("0", "未审批"),
	APPROVAL_PASS("1", "审批通过"),
	APPROVAL_REFUSE("2", "审批拒绝");
    
    
    /**
     * 状态名称
     */
    private final String statusName;
    /**
     * 状态值
     */
    private final String statusValue;

    ReleaseInfoStatus(String statusValue, String statusName) {
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
  	public ReleaseInfoStatus getYesOrNo(String val) {
  		ReleaseInfoStatus enums = null;
  		for (ReleaseInfoStatus tempEnums: ReleaseInfoStatus.values()) {
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
  		for (ReleaseInfoStatus tempEnums: ReleaseInfoStatus.values()) {
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