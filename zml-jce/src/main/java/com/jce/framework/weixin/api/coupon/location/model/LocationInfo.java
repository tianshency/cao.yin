package com.jce.framework.weixin.api.coupon.location.model;

import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

@ReqType("getLocationInfo")
public class LocationInfo extends WeixinReqParam
{
  private String filePathName;

  public String getFilePathName()
  {
    return this.filePathName;
  }

  public void setFilePathName(String filePathName) {
    this.filePathName = filePathName;
  }
}