package com.jce.framework.weixin.api.core.handler;

import com.jce.framework.weixin.api.core.exception.WexinReqException;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

public abstract interface WeiXinReqHandler
{
  public abstract String doRequest(WeixinReqParam paramWeixinReqParam)
    throws WexinReqException;
}