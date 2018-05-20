package com.jce.framework.weixin.api.coupon.qrcode.model;

public class Card
{
  private String card_id;
  private String code;
  private String openid;
  private Integer expire_seconds;
  private Boolean is_unique_code;
  private String balance;
  private Long outer_id;

  public String getCard_id()
  {
    return this.card_id;
  }
  public void setCard_id(String card_id) {
    this.card_id = card_id;
  }
  public String getCode() {
    return this.code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  public String getOpenid() {
    return this.openid;
  }
  public void setOpenid(String openid) {
    this.openid = openid;
  }
  public Integer getExpire_seconds() {
    return this.expire_seconds;
  }
  public void setExpire_seconds(Integer expire_seconds) {
    this.expire_seconds = expire_seconds;
  }
  public Boolean getIs_unique_code() {
    return this.is_unique_code;
  }
  public void setIs_unique_code(Boolean is_unique_code) {
    this.is_unique_code = is_unique_code;
  }
  public String getBalance() {
    return this.balance;
  }
  public void setBalance(String balance) {
    this.balance = balance;
  }
  public Long getOuter_id() {
    return this.outer_id;
  }
  public void setOuter_id(Long outer_id) {
    this.outer_id = outer_id;
  }
}