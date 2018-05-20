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
 * @Description: 放款文档
 * @author onlineGenerator
 * @date 2017-03-01 22:26:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_loan_pay_document", schema = "")
@SuppressWarnings("serial")
public class ZmlLoanPayDocumentEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.Integer version;
	/**放款*/
	private java.lang.String payId;
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**文档类型*/
	@Excel(name="文档类型")
	private java.lang.String docType;
	/**标识*/
	@Excel(name="标识")
	private java.lang.String fileFlag;
	/**图片路径*/
	@Excel(name="图片路径")
	private java.lang.String imagePath;
	/**文件路径*/
	@Excel(name="文件路径")
	private java.lang.String filePath;
	/**描述*/
	@Excel(name="描述")
	private java.lang.String details;
	/**序号*/
	@Excel(name="序号")
	private java.lang.Integer seqNo;
	/**档案编号*/
	@Excel(name="档案编号")
	private java.lang.String archivesNo;
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
	 *@return: java.lang.String  放款
	 */
	@Column(name ="PAY_ID",nullable=false,length=36)
	public java.lang.String getPayId(){
		return this.payId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  放款
	 */
	public void setPayId(java.lang.String payId){
		this.payId = payId;
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
	 *@return: java.lang.String  文档类型
	 */
	@Column(name ="DOC_TYPE",nullable=true,length=2)
	public java.lang.String getDocType(){
		return this.docType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文档类型
	 */
	public void setDocType(java.lang.String docType){
		this.docType = docType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  标识
	 */
	@Column(name ="FILE_FLAG",nullable=true,length=1)
	public java.lang.String getFileFlag(){
		return this.fileFlag;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  标识
	 */
	public void setFileFlag(java.lang.String fileFlag){
		this.fileFlag = fileFlag;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  图片路径
	 */
	@Column(name ="IMAGE_PATH",nullable=true,length=200)
	public java.lang.String getImagePath(){
		return this.imagePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  图片路径
	 */
	public void setImagePath(java.lang.String imagePath){
		this.imagePath = imagePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  文件路径
	 */
	@Column(name ="FILE_PATH",nullable=true,length=200)
	public java.lang.String getFilePath(){
		return this.filePath;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  文件路径
	 */
	public void setFilePath(java.lang.String filePath){
		this.filePath = filePath;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  描述
	 */
	@Column(name ="DETAILS",nullable=true,length=500)
	public java.lang.String getDetails(){
		return this.details;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  描述
	 */
	public void setDetails(java.lang.String details){
		this.details = details;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  序号
	 */
	@Column(name ="SEQ_NO",nullable=true,length=4)
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
	 *@return: java.lang.String  档案编号
	 */
	@Column(name ="ARCHIVES_NO",nullable=true,length=50)
	public java.lang.String getArchivesNo(){
		return this.archivesNo;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  档案编号
	 */
	public void setArchivesNo(java.lang.String archivesNo){
		this.archivesNo = archivesNo;
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
	@Column(name ="APPROVAL_DATE",nullable=true,length=20)
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
