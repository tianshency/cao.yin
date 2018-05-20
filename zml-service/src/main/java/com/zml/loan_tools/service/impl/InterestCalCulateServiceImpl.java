package com.zml.loan_tools.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.zml.loan_tools.common.GlobalConstants;
import com.zml.loan_tools.common.model.InterestCalCulateForm;
import com.zml.loan_tools.common.model.InterestPlanForm;
import com.zml.loan_tools.common.model.InterestVO;
import com.zml.loan_tools.common.utils.DateTools;
import com.zml.loan_tools.common.utils.NumberUtil;
import com.zml.loan_tools.service.InterestCalCulateService;


public class InterestCalCulateServiceImpl implements InterestCalCulateService {
	/** 本息 */
	private BigDecimal ppalInterest;
	/** 本金 */
	private BigDecimal ppal;
	/** 利息 */
	private BigDecimal interest;
	/** 本金累计 */
	private BigDecimal ppalCnt;
	/** 利息累计 */
	private BigDecimal interestCnt;
	/** 贷款余额 */
	private BigDecimal loanBalance;
	/** 日利率 */
	private BigDecimal dll;
	/**
	 * 初始化
	 */
	private void init() {

		ppalInterest = BigDecimal.ZERO;

		ppal = BigDecimal.ZERO;

		interest = BigDecimal.ZERO;

		ppalCnt = BigDecimal.ZERO;

		interestCnt = BigDecimal.ZERO;

		loanBalance = BigDecimal.ZERO;

	}
	
	/**
	 * 贷款计算
	 * 
	 * @param interForm
	 * @return
	 */
	@Override
	public List<InterestVO> calCulate(InterestCalCulateForm interForm) throws Exception {
		init();
		// 日利率
		dll = interForm.getRate().divide(GlobalConstants.DCNT, 50, BigDecimal.ROUND_HALF_UP);

		if (interForm.getLoanStartDate().compareTo(interForm.getLoanEndDate()) >= 0) {
			//throw new BizException("放款开始时间不能大于等于合同结束时间");
		}

		if ("5".equals(interForm.getRepayment())) {// 等额本金
			return bjCalCulate(interForm);
		} else if ("4".equals(interForm.getRepayment())) {// 等额本息
			try {
				return bxCalCulate(interForm);
			} catch (Exception e1) {
				e1.printStackTrace();
				return bxCalCulateByMonth(interForm);
			}
		} else if ("2".equals(interForm.getRepayment())) {// 一次性付息还款
			return opCalCulate(interForm);
		} else if ("1".equals(interForm.getRepayment())) {// 按固定周期付息,到期还款
			return fyCalCulate(interForm);
		} else if ("3".equals(interForm.getRepayment())) {// 按固定周期付息,按还款计划还本
			return fpbCalCulate(interForm);
		} else if ("6".equals(interForm.getRepayment())) {// 等本等息
			return bjbxCalCulate(interForm);
		}
		return null;
	}
	/**
	 * 获取期数 并做时间处理(贷款开始时间与贷款还款日,贷款结束时间与贷款还款日)
	 * 
	 * @param interForm
	 * @return
	 */
	private BigDecimal getPeriodNumber(InterestCalCulateForm interForm) {
		BigDecimal periodNumber = BigDecimal.ONE;// 期次
		Date startDate = interForm.getLoanStartDate(); // 放款日期
		while (true) {
			// 约定还款日期
			startDate = DateTools.getDateYYYYMMDD(startDate, interForm.getRepaymentNumber(), interForm.getRepaymentDate());
			if (startDate.compareTo(interForm.getLoanEndDate()) >= 0
					|| DateTools.isMonthCompare(startDate, interForm.getLoanEndDate())) {
				break;
			} else {
				periodNumber = periodNumber.add(BigDecimal.ONE);
			}
		}
		return periodNumber;
	}
	/**
	 * 排序自定义还款计划
	 * 
	 * @param planList自定义还款计划集
	 * @return
	 */
	private List<InterestPlanForm> sortList(List<InterestPlanForm> planList) {
		// 冒泡排序
		List<InterestPlanForm> resultList = new ArrayList<InterestPlanForm>();
		Object array[] = planList.toArray();
		InterestPlanForm planFormF = null;
		InterestPlanForm planFormS = null;
		for (int int_i = 0; int_i < array.length; int_i++) {
			for (int int_j = int_i + 1; int_j < array.length; int_j++) {
				planFormF = (InterestPlanForm) array[int_i];
				planFormS = (InterestPlanForm) array[int_j];
				if (planFormF.getPlanTime().compareTo(planFormS.getPlanTime()) > 0) {
					array[int_j] = planFormF;
					array[int_i] = planFormS;
				}
			}
		}
		for (int int_i = 0; int_i < array.length; int_i++) {
			resultList.add((InterestPlanForm) array[int_i]);
		}
		return resultList;
	}
	/**
	 * 等额本金
	 * 
	 * @param interestForm
	 *            贷款金额 计算公式: 每月还款数 = (贷款金额/月份) + (贷款金额 - 上次归还金额数) * 利率
	 */
	private List<InterestVO> bjCalCulate(InterestCalCulateForm interestForm) {
		BigDecimal mCnt = getPeriodNumber(interestForm);// 获取期数
		ppal = interestForm.getLoanAmount().divide(mCnt, 2, BigDecimal.ROUND_HALF_UP);// 每月还款本金
		loanBalance = interestForm.getLoanAmount();// 贷款余额
		List<InterestVO> interestList = new ArrayList<InterestVO>();
		InterestVO repayPlan = null;
		Date startDate = interestForm.getLoanStartDate(); // 放款日期
		Date endDate = null; // 约定还款日期
		int int_i = 1;
		while (true) {
			endDate = DateTools.getDateYYYYMMDD(startDate, interestForm.getRepaymentNumber(), interestForm
					.getRepaymentDate());// 约定还款日期
			if (int_i == mCnt.intValue()) { // 是否最后一期
				endDate = interestForm.getLoanEndDate();
			}
			// 利息
			interest = loanBalance.multiply(dll).multiply(new BigDecimal(DateTools.getDateDiff(startDate, endDate)));
			interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
			ppalInterest = ppal.add(interest);// 本息
			interestCnt = interestCnt.add(interest);// 累计利息
			ppalCnt = ppalCnt.add(ppal);// 累计本金
			loanBalance = loanBalance.subtract(ppal);
			if (int_i == mCnt.intValue()) {// 是否最后一期
				ppalInterest = ppalInterest.add(loanBalance);
				ppal = ppal.add(loanBalance);
				ppalCnt = ppalCnt.add(loanBalance);
				loanBalance = BigDecimal.ZERO;
			}
			if (int_i > 1) {// 第2期开始的计算日期 = 上期约定还款日期 + 1
				startDate = DateTools.getNextDateYYYYMMDD(startDate);
			}
			repayPlan = new InterestVO(int_i, DateTools.dateToString(endDate, GlobalConstants.DATE_FORMAT),
					ppalInterest, ppal, interest, ppalCnt, interestCnt, loanBalance, startDate, endDate);
			interestList.add(repayPlan);
			if (endDate.compareTo(interestForm.getLoanEndDate()) >= 0) {
				break;
			} else {
				startDate = endDate;
				int_i++;
			}
		}
		return interestList;
	}
	/**
	 * 等本等息-计算公式:当期本金=本金/期次,当期利息=利息/期次
	 * 
	 * @param interestForm
	 * @return
	 */
	private List<InterestVO> bjbxCalCulate(InterestCalCulateForm interestForm) {

		BigDecimal mCnt = getPeriodNumber(interestForm);// 获取期数
		ppal = interestForm.getLoanAmount().divide(mCnt, 2, BigDecimal.ROUND_HALF_UP);// 每月还款本金
		loanBalance = interestForm.getLoanAmount();// 贷款余额
		BigDecimal interestTotal = loanBalance.multiply(dll).multiply(
				new BigDecimal(DateTools.getDateDiff(interestForm.getLoanStartDate(), interestForm.getLoanEndDate())));
		interestTotal = interestTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		interest = interestTotal.divide(mCnt, 2, BigDecimal.ROUND_HALF_UP);// 每月还款利息
		List<InterestVO> interestList = new ArrayList<InterestVO>();
		InterestVO repayPlan = null;
		Date startDate = interestForm.getLoanStartDate(); // 放款日期
		Date endDate = null; // 约定还款日期
		int int_i = 1;
		while (true) {
			endDate = DateTools.getDateYYYYMMDD(startDate, interestForm.getRepaymentNumber(), interestForm
					.getRepaymentDate());// 约定还款日期
			if (int_i == mCnt.intValue()) { // 是否最后一期
				endDate = interestForm.getLoanEndDate();
			}
			ppalInterest = ppal.add(interest);// 本息
			interestCnt = interestCnt.add(interest);// 累计利息
			ppalCnt = ppalCnt.add(ppal);// 累计本金
			loanBalance = loanBalance.subtract(ppal);
			interestTotal = interestTotal.subtract(interest);
			if (int_i == mCnt.intValue()) {// 是否最后一期
				ppalInterest = ppalInterest.add(loanBalance).add(interestTotal);
				ppal = ppal.add(loanBalance);
				interest = interest.add(interestTotal);
				ppalCnt = ppalCnt.add(loanBalance);
				interestCnt = interestCnt.add(interestTotal);
				loanBalance = BigDecimal.ZERO;
			}
			if (int_i > 1) {// 第2期开始的计算日期 = 上期约定还款日期 + 1
				startDate = DateTools.getNextDateYYYYMMDD(startDate);
			}
			repayPlan = new InterestVO(int_i, DateTools.dateToString(endDate, GlobalConstants.DATE_FORMAT),
					ppalInterest, ppal, interest, ppalCnt, interestCnt, loanBalance, startDate, endDate);
			interestList.add(repayPlan);
			if (endDate.compareTo(interestForm.getLoanEndDate()) >= 0) {
				break;
			} else {
				startDate = endDate;
				int_i++;
			}
		}
		return interestList;
	}
	
	/**
	 * 按固定周期付息,按还款计划还本
	 * 
	 * @param interForm
	 */
	private List<InterestVO> fpbCalCulate(InterestCalCulateForm interForm) {
		List<InterestVO> interestList = new ArrayList<InterestVO>();
		InterestVO repayPlan = null;
		InterestPlanForm planForm = null;
		loanBalance = interForm.getLoanAmount(); // 贷款余额
		interForm.setPlanList(this.sortList(interForm.getPlanList())); // 自定义还款计划
		Date date = interForm.getLoanStartDate(); // 放款日期
		for (int int_i = 0; int_i < interForm.getPlanList().size(); int_i++) {
			planForm = interForm.getPlanList().get(int_i);
			interest = loanBalance.multiply(dll).multiply(
					new BigDecimal(DateTools.getDateDiff(date, planForm.getPlanTime()))); // 利息
			if (interest.compareTo(BigDecimal.ZERO) < 0) {
				interest = BigDecimal.ZERO;
			}
			interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
			interestCnt = interestCnt.add(interest); // 累计利息
			ppal = planForm.getPlanPpal(); // 本金
			ppalCnt = ppalCnt.add(ppal); // 累计本金
			ppalInterest = ppal.add(interest); // 本息
			loanBalance = loanBalance.subtract(ppal);
			if (int_i > 0) { // 从第2期开始...开始日期是上期约定还款日 + 1
				date = DateTools.getNextDateYYYYMMDD(date);
			}
			repayPlan = new InterestVO(int_i + 1, DateTools.dateToString(planForm.getPlanTime(),
					GlobalConstants.DATE_FORMAT), ppalInterest, ppal, interest, ppalCnt, interestCnt, loanBalance, date,
					planForm.getPlanTime());
			interestList.add(repayPlan);
			date = planForm.getPlanTime();
		}
		return interestList;
	}
	/**
	 * 按固定周期付息,到期还本
	 * 
	 * @param interForm
	 */
	private List<InterestVO> fyCalCulate(InterestCalCulateForm interestForm) {
		BigDecimal monthCnt = BigDecimal.ONE;// 获取期数
		Date startDate = interestForm.getLoanStartDate(); // 放款日期
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		boolean startFlag = false; // 头标记
		Date dateFlag = null; // 头开始日期
		int date = 0;
		if (calendar.get(Calendar.DATE) < interestForm.getRepaymentDate()) { // 放款日期日是否小于约定还款日
			date = DateTools.getActualMaximum(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
			if (date != calendar.get(Calendar.DATE)) {
				monthCnt = monthCnt.add(BigDecimal.ONE);
				if (date > interestForm.getRepaymentDate()) {
					date = interestForm.getRepaymentDate();
				}
				startDate = DateTools.getDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, date);
				startFlag = true;
				dateFlag = startDate;

			}
		}

		while (true) {
			// 约定还款日期
			startDate = DateTools.getDateYYYYMMDD(startDate, interestForm.getRepaymentNumber(), interestForm
					.getRepaymentDate());
			if (startDate.compareTo(interestForm.getLoanEndDate()) >= 0
					|| DateTools.dateToString(startDate, "yyyy-MM").equals(
							DateTools.dateToString(interestForm.getLoanEndDate(), "yyyy-MM"))) {
				break;
			} else {
				monthCnt = monthCnt.add(BigDecimal.ONE);
			}
		}
		List<InterestVO> interestList = new ArrayList<InterestVO>();
		InterestVO repayPlan = null;
		loanBalance = interestForm.getLoanAmount(); // 贷款余额
		startDate = interestForm.getLoanStartDate(); // 放款日期
		Date endDate = null;// 约定还款日期
		int int_i = 1;
		while (true) {
			if (int_i == 1 && startFlag) { // 首期是否小于约定还款日
				endDate = dateFlag;
			} else {
				endDate = DateTools.getDateYYYYMMDD(startDate, interestForm.getRepaymentNumber(), interestForm
						.getRepaymentDate());
			}
			if (int_i == monthCnt.intValue()) {// 是否最后一期
				endDate = interestForm.getLoanEndDate();
			}
			interest = loanBalance.multiply(dll).multiply(new BigDecimal(DateTools.getDateDiff(startDate, endDate)));// 利息
			interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
			ppalInterest = interest;// 本息
			interestCnt = interestCnt.add(interest);// 累计利息
			if (int_i == monthCnt.intValue()) { // 是否最后一期 特殊处理最后一期
				ppalInterest = ppalInterest.add(loanBalance);
				ppal = loanBalance;
				ppalCnt = ppalCnt.add(loanBalance);
				loanBalance = BigDecimal.ZERO;
			}
			if (int_i > 1) {// 第2期开始的计算日期 = 上期约定还款日期 + 1
				startDate = DateTools.getNextDateYYYYMMDD(startDate);
			}
			repayPlan = new InterestVO(int_i, DateTools.dateToString(endDate, GlobalConstants.DATE_FORMAT),
					ppalInterest, ppal, interest, ppalCnt, interestCnt, loanBalance, startDate, endDate);
			interestList.add(repayPlan);
			if (endDate.compareTo(interestForm.getLoanEndDate()) >= 0) {// 是否超过结束日期
				break;
			} else {
				startDate = endDate;
				int_i++;
			}
		}
		return interestList;
	}
	/**
	 * 一次性还本付息
	 * 
	 * @param interForm
	 *            贷款金额 计算公式: 到期一次还本付息额=贷款本金 * [1 + 月利率 * 贷款期(月) ]
	 */
	private List<InterestVO> opCalCulate(InterestCalCulateForm interForm) {
		int dayCnt = DateTools.getDateDiff(interForm.getLoanStartDate(), interForm.getLoanEndDate());// 天数
		interest = interForm.getLoanAmount().multiply(dll).multiply(new BigDecimal(dayCnt));// 利息
		ppal = interForm.getLoanAmount();// 本金
		interestCnt = interest;// 累计利息
		ppalCnt = ppal; // 累计本金
		ppalInterest = ppal.add(interest);// 本息
		List<InterestVO> interestList = new ArrayList<InterestVO>();
		// 还款期间
		interestList.add(new InterestVO(1, DateTools
				.dateToString(interForm.getLoanEndDate(), GlobalConstants.DATE_FORMAT), ppalInterest, ppal, interest,
				ppalCnt, interestCnt, loanBalance, interForm.getLoanStartDate(), interForm.getLoanEndDate()));
		return interestList;
	}
	/**
	 * 等额本息
	 * 
	 * @param interestForm
	 *            贷款金额 计算公式: 每月还款数 = (贷款金额 * 利率 * (1 + 利率 ) ^ 期数 )/((1 + 利率) ^
	 *            期数 - 1)
	 */
	private List<InterestVO> bxCalCulateByMonth(InterestCalCulateForm interestForm) {

		// 获取期数
		BigDecimal monthCnt = getPeriodNumber(interestForm);
		// 还款余额
		loanBalance = interestForm.getLoanAmount();
		BigDecimal mll = interestForm.getRate().divide(GlobalConstants.MCNT, 50, BigDecimal.ROUND_HALF_UP);
		BigDecimal qll = mll.multiply(new BigDecimal(interestForm.getRepaymentNumber())).add(BigDecimal.ONE); //
		// 期利率
		BigDecimal totalLl = qll.pow(monthCnt.intValue());
		ppalInterest = interestForm.getLoanAmount().multiply(totalLl).multiply(
				mll.multiply(new BigDecimal(interestForm.getRepaymentNumber()))).divide(
				totalLl.subtract(BigDecimal.ONE), 2, BigDecimal.ROUND_HALF_UP);
		Date startDate = interestForm.getLoanStartDate();
		Date endDate = null;
		int int_i = 1;
		List<InterestVO> interestList = new ArrayList<InterestVO>();
		InterestVO repayPlan = null;
		while (true) {
			endDate = DateTools.getDateYYYYMMDD(startDate, interestForm.getRepaymentNumber(), interestForm
					.getRepaymentDate());
			if (int_i == monthCnt.intValue()) { // 是否最后一期
				endDate = interestForm.getLoanEndDate();
			}
			interest = loanBalance.multiply(mll).multiply(new BigDecimal(interestForm.getRepaymentNumber()));
			interest = interest.setScale(2, BigDecimal.ROUND_HALF_UP);
			ppal = ppalInterest.subtract(interest);
			interestCnt = interestCnt.add(interest);
			ppalCnt = ppalCnt.add(ppal);
			loanBalance = loanBalance.subtract(ppal);
			if (int_i == monthCnt.intValue()) {
				ppalInterest = ppalInterest.add(loanBalance);
				ppal = ppal.add(loanBalance);
				ppalCnt = ppalCnt.add(loanBalance);
				loanBalance = BigDecimal.ZERO;
			}
			if (int_i > 1) {
				startDate = DateTools.getNextDateYYYYMMDD(startDate);
			}
			repayPlan = new InterestVO(int_i, DateTools.dateToString(endDate, GlobalConstants.DATE_FORMAT),
					ppalInterest, ppal, interest, ppalCnt, interestCnt, loanBalance, startDate, endDate);
			interestList.add(repayPlan);
			if (endDate.compareTo(interestForm.getLoanEndDate()) >= 0) {
				break;
			} else {
				startDate = endDate;
				int_i++;
			}
		}
		return interestList;
	}
	/**
	 * 等额本息
	 * 
	 * @param interestForm
	 *            贷款金额 计算公式: 每月还款数 = (贷款金额 * 利率 * (1 + 利率 ) ^ 期数 )/((1 + 利率) ^
	 *            期数 - 1)
	 */
	private List<InterestVO> bxCalCulate(InterestCalCulateForm interestForm) {
		BigDecimal monthCnt = null;// 获取期数
		if (interestForm.getPeriodCnt() == null) {
			monthCnt = getPeriodNumber(interestForm);
		} else {
			monthCnt = interestForm.getPeriodCnt();
		}
		Date startDate = interestForm.getLoanStartDate(); // 放款日期
		Date endDate = null; // 约定还款日期
		loanBalance = interestForm.getLoanAmount(); // 贷款余额
		int each_days[] = new int[monthCnt.intValue()]; // 期次集合,期次天数
		int int_i = 1;
		if (interestForm.getEach_days() == null) { // 放款or贷款试算
			while (true) {
				endDate = DateTools.getDateYYYYMMDD(startDate, interestForm.getRepaymentNumber(), interestForm
						.getRepaymentDate());
				if (int_i == monthCnt.intValue()) {
					endDate = interestForm.getLoanEndDate();
				}
				each_days[int_i - 1] = DateTools.getDateDiff(startDate, endDate);
				if (endDate.compareTo(interestForm.getLoanEndDate()) >= 0) {
					break;
				} else {
					startDate = endDate;
					int_i++;
				}
			}
		} else { // 调整还款计划
			each_days = interestForm.getEach_days();
		}
		// ---下面用法(execl 单变量)
		// 每期总额，理论上应该是每期都相等，但是因为精度原因，最后一期需要作出牺牲
		BigDecimal each_sum = new BigDecimal(0);
		// 每期本金
		BigDecimal[] each_owns = new BigDecimal[monthCnt.intValue()];
		// 每期利息
		BigDecimal[] each_rates = new BigDecimal[monthCnt.intValue()];
		// 开关
		boolean forceBreak = false;
		// 每次调整增量，精度初始值，方向标，贷款总额与本金合计差额
		double inc = 0, jd = 0.0000001, side = -1, x;
		// 递归次数
		int count = 0;
		// 每期本金之和
		BigDecimal own_sum = BigDecimal.ZERO;
		// 剩余金额
		BigDecimal var_left = BigDecimal.ZERO;
		// 最小误差
		double minDeviation = 1;
		do {
			// 初始化
			own_sum = new BigDecimal(0);
			var_left = interestForm.getLoanAmount();
			each_sum = each_sum.add(NumberUtil.formatDouble(inc));
			for (int i = 0; i < monthCnt.intValue(); i++) {
				// 每期利息
				each_rates[i] = var_left.multiply(dll).multiply(new BigDecimal(each_days[i])).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				each_owns[i] = each_sum.subtract(each_rates[i]);

				var_left = var_left.subtract(each_owns[i]);
				own_sum = own_sum.add(each_owns[i]);
			}
			x = interestForm.getLoanAmount().subtract(own_sum).doubleValue();
			if (side == -1) {
				side = x > 0 ? 0.1 : -0.1;
			}

			if (x * side > 0) {
				side = -side;
				if (jd == 10) {
					// 如果最小误差两次一样，退出计算
					if (minDeviation == Math.abs(x)) {
						break;
					}
					// 保存最小误差
					if (minDeviation > Math.abs(x)) {
						minDeviation = Math.abs(x);
					}
				} else {
					jd *= 10;
				}
			}
			// 增加量修改
			inc = -side / jd;
		} while (!forceBreak && count++ < 1000 && Math.abs(x) > 0.01);

		// // 最后的调整，牺牲最后一期，满足本金之和等于贷款总额
		// BigDecimal last_magic =
		// own_sum.subtract(interestForm.getLoanAmount());
		// if (last_magic.doubleValue() > 0) {
		// each_owns[monthCnt.intValue() - 1] = each_owns[monthCnt.intValue() -
		// 1].subtract(last_magic);
		// }

		List<InterestVO> interestList = new ArrayList<InterestVO>();
		InterestVO repayPlan = null;
		startDate = interestForm.getLoanStartDate();
		ppalInterest = each_sum; // 本息
		for (int_i = 1; int_i <= monthCnt.intValue(); int_i++) {
			ppal = each_owns[int_i - 1]; // 本金
			if (ppal.compareTo(BigDecimal.ZERO) < 0) {
				//throw new BizException("本金小于0");
			}
			interest = each_rates[int_i - 1]; // 利息
			ppalCnt = ppalCnt.add(ppal); // 累计本金
			interestCnt = interestCnt.add(interest);// 累计利息
			loanBalance = loanBalance.subtract(ppal);
			if (int_i == monthCnt.intValue()) {
				ppalInterest = ppalInterest.add(loanBalance);
				ppal = ppal.add(loanBalance);
				ppalCnt = ppalCnt.add(loanBalance);
				loanBalance = BigDecimal.ZERO;
			}
			endDate = DateTools.addDay(startDate, each_days[int_i - 1]);
			if (int_i > 1) {// 第2期开始的计算日期 = 上期约定还款日期 + 1
				startDate = DateTools.getNextDateYYYYMMDD(startDate);
			}
			repayPlan = new InterestVO(int_i, DateTools.dateToString(endDate, GlobalConstants.DATE_FORMAT),
					ppalInterest, ppal, interest, ppalCnt, interestCnt, loanBalance, startDate, endDate);
			interestList.add(repayPlan);
			startDate = endDate;
		}
		return interestList;
	}
}
