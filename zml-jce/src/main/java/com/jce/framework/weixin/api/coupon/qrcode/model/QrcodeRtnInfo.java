package com.jce.framework.weixin.api.coupon.qrcode.model;

public class QrcodeRtnInfo
{
  private String errcode;
  private String errmsg;
  private String ticket;

  public String getErrcode()
  {
    return this.errcode;
  }
  public void setErrcode(String errcode) {
    this.errcode = errcode;
  }
  public String getErrmsg() {
    return this.errmsg;
  }
  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
  public String getTicket() {
    return this.ticket;
  }
  public void setTicket(String ticket) {
    this.ticket = ticket;
  }
}