package com.zml.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlCropPlanEntity;

public interface ZmlCropPlanServiceI extends CommonService{
	
 	public void delete(ZmlCropPlanEntity entity) throws Exception;
 	
 	public Serializable save(ZmlCropPlanEntity entity) throws Exception;
 	
 	public void saveOrUpdate(ZmlCropPlanEntity entity) throws Exception;
 	
 	//public void batchSave(List<ZmlCropPlanEntity> list) throws Exception;
 	//根据userID查询种植计划
 	public List<Map<String, Object>> findDataByUserId(String userId, DataGrid dataGrid);
}
