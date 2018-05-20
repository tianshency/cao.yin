package com.zml.enums;
//文件分类
public enum FileType {
  IMG("1", "图片"),
  VIDEO("2", "视频"),
  DOC("3", "文档");

	/**
	 * 状态名称
	 */
	private final String statusName;
	/**
	 * 状态值
	 */
	private final String statusValue;
	
	FileType(String statusValue, String statusName) {
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
	public FileType getFileType(String val) {
		FileType enums = null;
		for (FileType tempEnums: FileType.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enums = tempEnums;
            	break;
            }
        }
	    return enums;
	}
	
	//根据 值获取对应枚举描述
	public String getFileTypeDesc(String val) {
		String enumsStr = null;
		for (FileType tempEnums: FileType.values()) {
            if(val.equals(tempEnums.getStatusValue())){
            	enumsStr = tempEnums.getStatusName();
            	break;
            }
        }
	    return enumsStr;
	}
	@Override
	public String toString() {
	    return "FileType {" + "statusName=" + statusName + ", statusValue=" + statusValue + '}';
	}
}