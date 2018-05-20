package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserReleaseTerritoryEntity;

public interface ZmlUserReleaseTerritoryServiceI extends CommonService{
	
 	public void delete(ZmlUserReleaseTerritoryEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserReleaseTerritoryEntity entity, MultipartHttpServletRequest mrequest) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserReleaseTerritoryEntity entity, MultipartHttpServletRequest mrequest) throws Exception;
 	//查询分页
 	public List<Map<String, Object>> findDatagrid(ZmlUserReleaseTerritoryEntity entity, DataGrid dataGrid);
}
