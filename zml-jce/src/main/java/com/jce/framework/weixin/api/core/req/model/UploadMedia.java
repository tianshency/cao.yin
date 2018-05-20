package com.jce.framework.weixin.api.core.req.model;

import com.jce.framework.weixin.api.core.annotation.ReqType;

@ReqType("mediaUpload")
public class UploadMedia extends WeixinReqParam
{
  private String type;
  private String filePathName;

  public String getType()
  {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getFilePathName() {
    return this.filePathName;
  }

  public void setFilePathName(String filePathName) {
    this.filePathName = filePathName;
  }
}