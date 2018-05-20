package com.jce.framework.weixin.api.core.req.model;

public class WeixinReqConfig
{
  private String key;
  private String url;
  private String method;
  private String mappingHandler;
  private String datatype;

  public String getDatatype()
  {
    return this.datatype;
  }

  public void setDatatype(String datatype) {
    this.datatype = datatype;
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMethod() {
    return this.method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getMappingHandler() {
    return this.mappingHandler;
  }

  public void setMappingHandler(String mappingHandler) {
    this.mappingHandler = mappingHandler;
  }
}