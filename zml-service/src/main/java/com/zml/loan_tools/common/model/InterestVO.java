package com.zml.loan_tools.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.zml.loan_tools.common.utils.MoneyUtil;


public class InterestVO implements Serializable {
	private Integer repaymentNumber; // 还款期数

	private String repaymentDate;// 页面显示还款日期

	private String ppalInterest; // 页面显示本息

	private String ppal; // 页面显示本金

	private String interest; // 页面显示利息

	private String ppalCnt; // 页面显示本金累计

	private String interestCnt; // 页面显示利息累计

	private String loanBalance; // 页面显示贷款余额

	private Date startDate; // 开始时间

	private Date endDate; // 结束时间

	private BigDecimal currentPricipalInterest;// 本息

	private BigDecimal currentPricipal;// 本金

	private BigDecimal currentInterest;// 利息

	private BigDecimal endCurrentPricipal;// 截止当期累计本金

	private BigDecimal endCurrentInterest;// 截止当期累计利息

	private BigDecimal endCurrentPrincipalBalance;// 截止当期本金余额
	
	private String endDateStr; // 结束时间

	public InterestVO() {
	}

	public InterestVO(int repaymentNumber, String repaymentDate, BigDecimal ppalInterest, BigDecimal ppal,
			BigDecimal interest, BigDecimal ppalCnt, BigDecimal interestCnt, BigDecimal loanBalance, Date startDate,
			Date endDate) {
		this.repaymentNumber = repaymentNumber;
		this.repaymentDate = repaymentDate;
		this.ppalInterest = MoneyUtil.formatMoney(ppalInterest.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.ppal = MoneyUtil.formatMoney(ppal.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.ppalCnt = MoneyUtil.formatMoney(ppalCnt.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.interest = MoneyUtil.formatMoney(interest.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.interestCnt = MoneyUtil.formatMoney(interestCnt.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.loanBalance = MoneyUtil.formatMoney(loanBalance.setScale(2, BigDecimal.ROUND_HALF_UP));
		this.currentPricipalInterest = ppalInterest.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.currentPricipal = ppal.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.currentInterest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.endCurrentInterest = interestCnt.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.endCurrentPricipal = ppalCnt.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.endCurrentPrincipalBalance = loanBalance.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * @return the repaymentNumber
	 */
	public Integer getRepaymentNumber() {
		return repaymentNumber;
	}

	/**
	 * @param repaymentNumber
	 *            the repaymentNumber to set
	 */
	public void setRepaymentNumber(Integer repaymentNumber) {
		this.repaymentNumber = repaymentNumber;
	}

	/**
	 * @return the ppalInterest
	 */
	public String getPpalInterest() {
		return ppalInterest;
	}

	/**
	 * @param ppalInterest
	 *            the ppalInterest to set
	 */
	public void setPpalInterest(String ppalInterest) {
		this.ppalInterest = ppalInterest;
	}

	/**
	 * @return the ppal
	 */
	public String getPpal() {
		return ppal;
	}

	/**
	 * @param ppal
	 *            the ppal to set
	 */
	public void setPpal(String ppal) {
		this.ppal = ppal;
	}

	/**
	 * @return the interest
	 */
	public String getInterest() {
		return interest;
	}

	/**
	 * @param interest
	 *            the interest to set
	 */
	public void setInterest(String interest) {
		this.interest = interest;
	}

	/**
	 * @return the ppalCnt
	 */
	public String getPpalCnt() {
		return ppalCnt;
	}

	/**
	 * @param ppalCnt
	 *            the ppalCnt to set
	 */
	public void setPpalCnt(String ppalCnt) {
		this.ppalCnt = ppalCnt;
	}

	/**
	 * @return the interestCnt
	 */
	public String getInterestCnt() {
		return interestCnt;
	}

	/**
	 * @param interestCnt
	 *            the interestCnt to set
	 */
	public void setInterestCnt(String interestCnt) {
		this.interestCnt = interestCnt;
	}

	/**
	 * @return the loanBalance
	 */
	public String getLoanBalance() {
		return loanBalance;
	}

	/**
	 * @param loanBalance
	 *            the loanBalance to set
	 */
	public void setLoanBalance(String loanBalance) {
		this.loanBalance = loanBalance;
	}

	/**
	 * @return the repaymentDate
	 */
	public String getRepaymentDate() {
		return repaymentDate;
	}

	/**
	 * @param repaymentDate
	 *            the repaymentDate to set
	 */
	public void setRepaymentDate(String repaymentDate) {
		this.repaymentDate = repaymentDate;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *            the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *            the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the currentPricipalInterest
	 */
	public BigDecimal getCurrentPricipalInterest() {
		return currentPricipalInterest;
	}

	/**
	 * @param currentPricipalInterest
	 *            the currentPricipalInterest to set
	 */
	public void setCurrentPricipalInterest(BigDecimal currentPricipalInterest) {
		this.currentPricipalInterest = currentPricipalInterest;
	}

	/**
	 * @return the currentPricipal
	 */
	public BigDecimal getCurrentPricipal() {
		return currentPricipal;
	}

	/**
	 * @param currentPricipal
	 *            the currentPricipal to set
	 */
	public void setCurrentPricipal(BigDecimal currentPricipal) {
		this.currentPricipal = currentPricipal;
	}

	/**
	 * @return the currentInterest
	 */
	public BigDecimal getCurrentInterest() {
		return currentInterest;
	}

	/**
	 * @param currentInterest
	 *            the currentInterest to set
	 */
	public void setCurrentInterest(BigDecimal currentInterest) {
		this.currentInterest = currentInterest;
	}

	/**
	 * @return the endCurrentPricipal
	 */
	public BigDecimal getEndCurrentPricipal() {
		return endCurrentPricipal;
	}

	/**
	 * @param endCurrentPricipal
	 *            the endCurrentPricipal to set
	 */
	public void setEndCurrentPricipal(BigDecimal endCurrentPricipal) {
		this.endCurrentPricipal = endCurrentPricipal;
	}

	/**
	 * @return the endCurrentInterest
	 */
	public BigDecimal getEndCurrentInterest() {
		return endCurrentInterest;
	}

	/**
	 * @param endCurrentInterest
	 *            the endCurrentInterest to set
	 */
	public void setEndCurrentInterest(BigDecimal endCurrentInterest) {
		this.endCurrentInterest = endCurrentInterest;
	}

	/**
	 * @return the endCurrentPrincipalBalance
	 */
	public BigDecimal getEndCurrentPrincipalBalance() {
		return endCurrentPrincipalBalance;
	}

	/**
	 * @param endCurrentPrincipalBalance
	 *            the endCurrentPrincipalBalance to set
	 */
	public void setEndCurrentPrincipalBalance(BigDecimal endCurrentPrincipalBalance) {
		this.endCurrentPrincipalBalance = endCurrentPrincipalBalance;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
}
