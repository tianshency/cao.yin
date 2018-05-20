package com.jce.framework.weixin.api.core.req.model.message;

import com.jce.framework.weixin.api.core.annotation.ReqType;
import com.jce.framework.weixin.api.core.req.model.WeixinReqParam;

@ReqType("industryTemplateMessageSend")
public class IndustryTemplateMessageSend extends WeixinReqParam
{
  private String touser;
  private String template_id;
  private String url;
  private String topcolor;
  private TemplateMessage data;

  public String getTouser()
  {
    return this.touser;
  }

  public void setTouser(String touser) {
    this.touser = touser;
  }

  public String getTemplate_id() {
    return this.template_id;
  }

  public void setTemplate_id(String template_id) {
    this.template_id = template_id;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTopcolor() {
    return this.topcolor;
  }

  public void setTopcolor(String topcolor) {
    this.topcolor = topcolor;
  }

  public TemplateMessage getData() {
    return this.data;
  }

  public void setData(TemplateMessage data) {
    this.data = data;
  }
}