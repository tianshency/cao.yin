package com.jce.framework.weixin.api.core.req.model.message;

public class TemplateData
{
  private String value;
  private String color;

  public String getValue()
  {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getColor() {
    return this.color;
  }

  public void setColor(String color) {
    this.color = color;
  }
}