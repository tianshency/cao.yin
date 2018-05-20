package com.zml.base.loan.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 催收记录
 * @author onlineGenerator
 * @date 2017-03-01 22:28:14
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_collection_record", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanCollectionRecordEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.Integer version;
	/**合同*/
	@Excel(name="合同")
	private java.lang.String contractId;
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**催收人*/
	@Excel(name="催收人")
	private java.lang.String operatorId;
	/**催收时间*/
	@Excel(name="催收时间",format = "yyyy-MM-dd")
	private java.util.Date collectionDate;
	/**催收方式*/
	@Excel(name="催收方式")
	private java.lang.String collectionWay;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remark;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public java.lang.String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  主键
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true,length=20)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true,length=20)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  版本
	 */
	@Column(name ="VERSION",nullable=true,length=5)
	public java.lang.Integer getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  版本
	 */
	public void setVersion(java.lang.Integer version){
		this.version = version;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  合同
	 */
	@Column(name ="CONTRACT_ID",nullable=false,length=36)
	public java.lang.String getContractId(){
		return this.contractId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  合同
	 */
	public void setContractId(java.lang.String contractId){
		this.contractId = contractId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户
	 */
	@Column(name ="USER_ID",nullable=false,length=36)
	public java.lang.String getUserId(){
		return this.userId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户
	 */
	public void setUserId(java.lang.String userId){
		this.userId = userId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  催收人
	 */
	@Column(name ="OPERATOR_ID",nullable=true,length=36)
	public java.lang.String getOperatorId(){
		return this.operatorId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  催收人
	 */
	public void setOperatorId(java.lang.String operatorId){
		this.operatorId = operatorId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  催收时间
	 */
	@Column(name ="COLLECTION_DATE",nullable=true,length=20)
	public java.util.Date getCollectionDate(){
		return this.collectionDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  催收时间
	 */
	public void setCollectionDate(java.util.Date collectionDate){
		this.collectionDate = collectionDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  催收方式
	 */
	@Column(name ="COLLECTION_WAY",nullable=true,length=2)
	public java.lang.String getCollectionWay(){
		return this.collectionWay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  催收方式
	 */
	public void setCollectionWay(java.lang.String collectionWay){
		this.collectionWay = collectionWay;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=500)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
}
