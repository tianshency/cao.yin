package com.jce.framework.weixin.api.core.req.model.kfaccount;

import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

@ReqType("kfaccountUploadheadimg")
public class KfaccountUploadheadimg extends WeixinReqParam
{
  private String type;
  private String filePathName;
  private String kf_account;

  public String getKf_account()
  {
    return this.kf_account;
  }

  public void setKf_account(String kf_account) {
    this.kf_account = kf_account;
  }

  public String getType() {
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