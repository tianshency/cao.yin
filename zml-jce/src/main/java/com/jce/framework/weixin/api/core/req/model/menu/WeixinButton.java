package com.jce.framework.weixin.api.core.req.model.menu;

import java.util.List;

public class WeixinButton
{
  private String type;
  private String name;
  private String key;
  private String url;
  private List<WeixinButton> sub_button;

  public String getType()
  {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  public List<WeixinButton> getSub_button() {
    return this.sub_button;
  }

  public void setSub_button(List<WeixinButton> sub_button) {
    this.sub_button = sub_button;
  }
}