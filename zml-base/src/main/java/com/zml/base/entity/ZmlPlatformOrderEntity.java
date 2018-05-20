package com.zml.base.entity;
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
 * @Description: 平台订单
 * @author onlineGenerator
 * @date 2017-01-01 13:58:39
 * @version V1.0   
 *
 */
@Entity
@Table(name = "zml_platform_order", schema = "")
@SuppressWarnings("serial")
public class ZmlPlatformOrderEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**创建人名称*/
	private java.lang.String createName;
	/**创建人登录名称*/
	private java.lang.String createBy;
	/**创建日期*/
	private java.util.Date createDate;
	/**更新人名称*/
	private java.lang.String updateName;
	/**更新人登录名称*/
	private java.lang.String updateBy;
	/**更新日期*/
	private java.util.Date updateDate;
	/**所属部门*/
	private java.lang.String sysOrgCode;
	/**所属公司*/
	private java.lang.String sysCompanyCode;
	/**版本*/
	private java.lang.Integer version;
	/**商家*/
    @Excel(name="商家")
	private java.lang.String sellerId;
	/**订单编号*/
    @Excel(name="订单编号")
	private java.lang.String porderNum;
	/**订单金额*/
    @Excel(name="订单金额")
	private java.math.BigDecimal allAmount;
	/**支付方式*/
    @Excel(name="支付方式")
	private java.lang.String payWay;
	/**付款凭证*/
    @Excel(name="付款凭证")
	private java.lang.String payVoucher;
	/**发票类型*/
    @Excel(name="发票类型")
	private java.lang.String invoiceType;
	/**发票抬头*/
    @Excel(name="发票抬头")
	private java.lang.String invoiceTitle;
	/**发票内容*/
    @Excel(name="发票内容")
	private java.lang.String invoiceContent;
	/**订单状态*/
    @Excel(name="订单状态")
	private java.lang.String status;
	/**是否退货*/
    @Excel(name="是否退货")
	private java.lang.String isReturns;
	/**退货原因*/
    @Excel(name="退货原因")
	private java.lang.String returnReason;
	/**下单时间*/
    @Excel(name="下单时间",format = "yyyy-MM-dd")
	private java.util.Date orderTime;
	/**付款时间*/
    @Excel(name="付款时间",format = "yyyy-MM-dd")
	private java.util.Date payTime;
	/**备注*/
    @Excel(name="备注")
	private java.lang.String remarks;
	/**收货地址*/
    @Excel(name="收货地址")
	private java.lang.String address;
	/**物流公司*/
    @Excel(name="物流公司")
	private java.lang.String logisticsCompany;
	/**物流单号*/
    @Excel(name="物流单号")
	private java.lang.String logisticsNum;
	/**物流费*/
    @Excel(name="物流费")
	private java.math.BigDecimal logisticsFee;
	/**物流联系人*/
    @Excel(name="物流联系人")
	private java.lang.String logisticsLinkPerson;
	/**物流联系人电话*/
    @Excel(name="物流联系人电话")
	private java.lang.String logisticsLinkPhnoe;
	
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人名称
	 */
	
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人名称
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人登录名称
	 */
	
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人登录名称
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
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
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人名称
	 */
	
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人名称
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新人登录名称
	 */
	
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新人登录名称
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
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
	 *@return: java.lang.String  所属部门
	 */
	
	@Column(name ="SYS_ORG_CODE",nullable=true,length=50)
	public java.lang.String getSysOrgCode(){
		return this.sysOrgCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属部门
	 */
	public void setSysOrgCode(java.lang.String sysOrgCode){
		this.sysOrgCode = sysOrgCode;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  所属公司
	 */
	
	@Column(name ="SYS_COMPANY_CODE",nullable=true,length=50)
	public java.lang.String getSysCompanyCode(){
		return this.sysCompanyCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  所属公司
	 */
	public void setSysCompanyCode(java.lang.String sysCompanyCode){
		this.sysCompanyCode = sysCompanyCode;
	}
	
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  版本
	 */
	
	@Column(name ="VERSION",nullable=true,length=1)
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
	 *@return: java.lang.String  商家
	 */
	
	@Column(name ="SELLER_ID",nullable=true,length=36)
	public java.lang.String getSellerId(){
		return this.sellerId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  商家
	 */
	public void setSellerId(java.lang.String sellerId){
		this.sellerId = sellerId;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单编号
	 */
	
	@Column(name ="PORDER_NUM",nullable=true,length=30)
	public java.lang.String getPorderNum(){
		return this.porderNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单编号
	 */
	public void setPorderNum(java.lang.String porderNum){
		this.porderNum = porderNum;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  订单金额
	 */
	
	@Column(name ="ALL_AMOUNT",nullable=true,scale=2,length=14)
	public java.math.BigDecimal getAllAmount(){
		return this.allAmount;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  订单金额
	 */
	public void setAllAmount(java.math.BigDecimal allAmount){
		this.allAmount = allAmount;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  支付方式
	 */
	
	@Column(name ="PAY_WAY",nullable=true,length=1)
	public java.lang.String getPayWay(){
		return this.payWay;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  支付方式
	 */
	public void setPayWay(java.lang.String payWay){
		this.payWay = payWay;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  付款凭证
	 */
	
	@Column(name ="PAY_VOUCHER",nullable=true,length=200)
	public java.lang.String getPayVoucher(){
		return this.payVoucher;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  付款凭证
	 */
	public void setPayVoucher(java.lang.String payVoucher){
		this.payVoucher = payVoucher;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票类型
	 */
	
	@Column(name ="INVOICE_TYPE",nullable=true,length=1)
	public java.lang.String getInvoiceType(){
		return this.invoiceType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票类型
	 */
	public void setInvoiceType(java.lang.String invoiceType){
		this.invoiceType = invoiceType;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票抬头
	 */
	
	@Column(name ="INVOICE_TITLE",nullable=true,length=1)
	public java.lang.String getInvoiceTitle(){
		return this.invoiceTitle;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票抬头
	 */
	public void setInvoiceTitle(java.lang.String invoiceTitle){
		this.invoiceTitle = invoiceTitle;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  发票内容
	 */
	
	@Column(name ="INVOICE_CONTENT",nullable=true,length=1)
	public java.lang.String getInvoiceContent(){
		return this.invoiceContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  发票内容
	 */
	public void setInvoiceContent(java.lang.String invoiceContent){
		this.invoiceContent = invoiceContent;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  订单状态
	 */
	
	@Column(name ="STATUS",nullable=true,length=2)
	public java.lang.String getStatus(){
		return this.status;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  订单状态
	 */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否退货
	 */
	
	@Column(name ="IS_RETURNS",nullable=true,length=1)
	public java.lang.String getIsReturns(){
		return this.isReturns;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否退货
	 */
	public void setIsReturns(java.lang.String isReturns){
		this.isReturns = isReturns;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  退货原因
	 */
	
	@Column(name ="RETURN_REASON",nullable=true,length=200)
	public java.lang.String getReturnReason(){
		return this.returnReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  退货原因
	 */
	public void setReturnReason(java.lang.String returnReason){
		this.returnReason = returnReason;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  下单时间
	 */
	
	@Column(name ="ORDER_TIME",nullable=true,length=20)
	public java.util.Date getOrderTime(){
		return this.orderTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  下单时间
	 */
	public void setOrderTime(java.util.Date orderTime){
		this.orderTime = orderTime;
	}
	
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  付款时间
	 */
	
	@Column(name ="PAY_TIME",nullable=true,length=20)
	public java.util.Date getPayTime(){
		return this.payTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  付款时间
	 */
	public void setPayTime(java.util.Date payTime){
		this.payTime = payTime;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	
	@Column(name ="REMARKS",nullable=true,length=200)
	public java.lang.String getRemarks(){
		return this.remarks;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemarks(java.lang.String remarks){
		this.remarks = remarks;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  收货地址
	 */
	
	@Column(name ="ADDRESS",nullable=true,length=200)
	public java.lang.String getAddress(){
		return this.address;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  收货地址
	 */
	public void setAddress(java.lang.String address){
		this.address = address;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流公司
	 */
	
	@Column(name ="LOGISTICS_COMPANY",nullable=true,length=200)
	public java.lang.String getLogisticsCompany(){
		return this.logisticsCompany;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流公司
	 */
	public void setLogisticsCompany(java.lang.String logisticsCompany){
		this.logisticsCompany = logisticsCompany;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流单号
	 */
	
	@Column(name ="LOGISTICS_NUM",nullable=true,length=50)
	public java.lang.String getLogisticsNum(){
		return this.logisticsNum;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流单号
	 */
	public void setLogisticsNum(java.lang.String logisticsNum){
		this.logisticsNum = logisticsNum;
	}
	
	/**
	 *方法: 取得java.math.BigDecimal
	 *@return: java.math.BigDecimal  物流费
	 */
	
	@Column(name ="LOGISTICS_FEE",nullable=true,scale=2,length=8)
	public java.math.BigDecimal getLogisticsFee(){
		return this.logisticsFee;
	}

	/**
	 *方法: 设置java.math.BigDecimal
	 *@param: java.math.BigDecimal  物流费
	 */
	public void setLogisticsFee(java.math.BigDecimal logisticsFee){
		this.logisticsFee = logisticsFee;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流联系人
	 */
	
	@Column(name ="LOGISTICS_LINK_PERSON",nullable=true,length=50)
	public java.lang.String getLogisticsLinkPerson(){
		return this.logisticsLinkPerson;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流联系人
	 */
	public void setLogisticsLinkPerson(java.lang.String logisticsLinkPerson){
		this.logisticsLinkPerson = logisticsLinkPerson;
	}
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  物流联系人电话
	 */
	
	@Column(name ="LOGISTICS_LINK_PHNOE",nullable=true,length=11)
	public java.lang.String getLogisticsLinkPhnoe(){
		return this.logisticsLinkPhnoe;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  物流联系人电话
	 */
	public void setLogisticsLinkPhnoe(java.lang.String logisticsLinkPhnoe){
		this.logisticsLinkPhnoe = logisticsLinkPhnoe;
	}
	
}
