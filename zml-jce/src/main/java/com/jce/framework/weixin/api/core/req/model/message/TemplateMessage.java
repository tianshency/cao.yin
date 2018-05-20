package com.jce.framework.weixin.api.core.req.model.message;

public class TemplateMessage
{
  private TemplateData first;
  private TemplateData keynote1;
  private TemplateData keynote2;
  private TemplateData keynote3;
  private TemplateData remark;

  public TemplateData getFirst()
  {
    return this.first;
  }

  public void setFirst(TemplateData first) {
    this.first = first;
  }

  public TemplateData getKeynote1() {
    return this.keynote1;
  }

  public void setKeynote1(TemplateData keynote1) {
    this.keynote1 = keynote1;
  }

  public TemplateData getKeynote2() {
    return this.keynote2;
  }

  public void setKeynote2(TemplateData keynote2) {
    this.keynote2 = keynote2;
  }

  public TemplateData getKeynote3() {
    return this.keynote3;
  }

  public void setKeynote3(TemplateData keynote3) {
    this.keynote3 = keynote3;
  }

  public TemplateData getRemark() {
    return this.remark;
  }

  public void setRemark(TemplateData remark) {
    this.remark = remark;
  }
}