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
 * @Description: 借款用户联系人
 * @author onlineGenerator
 * @date 2017-03-01 22:20:50
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_user_contacts", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanUserContactsEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.Integer version;
	/**借款主题*/
	@Excel(name="借款主题")
	private java.lang.String applId;
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**姓名*/
	@Excel(name="姓名")
	private java.lang.String name;
	/**电话*/
	@Excel(name="电话")
	private java.lang.String phone;
	/**职业*/
	@Excel(name="职业")
	private java.lang.String profession;
	/**工作*/
	@Excel(name="工作")
	private java.lang.String work;
	/**关系*/
	@Excel(name="关系")
	private java.lang.String relation;
	/**身份证号*/
	@Excel(name="身份证号")
	private java.lang.String idNumber;
	/**年龄*/
	@Excel(name="年龄")
	private java.lang.Integer age;
	/**性别*/
	@Excel(name="性别")
	private java.lang.String sex;
	/**住所*/
	@Excel(name="住所")
	private java.lang.String address;
	/**序号*/
	@Excel(name="序号")
	private java.lang.Integer seqNo;
	/**审批标识*/
	@Excel(name="审批标识")
	private java.lang.String approvalFlag;
	/**审批人*/
	@Excel(name="审批人")
	private java.lang.String approvalUserId;
	/**审批时间*/
	@Excel(name="审批时间",format = "yyyy-MM-dd")
	private java.util.Date approvalDate;
	/**审批意见*/
	@Excel(name="审批意见")
	private java.lang.String approvalOpinion;
	
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
	 *@return: java.lang.String  借款主题
	 */
	@Column(name ="APPL_ID",nullable=true,length=36)
	public java.lang.String getApplId(){
		return this.applId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  借款主题
	 */
	public void setApplId(java.lang.String applId){
		this.applId = applId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户
	 */
	@Column(name ="USER_ID",nullable=true,length=36)
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
	 *@return: java.lang.String  姓名
	 */
	@Column(name ="NAME",nullable=true,length=10)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  姓名
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  电话
	 */
	@Column(name ="PHONE",nullable=true,length=12)
	public java.lang.String getPhone(){
		return this.phone;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  电话
	 */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  职业
	 */
	@Column(name ="PROFESSION",nullable=true,length=2)
	public java.lang.String getProfession(){
		return this.profession;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  职业
	 */
	public void setProfession(java.lang.String profession){
		this.profession = profession;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  工作
	 */
	@Column(name ="WORK",nullable=true,length=50)
	public java.lang.String getWork(){
		return this.work;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  工作
	 */
	public void setWork(java.lang.String work){
		this.work = work;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  关系
	 */
	@Column(name ="RELATION",nullable=true,length=2)
	public java.lang.String getRelation(){
		return this.relation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  关系
	 */
	public void setRelation(java.lang.String relation){
		this.relation = relation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  身份证号
	 */
	@Column(name ="ID_NUMBER",nullable=true,length=20)
	public java.lang.String getIdNumber(){
		return this.idNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号
	 */
	public void setIdNumber(java.lang.String idNumber){
		this.idNumber = idNumber;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  年龄
	 */
	@Column(name ="AGE",nullable=true,length=3)
	public java.lang.Integer getAge(){
		return this.age;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  年龄
	 */
	public void setAge(java.lang.Integer age){
		this.age = age;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="SEX",nullable=true,length=1)
	public java.lang.String getSex(){
		return this.sex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  住所
	 */
	@Column(name ="ADDRESS",nullable=true,length=200)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  住所
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */
	@Column(name ="SEQ_NO",nullable=true,length=2)
	public java.lang.Integer getSeqNo(){
		return this.seqNo;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  序号
	 */
	public void setSeqNo(java.lang.Integer seqNo){
		this.seqNo = seqNo;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批标识
	 */
	@Column(name ="APPROVAL_FLAG",nullable=true,length=1)
	public java.lang.String getApprovalFlag(){
		return this.approvalFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批标识
	 */
	public void setApprovalFlag(java.lang.String approvalFlag){
		this.approvalFlag = approvalFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批人
	 */
	@Column(name ="APPROVAL_USER_ID",nullable=true,length=36)
	public java.lang.String getApprovalUserId(){
		return this.approvalUserId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批人
	 */
	public void setApprovalUserId(java.lang.String approvalUserId){
		this.approvalUserId = approvalUserId;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  审批时间
	 */
	@Column(name ="APPROVAL_DATE",nullable=true,length=32)
	public java.util.Date getApprovalDate(){
		return this.approvalDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  审批时间
	 */
	public void setApprovalDate(java.util.Date approvalDate){
		this.approvalDate = approvalDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  审批意见
	 */
	@Column(name ="APPROVAL_OPINION",nullable=true,length=500)
	public java.lang.String getApprovalOpinion(){
		return this.approvalOpinion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  审批意见
	 */
	public void setApprovalOpinion(java.lang.String approvalOpinion){
		this.approvalOpinion = approvalOpinion;
	}
}
