package com.zml.loan_tools.common.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class InterestCalCulateForm {
private Integer partyId;//参与人ID
	
	private Integer contractId;//合同ID
	
	private Integer projectId;//业务ID
	
	private Integer payLoanId;//放款ID
	
	private String operatorUser;//操作者
	
	private BigDecimal overdueRate;//逾期利率
	
	private String operatorMechanism;//操作机构

	private BigDecimal loanAmount; // 贷款金额

	private Date loanStartDate; // 贷款开始时间

	private Date loanEndDate;// 贷款结束时间

	private Integer repaymentDate; // 还款日期

	private String repayment; // 还款方式

	private BigDecimal rate; // 年利率
	
	private Integer repaymentNumber; //还款周期月数

	private List<InterestPlanForm> planList; // 计划还款
	
	private BigDecimal chargeAmt = BigDecimal.ZERO; // 放款手续费
	
	private boolean flag = true;// 是否调用账物处理
	
	private String transactionNo;//交易编号
	
	private Integer applyTerm; // 期限
	
	private String applyTermUnit;// 期限单位
	
	private int [] each_days; // 修改等额本息,时用到
	
	private BigDecimal periodCnt; // 期次
	
	private String sourceType; //资金来源

	/**
	 * @return the partyId
	 */
	public Integer getPartyId() {
		return partyId;
	}

	/**
	 * @param partyId the partyId to set
	 */
	public void setPartyId(Integer partyId) {
		this.partyId = partyId;
	}

	/**
	 * @return the contractId
	 */
	public Integer getContractId() {
		return contractId;
	}

	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	/**
	 * @return the projectId
	 */
	public Integer getProjectId() {
		return projectId;
	}

	/**
	 * @param projectId the projectId to set
	 */
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the payLoanId
	 */
	public Integer getPayLoanId() {
		return payLoanId;
	}

	/**
	 * @param payLoanId the payLoanId to set
	 */
	public void setPayLoanId(Integer payLoanId) {
		this.payLoanId = payLoanId;
	}

	/**
	 * @return the operatorUser
	 */
	public String getOperatorUser() {
		return operatorUser;
	}

	/**
	 * @param operatorUser the operatorUser to set
	 */
	public void setOperatorUser(String operatorUser) {
		this.operatorUser = operatorUser;
	}

	/**
	 * @return the operatorMechanism
	 */
	public String getOperatorMechanism() {
		return operatorMechanism;
	}

	/**
	 * @param operatorMechanism the operatorMechanism to set
	 */
	public void setOperatorMechanism(String operatorMechanism) {
		this.operatorMechanism = operatorMechanism;
	}

	/**
	 * @return the loanAmount
	 */
	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	/**
	 * @param loanAmount
	 *            the loanAmount to set
	 */
	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	/**
	 * @return the repayment
	 */
	public String getRepayment() {
		return repayment;
	}

	/**
	 * @param repayment
	 *            the repayment to set
	 */
	public void setRepayment(String repayment) {
		this.repayment = repayment;
	}

	/**
	 * @return the rate
	 */
	public BigDecimal getRate() {
		return rate;
	}

	/**
	 * @param rate
	 *            the rate to set
	 */
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	/**
	 * @return the planList
	 */
	public List<InterestPlanForm> getPlanList() {
		return planList;
	}

	/**
	 * @param planList
	 *            the planList to set
	 */
	public void setPlanList(List<InterestPlanForm> planList) {
		this.planList = planList;
	}

	/**
	 * @return the loanStartDate
	 */
	public Date getLoanStartDate() {
		return loanStartDate;
	}

	/**
	 * @param loanStartDate
	 *            the loanStartDate to set
	 */
	public void setLoanStartDate(Date loanStartDate) {
		this.loanStartDate = loanStartDate;
	}

	/**
	 * @return the loanEndDate
	 */
	public Date getLoanEndDate() {
		return loanEndDate;
	}

	/**
	 * @param loanEndDate
	 *            the loanEndDate to set
	 */
	public void setLoanEndDate(Date loanEndDate) {
		this.loanEndDate = loanEndDate;
	}

	/**
	 * @return the repaymentDate
	 */
	public Integer getRepaymentDate() {
		return repaymentDate;
	}

	/**
	 * @param repaymentDate
	 *            the repaymentDate to set
	 */
	public void setRepaymentDate(Integer repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	/**
	 * @return the repaymentNumber
	 */
	public Integer getRepaymentNumber() {
		return repaymentNumber;
	}

	/**
	 * @param repaymentNumber the repaymentNumber to set
	 */
	public void setRepaymentNumber(Integer repaymentNumber) {
		this.repaymentNumber = repaymentNumber;
	}

	/**
	 * @return the overdueRate
	 */
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}

	/**
	 * @param overdueRate the overdueRate to set
	 */
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}

	public BigDecimal getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(BigDecimal chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Integer getApplyTerm() {
		return applyTerm;
	}

	public void setApplyTerm(Integer applyTerm) {
		this.applyTerm = applyTerm;
	}

	public String getApplyTermUnit() {
		return applyTermUnit;
	}

	public void setApplyTermUnit(String applyTermUnit) {
		this.applyTermUnit = applyTermUnit;
	}

	public int[] getEach_days() {
		return each_days;
	}

	public void setEach_days(int[] each_days) {
		this.each_days = each_days;
	}

	public BigDecimal getPeriodCnt() {
		return periodCnt;
	}

	public void setPeriodCnt(BigDecimal periodCnt) {
		this.periodCnt = periodCnt;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

}
