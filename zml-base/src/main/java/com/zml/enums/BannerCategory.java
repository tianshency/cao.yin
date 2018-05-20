package com.zml.enums;

//轮播分类
public enum BannerCategory {
	TOP_BANNER("top_banner", "顶部轮播"),
	HEADLINES_BANNER("headlines_banner", "头条轮播");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;

	BannerCategory(String statusValue, String statusName) {
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
	public BannerCategory getBannerCategory(String val) {
		BannerCategory enums = null;
		for (BannerCategory tempEnums: BannerCategory.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enums = tempEnums;
          	break;
          }
      }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getBannerCategoryDesc(String val) {
		String enumsStr = null;
		for (BannerCategory tempEnums: BannerCategory.values()) {
          if(val.equals(tempEnums.getStatusValue())){
          	enumsStr = tempEnums.getStatusName();
          	break;
          }
      }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "BannerCategory {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}