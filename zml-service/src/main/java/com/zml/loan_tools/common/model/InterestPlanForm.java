package com.zml.loan_tools.common.model;

import java.math.BigDecimal;
import java.util.Date;

public class InterestPlanForm {

	private Date planTime; //计划还款时间

	private BigDecimal planPpal;//计划还款金额

	private BigDecimal loanAmount;

	private Date loanStartDate;

	private Date loanEndDate;
	
	private Integer id; //标识

	/**
	 * @return the planTime
	 */
	public Date getPlanTime() {
		return planTime;
	}

	/**
	 * @param planTime
	 *            the planTime to set
	 */
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	/**
	 * @return the planPpal
	 */
	public BigDecimal getPlanPpal() {
		return planPpal;
	}

	/**
	 * @param planPpal
	 *            the planPpal to set
	 */
	public void setPlanPpal(BigDecimal planPpal) {
		this.planPpal = planPpal;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
