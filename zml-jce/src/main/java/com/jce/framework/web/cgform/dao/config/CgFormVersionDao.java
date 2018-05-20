package com.jce.framework.web.cgform.dao.config;

import com.jce.framework.web.cgform.entity.config.CgFormHeadEntity;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

/**
 * 
 * @Title:CgFormFieldDao
 * @description:
 */
@Repository("cgFormVersionDao")
public interface CgFormVersionDao {
	@Arguments("tableName")
	public String  getCgFormVersionByTableName(String tableName);
	@Arguments("id")
	public String  getCgFormVersionById(String id);
	@Arguments({"newVersion","formId"})
	public void  updateVersion(String newVersion,String formId);
	
	@Arguments({"id"})
	public CgFormHeadEntity  getCgFormById(String id);
}
