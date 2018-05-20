package com.zml.loan_tools.common.utils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.zml.loan_tools.common.model.InterestCalCulateForm;
import com.zml.loan_tools.common.model.InterestVO;
import com.zml.loan_tools.service.InterestCalCulateService;
import com.zml.loan_tools.service.impl.InterestCalCulateServiceImpl;

public class CalcUtil {
	public static BigDecimal loanAmt = new BigDecimal("1000");//贷款金额
	public static Integer applyTerm = new Integer(6);//期限
	public static String termUnit = "2";//期限单位
	public static Integer repaymentNumber = 1;//还款周期月数
	public static String repayment = "1";//还款方式
	public static Integer repayDate = 21;//还款日期
	public static Date applyDate = DateTools.stringToDate("2017-03-01", DateTools.FORMAT_YYYY_MM_DD);//贷款开始时间
	public static BigDecimal yearRate = new BigDecimal("0.15");
	
	public static void main(String[] args) {	 
		
		InterestCalCulateForm interestForm  = new InterestCalCulateForm();
		interestForm.setLoanAmount(loanAmt); // 贷款金额
		interestForm.setApplyTerm(applyTerm); // 期限
		interestForm.setApplyTermUnit(termUnit); // 期限单位
		interestForm.setRepaymentNumber(repaymentNumber); // 还款周期月数
		interestForm.setRepayment(repayment); // // 还款方式
		interestForm.setRepaymentDate(repayDate); // 还款日期
		interestForm.setLoanStartDate(applyDate); // 贷款开始时间
		interestForm.setRate(yearRate);
		try {
			calcRepayPlan(interestForm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<InterestVO> calcRepayPlan(InterestCalCulateForm interestForm) throws Exception{
		List<InterestVO> list =null;
		if (interestForm != null) {
			interestForm.setProjectId(null);
			Date endDate = DateTools.getEndingDate(interestForm.getLoanStartDate(),	interestForm.getApplyTermUnit(),
					interestForm.getApplyTerm());
			interestForm.setLoanEndDate(endDate);
			interestForm.setRate(interestForm.getRate().divide(new BigDecimal(100)));
			
			InterestCalCulateService ics = new InterestCalCulateServiceImpl();
			
			list = ics.calCulate(interestForm);
			//计算合计金额
			BigDecimal interTotal = new BigDecimal("0");//利息总额
			BigDecimal pricInterTotal = new BigDecimal("0");//本金利息总额
			if (list != null) {
				for (InterestVO rpp : list) {
					System.out.println("期数：" + rpp.getRepaymentNumber()
							+ "     计划还款日：" + rpp.getRepaymentDate()
							+ "   本期应还金额：" + rpp.getCurrentPricipalInterest()
							+ "    应还本金：" + rpp.getCurrentPricipal()
							+ "   应还利息：" + rpp.getCurrentInterest() + "累计本金="
							+ rpp.getEndCurrentPricipal() + "累计利息="
							+ rpp.getEndCurrentInterest() + "   贷款余额"
							+ rpp.getEndCurrentPrincipalBalance());
					interTotal = interTotal.add(rpp.getCurrentInterest());
					pricInterTotal = pricInterTotal.add(rpp.getCurrentPricipalInterest());
				}
				InterestVO totalIvo = new InterestVO();
				totalIvo.setCurrentPricipalInterest(pricInterTotal);
				totalIvo.setCurrentInterest(interTotal);
				list.add(totalIvo);
			}
		}
		return list;
	}
}
