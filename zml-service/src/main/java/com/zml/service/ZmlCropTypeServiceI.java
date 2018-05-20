package com.zml.service;

import java.io.Serializable;
import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlCropTypeEntity;

public interface ZmlCropTypeServiceI extends CommonService{
	
 	public void delete(ZmlCropTypeEntity entity) throws Exception;
 	
 	public Serializable save(ZmlCropTypeEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlCropTypeEntity entity) throws Exception;
 	
 	//查询首页列表
 	public List<Object> findIndexList(int num);
 	
}
