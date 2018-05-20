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
 * @Description: 用户信息记录
 * @author onlineGenerator
 * @date 2017-03-30 16:54:07
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_user_record", schema = "")
@SuppressWarnings("serial")
public class ZmlUserRecordEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新日期*/
	private java.util.Date updateDate;
	/**版本*/
	private java.lang.String version;
	/**申请*/
	@Excel(name="申请")
	private java.lang.String applyId;
	/**用户*/
	private java.lang.String userId;
	/**真实姓名*/
	@Excel(name="真实姓名")
	private java.lang.String realName;
	/**用户类型*/
	@Excel(name="用户类型")
	private java.lang.String type;
	/**电话*/
	@Excel(name="电话")
	private java.lang.String phone;
	/**身份证号码*/
	@Excel(name="身份证号码")
	private java.lang.String identificationNumber;
	/**生日*/
	@Excel(name="生日",format = "yyyy-MM-dd")
	private java.util.Date birthDay;
	/**年龄*/
	@Excel(name="年龄")
	private java.lang.Integer age;
	/**性别*/
	@Excel(name="性别")
	private java.lang.String sex;
	/**户口所在地*/
	@Excel(name="户口所在地")
	private java.lang.String accountLocation;
	/**发证机关*/
	@Excel(name="发证机关")
	private java.lang.String issuingAuthority;
	/**有效期起*/
	@Excel(name="有效期起",format = "yyyy-MM-dd")
	private java.util.Date validStart;
	/**有效期至*/
	@Excel(name="有效期至",format = "yyyy-MM-dd")
	private java.util.Date validEnd;
	/**是否实名认证*/
	@Excel(name="是否实名认证")
	private java.lang.String isVerified;
	/**实名认证方式*/
	@Excel(name="实名认证方式")
	private java.lang.String verifiedMode;
	/**昵称*/
	@Excel(name="昵称")
	private java.lang.String wxNickname;
	/**性别*/
	@Excel(name="性别")
	private java.lang.String wxSex;
	/**国家*/
	@Excel(name="国家")
	private java.lang.String wxCountry;
	/**省份*/
	@Excel(name="省份")
	private java.lang.String wxProvince;
	/**城市*/
	@Excel(name="城市")
	private java.lang.String wxCity;
	/**上次修改时间*/
	@Excel(name="上次修改时间")
	private java.lang.String wxSubscribeTime;
	/**列表*/
	@Excel(name="列表")
	private java.lang.String wxTagidList;
	/**订阅*/
	@Excel(name="订阅")
	private java.lang.String wxSubscribe;
	/**语言*/
	@Excel(name="语言")
	private java.lang.String wxLanguage;
	/**OpenId*/
	@Excel(name="OpenId")
	private java.lang.String wxOpenid;
	/**组ID*/
	@Excel(name="组ID")
	private java.lang.String wxGroupid;
	/**头像*/
	@Excel(name="头像")
	private java.lang.String wxHeadimgurl;
	/**备注*/
	@Excel(name="备注")
	private java.lang.String wxRemark;
	/**账号*/
	@Excel(name="账号")
	private java.lang.String accountNumber;
	
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
	 *@return: java.lang.String  版本
	 */
	@Column(name ="VERSION",nullable=true,length=10)
	public java.lang.String getVersion(){
		return this.version;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本
	 */
	public void setVersion(java.lang.String version){
		this.version = version;
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
	 *@return: java.lang.String  真实姓名
	 */
	@Column(name ="REAL_NAME",nullable=true,length=50)
	public java.lang.String getRealName(){
		return this.realName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  真实姓名
	 */
	public void setRealName(java.lang.String realName){
		this.realName = realName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  用户类型
	 */
	@Column(name ="TYPE",nullable=true,length=1)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  用户类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
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
	 *@return: java.lang.String  身份证号码
	 */
	@Column(name ="IDENTIFICATION_NUMBER",nullable=true,length=20)
	public java.lang.String getIdentificationNumber(){
		return this.identificationNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  身份证号码
	 */
	public void setIdentificationNumber(java.lang.String identificationNumber){
		this.identificationNumber = identificationNumber;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  生日
	 */
	@Column(name ="BIRTH_DAY",nullable=true,length=32)
	public java.util.Date getBirthDay(){
		return this.birthDay;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  生日
	 */
	public void setBirthDay(java.util.Date birthDay){
		this.birthDay = birthDay;
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
	 *@return: java.lang.String  户口所在地
	 */
	@Column(name ="ACCOUNT_LOCATION",nullable=true,length=200)
	public java.lang.String getAccountLocation(){
		return this.accountLocation;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  户口所在地
	 */
	public void setAccountLocation(java.lang.String accountLocation){
		this.accountLocation = accountLocation;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发证机关
	 */
	@Column(name ="ISSUING_AUTHORITY",nullable=true,length=200)
	public java.lang.String getIssuingAuthority(){
		return this.issuingAuthority;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发证机关
	 */
	public void setIssuingAuthority(java.lang.String issuingAuthority){
		this.issuingAuthority = issuingAuthority;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效期起
	 */
	@Column(name ="VALID_START",nullable=true,length=32)
	public java.util.Date getValidStart(){
		return this.validStart;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效期起
	 */
	public void setValidStart(java.util.Date validStart){
		this.validStart = validStart;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  有效期至
	 */
	@Column(name ="VALID_END",nullable=true,length=32)
	public java.util.Date getValidEnd(){
		return this.validEnd;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  有效期至
	 */
	public void setValidEnd(java.util.Date validEnd){
		this.validEnd = validEnd;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否实名认证
	 */
	@Column(name ="IS_VERIFIED",nullable=true,length=1)
	public java.lang.String getIsVerified(){
		return this.isVerified;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否实名认证
	 */
	public void setIsVerified(java.lang.String isVerified){
		this.isVerified = isVerified;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  实名认证方式
	 */
	@Column(name ="VERIFIED_MODE",nullable=true,length=2)
	public java.lang.String getVerifiedMode(){
		return this.verifiedMode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  实名认证方式
	 */
	public void setVerifiedMode(java.lang.String verifiedMode){
		this.verifiedMode = verifiedMode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  昵称
	 */
	@Column(name ="WX_NICKNAME",nullable=true,length=32)
	public java.lang.String getWxNickname(){
		return this.wxNickname;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  昵称
	 */
	public void setWxNickname(java.lang.String wxNickname){
		this.wxNickname = wxNickname;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  性别
	 */
	@Column(name ="WX_SEX",nullable=true,length=1)
	public java.lang.String getWxSex(){
		return this.wxSex;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  性别
	 */
	public void setWxSex(java.lang.String wxSex){
		this.wxSex = wxSex;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  国家
	 */
	@Column(name ="WX_COUNTRY",nullable=true,length=100)
	public java.lang.String getWxCountry(){
		return this.wxCountry;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  国家
	 */
	public void setWxCountry(java.lang.String wxCountry){
		this.wxCountry = wxCountry;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  省份
	 */
	@Column(name ="WX_PROVINCE",nullable=true,length=50)
	public java.lang.String getWxProvince(){
		return this.wxProvince;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  省份
	 */
	public void setWxProvince(java.lang.String wxProvince){
		this.wxProvince = wxProvince;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  城市
	 */
	@Column(name ="WX_CITY",nullable=true,length=50)
	public java.lang.String getWxCity(){
		return this.wxCity;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  城市
	 */
	public void setWxCity(java.lang.String wxCity){
		this.wxCity = wxCity;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  上次修改时间
	 */
	@Column(name ="WX_SUBSCRIBE_TIME",nullable=true,length=20)
	public java.lang.String getWxSubscribeTime(){
		return this.wxSubscribeTime;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  上次修改时间
	 */
	public void setWxSubscribeTime(java.lang.String wxSubscribeTime){
		this.wxSubscribeTime = wxSubscribeTime;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  列表
	 */
	@Column(name ="WX_TAGID_LIST",nullable=true,length=1000)
	public java.lang.String getWxTagidList(){
		return this.wxTagidList;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  列表
	 */
	public void setWxTagidList(java.lang.String wxTagidList){
		this.wxTagidList = wxTagidList;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订阅
	 */
	@Column(name ="WX_SUBSCRIBE",nullable=true,length=500)
	public java.lang.String getWxSubscribe(){
		return this.wxSubscribe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订阅
	 */
	public void setWxSubscribe(java.lang.String wxSubscribe){
		this.wxSubscribe = wxSubscribe;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  语言
	 */
	@Column(name ="WX_LANGUAGE",nullable=true,length=50)
	public java.lang.String getWxLanguage(){
		return this.wxLanguage;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  语言
	 */
	public void setWxLanguage(java.lang.String wxLanguage){
		this.wxLanguage = wxLanguage;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  OpenId
	 */
	@Column(name ="WX_OPENID",nullable=true,length=100)
	public java.lang.String getWxOpenid(){
		return this.wxOpenid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  OpenId
	 */
	public void setWxOpenid(java.lang.String wxOpenid){
		this.wxOpenid = wxOpenid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  组ID
	 */
	@Column(name ="WX_GROUPID",nullable=true,length=100)
	public java.lang.String getWxGroupid(){
		return this.wxGroupid;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  组ID
	 */
	public void setWxGroupid(java.lang.String wxGroupid){
		this.wxGroupid = wxGroupid;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  头像
	 */
	@Column(name ="WX_HEADIMGURL",nullable=true,length=200)
	public java.lang.String getWxHeadimgurl(){
		return this.wxHeadimgurl;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  头像
	 */
	public void setWxHeadimgurl(java.lang.String wxHeadimgurl){
		this.wxHeadimgurl = wxHeadimgurl;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="WX_REMARK",nullable=true,length=500)
	public java.lang.String getWxRemark(){
		return this.wxRemark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setWxRemark(java.lang.String wxRemark){
		this.wxRemark = wxRemark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  账号
	 */
	@Column(name ="ACCOUNT_NUMBER",nullable=true,length=50)
	public java.lang.String getAccountNumber(){
		return this.accountNumber;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  账号
	 */
	public void setAccountNumber(java.lang.String accountNumber){
		this.accountNumber = accountNumber;
	}
}
