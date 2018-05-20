package com.zml.enums;

//文档目录名
public enum DocumentDirName {
	BANNER("banner", "轮播图"),
	COMMODITY_CLASSIFY("commodity_classify", "商品分类"),
	COMMODITY("commodity", "商品"),
	MERCHANTS("merchants", "商户"),
	RELEASE_TERRITORY("release_territory", "发布土地"),
	RELEASE_FOOD("release_food", "发布粮食"),
	RELEASE_INFO("release_info", "发布综合"),
	RELEASE_MACHINE("release_machine", "发布机车"),
	RELEASE_OTHER("release_other", "发布其他"),
	FEED_BACK_INFO("feed_back_info", "意见反馈");
  
  
  /**
   * 状态名称
   */
  private final String statusName;
  /**
   * 状态值
   */
  private final String statusValue;

  DocumentDirName(String statusValue, String statusName) {
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
	public DocumentDirName getDocumentDirName(String val) {
		DocumentDirName enums = null;
		for (DocumentDirName tempEnums: DocumentDirName.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getDocumentDirNameDesc(String val) {
		String enumsStr = null;
		for (DocumentDirName tempEnums: DocumentDirName.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
  @Override
  public String toString() {
      return "DocumentDirName {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
  }
}
