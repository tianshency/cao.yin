package com.jce.framework.weixin.api.coupon.qrcode.model;

public class GetticketRtn
{
  private String errcode;
  private String errmsg;
  private String ticket;
  private String expires_in;

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
  public String getExpires_in() {
    return this.expires_in;
  }
  public void setExpires_in(String expires_in) {
    this.expires_in = expires_in;
  }
}