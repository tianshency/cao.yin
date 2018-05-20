package com.zml.loan_tools.service;

import java.util.List;

import com.zml.loan_tools.common.model.InterestCalCulateForm;
import com.zml.loan_tools.common.model.InterestVO;

public interface InterestCalCulateService {
	/**
	 * 贷款计算
	 * @param interForm
	 * @return
	 */
	List<InterestVO> calCulate(InterestCalCulateForm interForm) throws Exception;
}
