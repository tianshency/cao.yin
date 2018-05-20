package com.jce.framework.web.cgform.enhance;

import java.util.Map;

import com.jce.framework.core.common.exception.BusinessException;

/**
 * JAVA增强
 * @author luobaoli
 *
 */
public interface CgformEnhanceJavaInter {
	/**
	 * 
	 * @param map 表单数据
	 */
	public void execute(Map map) throws BusinessException;
}
