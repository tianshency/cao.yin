package com.jce.framework.web.demo.service.test;

import com.jce.framework.web.demo.entity.test.TFinanceEntity;
import com.jce.framework.web.demo.entity.test.TFinanceFilesEntity;

import com.jce.framework.core.common.service.CommonService;

public interface TFinanceServiceI extends CommonService{

	void deleteFile(TFinanceFilesEntity file);

	void deleteFinance(TFinanceEntity finance);

}
