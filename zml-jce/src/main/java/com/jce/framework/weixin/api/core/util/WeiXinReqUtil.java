package com.jce.framework.weixin.api.core.util;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.Logger;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import com.jce.framework.weixin.api.core.handler.WeiXinReqHandler;
import com.jce.framework.weixin.api.core.req.WeiXinReqService;
import com.jce.framework.weixin.api.core.req.model.WeixinReqConfig;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

public class WeiXinReqUtil
{
  private static Logger logger = Logger.getLogger(WeiXinReqUtil.class);

  private static Map<String, WeixinReqConfig> REQ_MAPPING = new HashMap();

  private static Map<String, WeiXinReqHandler> MAPPING_HANDLER = new HashMap();

  private static Properties file_content_type = null;

  public static Object getObjectByClassName(String className)
  {
    Class obj = null;
    try {
      obj = Class.forName(className);
      return obj.newInstance();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return obj;
  }

  public static void initReqConfig(String configName)
    throws JDOMException, IOException
  {
    InputStream is = WeiXinReqService.class.getClassLoader().getResourceAsStream(configName);
    SAXBuilder xmlBuilder = new SAXBuilder();
    Document doc = xmlBuilder.build(is);
    Element objRoot = doc.getRootElement();
    List<Element> lstMapping = objRoot.getChildren("req");
    WeixinReqConfig objConfig = null;
    for (Element mapping : lstMapping) {
      String key = mapping.getAttribute("key").getValue();
      String method = mapping.getAttribute("method").getValue();
      String url = mapping.getAttribute("url").getValue();
      String mappingHandler = "com.jce.framework.weixin.api.core.handler.impl.WeixinReqDefaultHandler";
      String datatype = WeiXinConstant.PARAM_DATA_TYPE;
      if (mapping.getAttribute("mappingHandler") != null) {
        mappingHandler = mapping.getAttribute("mappingHandler").getValue();
      }
      if (mapping.getAttribute("datatype") != null) {
        datatype = mapping.getAttribute("datatype").getValue();
      }
      objConfig = new WeixinReqConfig();
      objConfig.setKey(key);
      objConfig.setMappingHandler(mappingHandler);
      objConfig.setMethod(method);
      objConfig.setUrl(url);
      objConfig.setDatatype(datatype);
      registerMapping(key, objConfig);
    }
  }

  public static void registerMapping(String key, WeixinReqConfig weixinReqConfig)
  {
    REQ_MAPPING.put(key, weixinReqConfig);
  }

  public static WeixinReqConfig getWeixinReqConfig(String key)
  {
    return (WeixinReqConfig)REQ_MAPPING.get(key);
  }

  public static WeiXinReqHandler getMappingHander(String className)
  {
    WeiXinReqHandler handler = (WeiXinReqHandler)MAPPING_HANDLER.get(className);
    if (handler == null) {
      handler = (WeiXinReqHandler)getObjectByClassName(className);
      MAPPING_HANDLER.put(className, handler);
    }
    return handler;
  }

  public static Map getWeixinReqParam(WeixinReqParam weixinReqParam)
  {
    Gson gson = new Gson();
    Map params = null;
    try {
      String json = gson.toJson(weixinReqParam);
      params = (Map)gson.fromJson(json, Map.class);
    }
    catch (Exception e) {
      logger.error("处理参数解析出错", e);
      params = new HashMap();
      params.put("access_token", weixinReqParam.getAccess_token());
    }
    return params;
  }

  public static String getWeixinParamJson(WeixinReqParam weixinReqParam)
  {
    Gson gson = new Gson();
    String json = gson.toJson(weixinReqParam);
    return json;
  }

  public static String getFileContentType(String fileSuffix)
  {
    if (file_content_type == null) {
      file_content_type = new Properties();
      InputStream in = WeiXinReqService.class.getClassLoader().getResourceAsStream("fie-content-type.properties");
      try {
        file_content_type.load(in);
        in.close();
      }
      catch (IOException e) {
        e.printStackTrace();
        file_content_type = new Properties();
      }
    }
    return (String)file_content_type.get(fileSuffix);
  }

  public static String getFileSuffix(String contentType)
  {
    if (file_content_type == null) {
      file_content_type = new Properties();
      InputStream in = WeiXinReqService.class.getClassLoader().getResourceAsStream("fie-content-type.properties");
      try {
        file_content_type.load(in);
        in.close();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }

    String fileSuffix = "";
    Set type = file_content_type.entrySet();
    Iterator it = type.iterator();
    while (it.hasNext()) {
      Map.Entry entity = (Map.Entry)it.next();
      if (entity.getValue().equals(contentType)) {
        fileSuffix = (String)entity.getKey();
        break;
      }
    }
    return fileSuffix;
  }

  public static void main(String[] args) throws JDOMException, IOException
  {
    String realPath = WeiXinReqUtil.class.getClassLoader().getResource("").getFile();
    System.out.println(realPath);
    initReqConfig("weixin-reqcongfig.xml");
  }
}