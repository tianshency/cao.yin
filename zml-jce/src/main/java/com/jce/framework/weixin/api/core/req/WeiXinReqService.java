package com.jce.framework.weixin.api.core.req;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import net.sf.json.JSONObject;
import org.jdom.JDOMException;
import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.exception.WexinReqException;
import com.jce.framework.weixin.api.core.handler.WeiXinReqHandler;
import com.jce.framework.weixin.api.core.req.model.DownloadMedia;
import com.jce.framework.weixin.api.core.req.model.WeixinReqConfig;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;
import com.jce.framework.weixin.api.core.util.WeiXinConstant;
import com.jce.framework.weixin.api.core.util.WeiXinReqUtil;

public class WeiXinReqService
{
  private static WeiXinReqService weiXinReqUtil = null;

  private WeiXinReqService()
  {
    String realPath = WeiXinReqService.class.getClassLoader().getResource("").getFile();
    try {
      WeiXinReqUtil.initReqConfig("weixin-reqcongfig.xml");
    }
    catch (JDOMException e) {
      e.printStackTrace();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static WeiXinReqService getInstance()
  {
    if (weiXinReqUtil == null)
    {
      synchronized (WeiXinReqService.class)
      {
        if (weiXinReqUtil == null) {
          weiXinReqUtil = new WeiXinReqService();
        }
      }
    }
    return weiXinReqUtil;
  }

  public String doWeinxinReq(WeixinReqParam weixinReqParam)
    throws WexinReqException
  {
    String strReturnInfo = "";
    if (weixinReqParam.getClass().isAnnotationPresent(ReqType.class)) {
      ReqType reqType = (ReqType)weixinReqParam.getClass().getAnnotation(ReqType.class);
      WeixinReqConfig objConfig = WeiXinReqUtil.getWeixinReqConfig(reqType.value());
      WeiXinReqHandler handler = WeiXinReqUtil.getMappingHander(objConfig.getMappingHandler());
      strReturnInfo = handler.doRequest(weixinReqParam);
    }
    return strReturnInfo;
  }

  public JSONObject doWeinxinReqJson(WeixinReqParam weixinReqParam)
    throws WexinReqException
  {
    String strResult = doWeinxinReq(weixinReqParam);
    JSONObject result = JSONObject.fromObject(strResult);
    Object error = result.get(WeiXinConstant.RETURN_ERROR_INFO_CODE);
    if ((error != null) && (Integer.parseInt(error.toString()) != 0)) {
      throw new WexinReqException(result.toString());
    }
    return result;
  }

  public static void main(String[] args) {
    String ddd = "";
    try
    {
      DownloadMedia g = new DownloadMedia();
      g.setFilePath("H:/temp");
      g.setMedia_id("nH-tzebPcZY41Hlao3mjPHpUHHJSIbfP8hbGJy73LUj5BfvVDV9b84uIpZ8Yjlzw");
      g.setAccess_token("bbkXUUyC6F85R_vh7FOokDZn54P4jY6RVg8rvtzd0D10nIgd7Ksg7bBc8mncX6SZ1mMEI7v1q1OBtWoWG8--iR6ohe3kXbx5jUTHGAjGPAU");
      ddd = getInstance().doWeinxinReq(g);

      System.out.println(ddd);
    }
    catch (WexinReqException e) {
      e.printStackTrace();
    }
  }
}