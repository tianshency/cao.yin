package com.jce.framework.weixin.api.core.handler.impl;

import java.util.Map;
import org.apache.log4j.Logger;
import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.exception.WexinReqException;
import com.jce.framework.weixin.api.core.handler.WeiXinReqHandler;
import com.jce.framework.weixin.api.core.req.model.WeixinReqConfig;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;
import com.jce.framework.weixin.api.core.util.HttpRequestProxy;
import com.jce.framework.weixin.api.core.util.WeiXinConstant;
import com.jce.framework.weixin.api.core.util.WeiXinReqUtil;

public class WeixinReqDefaultHandler
  implements WeiXinReqHandler
{
  private static Logger logger = Logger.getLogger(WeixinReqDefaultHandler.class);

  public String doRequest(WeixinReqParam weixinReqParam)
    throws WexinReqException
  {
    String strReturnInfo = "";
    if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
      ReqType reqType = (ReqType)weixinReqParam.getClass().getAnnotation(ReqType.class);
      WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
      if (objConfig != null) {
        String reqUrl = objConfig.getUrl();
        String method = objConfig.getMethod();
        String datatype = objConfig.getDatatype();
        Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
        if (WeiXinConstant.JSON_DATA_TYPE.equalsIgnoreCase(datatype)) {
          parameters.clear();
          parameters.put("access_token", weixinReqParam.getAccess_token());
          weixinReqParam.setAccess_token(null);
          String jsonData = WeiXinReqUtil.getWeixinParamJson(weixinReqParam);
          strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
        }
        else if (WeiXinConstant.REQUEST_GET.equalsIgnoreCase(method)) {
          strReturnInfo = HttpRequestProxy.doGet(reqUrl, parameters, "UTF-8");
        } else {
          strReturnInfo = HttpRequestProxy.doPost(reqUrl, parameters, "UTF-8");
        }
      }
    }
    else {
      logger.info("没有找到对应的配置信息");
    }
    return strReturnInfo;
  }
}