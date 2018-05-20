package com.jce.framework.web.cgform.dao.config;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

/**
 * 
 * @Title:CgFormFieldDao
 * @description:
 */
@Repository("cgFormFieldDao")
public interface CgFormFieldDao {
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormFieldByTableName(String tableName);
	
	@Arguments("tableName")
	public List<Map<String, Object>> getCgFormHiddenFieldByTableName(String tableName);
	
}
