package com.jce.framework.weixin.api.core.handler.impl;

import java.util.Map;
import org.apache.log4j.Logger;
import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.exception.WexinReqException;
import com.jce.framework.weixin.api.core.handler.WeiXinReqHandler;
import com.jce.framework.weixin.api.core.req.model.DownloadMedia;
import com.jce.framework.weixin.api.core.req.model.WeixinReqConfig;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;
import com.jce.framework.weixin.api.core.util.HttpRequestProxy;
import com.jce.framework.weixin.api.core.util.WeiXinReqUtil;

public class WeixinReqMediaDownHandler
  implements WeiXinReqHandler
{
  private static Logger logger = Logger.getLogger(WeixinReqMediaDownHandler.class);

  public String doRequest(WeixinReqParam weixinReqParam)
    throws WexinReqException
  {
    String strReturnInfo = "";
    if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
      DownloadMedia downloadMedia = (DownloadMedia)weixinReqParam;
      ReqType reqType = (ReqType)weixinReqParam.getClass().getAnnotation(ReqType.class);
      WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
      if (objConfig != null) {
        String reqUrl = objConfig.getUrl();
        String filePath = downloadMedia.getFilePath();
        Map parameters = WeiXinReqUtil.getWeixinReqParam(weixinReqParam);
        parameters.remove("filePathName");
        strReturnInfo = HttpRequestProxy.downMadGet(reqUrl, parameters, "UTF-8", filePath, downloadMedia.getMedia_id());
      }
    } else {
      logger.info("没有找到对应的配置信息");
    }
    return strReturnInfo;
  }
}