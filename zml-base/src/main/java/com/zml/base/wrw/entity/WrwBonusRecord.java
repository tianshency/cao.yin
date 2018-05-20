package com.zml.base.wrw.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
//奖金记录
@Entity
@Table(name = "wrw_bonus_record", schema = "")
@SuppressWarnings("serial")
public class WrwBonusRecord {
	private String id;//主键 
	private String userId;//当前人 
	private BigDecimal amount;//奖金 
	private BigDecimal oldAmount;//历史奖金
	private Date updateDate;//修改时间 
	private Date createDate;//创建时间 
	private String type;//类型1级、2级、3级、4数量奖金
	private String status;//状态1加、2减
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(name ="USER_ID",nullable=true)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name ="AMOUNT",nullable=true)
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Column(name ="OLD_AMOUNT",nullable=true)
	public BigDecimal getOldAmount() {
		return oldAmount;
	}
	public void setOldAmount(BigDecimal oldAmount) {
		this.oldAmount = oldAmount;
	}
	@Column(name ="TYPE",nullable=true)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name ="STATUS",nullable=true)
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}
