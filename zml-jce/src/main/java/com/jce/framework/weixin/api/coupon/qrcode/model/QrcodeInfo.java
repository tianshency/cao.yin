package com.jce.framework.weixin.api.coupon.qrcode.model;

import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

@ReqType("getGrcodeInfo")
public class QrcodeInfo extends WeixinReqParam
{
  private String action_name;
  private ActionInfo action_info;

  public String getAction_name()
  {
    return this.action_name;
  }
  public void setAction_name(String action_name) {
    this.action_name = action_name;
  }
  public ActionInfo getAction_info() {
    return this.action_info;
  }
  public void setAction_info(ActionInfo action_info) {
    this.action_info = action_info;
  }
}