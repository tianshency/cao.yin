package com.jce.framework.weixin.api.core.req.model.menu;

import java.util.List;

import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

@ReqType("menuCreate")
public class MenuCreate extends WeixinReqParam
{
  private List<WeixinButton> button;

  public List<WeixinButton> getButton()
  {
    return this.button;
  }

  public void setButton(List<WeixinButton> button) {
    this.button = button;
  }
}