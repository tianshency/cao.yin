package com.zml.base.wrw.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//推荐记录
@Entity
@Table(name = "wrw_recommend_record", schema = "")
@SuppressWarnings("serial")
public class WrwRecommendRecord {
	private String id;//主键 
	private String currUser;//当前人 
	private String recommenUser;//推荐人 
	private String secondUser;//二级推荐人
	private String thirdUser;//三级推荐人
	private String type;//类型1级、2级、3级
	private Date updateDate;//修改时间 
	private Date createDate;//创建时间 
	private String isBonus;//是否分红 
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
	@Column(name ="CURR_USER",nullable=true)
	public String getCurrUser() {
		return currUser;
	}
	public void setCurrUser(String currUser) {
		this.currUser = currUser;
	}
	@Column(name ="RECOMMEN_USER",nullable=true)
	public String getRecommenUser() {
		return recommenUser;
	}
	public void setRecommenUser(String recommenUser) {
		this.recommenUser = recommenUser;
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
	@Column(name ="IS_BONUS",nullable=true)
	public String getIsBonus() {
		return isBonus;
	}
	public void setIsBonus(String isBonus) {
		this.isBonus = isBonus;
	}
	
	@Column(name ="type",nullable=true)
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name ="SECOND_USER",nullable=true)
	public String getSecondUser() {
		return secondUser;
	}
	public void setSecondUser(String secondUser) {
		this.secondUser = secondUser;
	}
	
	@Column(name ="THIRD_USER",nullable=true)
	public String getThirdUser() {
		return thirdUser;
	}
	public void setThirdUser(String thirdUser) {
		this.thirdUser = thirdUser;
	}
}
