package com.jce.framework.weixin.api.coupon.qrcode;

import net.sf.json.JSONObject;
import com.jce.framework.weixin.api.core.exception.WexinReqException;
import com.jce.framework.weixin.api.core.req.WeiXinReqService;
import com.jce.framework.weixin.api.coupon.qrcode.model.Getticket;
import com.jce.framework.weixin.api.coupon.qrcode.model.GetticketRtn;
import com.jce.framework.weixin.api.coupon.qrcode.model.QrcodeInfo;
import com.jce.framework.weixin.api.coupon.qrcode.model.QrcodeRtnInfo;

public class JwQrcodeAPI
{
  public static QrcodeRtnInfo doAddQrcode(String accesstoken, QrcodeInfo qrcodeInfo)
    throws WexinReqException
  {
    if (accesstoken != null) {
      qrcodeInfo.setAccess_token(accesstoken);
      JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(qrcodeInfo);
      QrcodeRtnInfo qrcodeRtnInfo = (QrcodeRtnInfo)JSONObject.toBean(result, QrcodeRtnInfo.class);
      return qrcodeRtnInfo;
    }
    return null;
  }

  public static GetticketRtn doGetticket(String accesstoken)
    throws WexinReqException
  {
    if (accesstoken != null) {
      Getticket gk = new Getticket();
      gk.setAccess_token(accesstoken);
      JSONObject result = WeiXinReqService.getInstance().doWeinxinReqJson(gk);
      GetticketRtn getticketRtn = (GetticketRtn)JSONObject.toBean(result, GetticketRtn.class);
      return getticketRtn;
    }
    return null;
  }
}