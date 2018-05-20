package com.jce.framework.web.system.service;

import com.jce.framework.core.common.service.CommonService;
import com.jce.framework.web.system.pojo.base.TSCategoryEntity;

public interface CategoryServiceI extends CommonService{
	/**
	 * 保存分类管理
	 * @param category
	 */
	void saveCategory(TSCategoryEntity category);

}
