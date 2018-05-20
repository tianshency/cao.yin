package com.jce.framework.weixin.api.core.req.model;

import com.jce.framework.weixin.api.core.annotation.ReqType;

@ReqType("mediaget")
public class DownloadMedia extends WeixinReqParam
{
  private String media_id;
  private String filePath;

  public String getMedia_id()
  {
    return this.media_id;
  }

  public void setMedia_id(String media_id) {
    this.media_id = media_id;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }
}