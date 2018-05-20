package com.jce.framework.weixin.api.core.handler.impl;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.exception.WexinReqException;
import com.jce.framework.weixin.api.core.handler.WeiXinReqHandler;
import com.jce.framework.weixin.api.core.req.model.WeixinReqConfig;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;
import com.jce.framework.weixin.api.core.req.model.message.IndustryTemplateMessageSend;
import com.jce.framework.weixin.api.core.req.model.message.TemplateMessage;
import com.jce.framework.weixin.api.core.util.HttpRequestProxy;
import com.jce.framework.weixin.api.core.util.WeiXinReqUtil;

public class WeixinReqTemplateMessageHandler
  implements WeiXinReqHandler
{
  private static Logger logger = Logger.getLogger(WeixinReqTemplateMessageHandler.class);

  public String doRequest(WeixinReqParam weixinReqParam)
    throws WexinReqException
  {
    String strReturnInfo = "";
    if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
      ReqType reqType = (ReqType)weixinReqParam.getClass().getAnnotation(ReqType.class);
      WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
      if (objConfig != null) {
        String reqUrl = objConfig.getUrl();
        IndustryTemplateMessageSend mc = (IndustryTemplateMessageSend)weixinReqParam;
        Map parameters = new HashMap();
        parameters.put("access_token", mc.getAccess_token());
        String jsonData = getMsgJson(mc);
        logger.info("处理模板消息" + jsonData);
        strReturnInfo = HttpRequestProxy.doJsonPost(reqUrl, parameters, jsonData);
      }
    } else {
      logger.info("没有找到对应的配置信息");
    }
    return strReturnInfo;
  }

  private String getMsgJson(IndustryTemplateMessageSend mc)
  {
    StringBuffer json = new StringBuffer();
    Gson gson = new Gson();
    TemplateMessage tm = mc.getData();
    mc.setData(null);
    String objJson = gson.toJson(mc);
    mc.setData(tm);
    json.append(objJson);
    json.setLength(json.length() - 1);
    json.append(",");
    json.append("\"data\":{");

    objJson = gson.toJson(tm.getFirst());
    json.append(" \"first\":");
    json.append(objJson);
    json.append(",");
    objJson = gson.toJson(tm.getKeynote1());
    json.append(" \"keynote1\":");
    json.append(objJson);
    json.append(",");
    objJson = gson.toJson(tm.getKeynote2());
    json.append(" \"keynote2\":");
    json.append(objJson);
    json.append(",");
    objJson = gson.toJson(tm.getKeynote3());
    json.append(" \"keynote3\":");
    json.append(objJson);
    json.append(",");
    objJson = gson.toJson(tm.getRemark());
    json.append(" \"remark\":");
    json.append(objJson);
    json.append("}}");
    return json.toString();
  }
}