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
 * @Description: 风控检查结果
 * @author onlineGenerator
 * @date 2017-04-02 09:03:22
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_risk_check_result", schema = "")
@SuppressWarnings("serial")
public class ZmlRiskCheckResultEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**申请*/
	@Excel(name="申请")
	private java.lang.String applyId;
	/**用户*/
	@Excel(name="用户")
	private java.lang.String userId;
	/**模型*/
	@Excel(name="模型")
	private java.lang.String modeId;
	/**结果*/
	@Excel(name="结果")
	private java.math.BigDecimal result;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String remarkes;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  申请
	 */
	@Column(name ="APPLY_ID",nullable=true,length=36)
	public java.lang.String getApplyId(){
		return this.applyId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  申请
	 */
	public void setApplyId(java.lang.String applyId){
		this.applyId = applyId;
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
	 *@return: java.lang.String  模型
	 */
	@Column(name ="MODE_ID",nullable=true,length=36)
	public java.lang.String getModeId(){
		return this.modeId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  模型
	 */
	public void setModeId(java.lang.String modeId){
		this.modeId = modeId;
	}
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  结果
	 */
	@Column(name ="RESULT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getResult(){
		return this.result;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  结果
	 */
	public void setResult(java.math.BigDecimal result){
		this.result = result;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARKES",nullable=true,length=500)
	public java.lang.String getRemarkes(){
		return this.remarkes;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarkes(java.lang.String remarkes){
		this.remarkes = remarkes;
	}
}
