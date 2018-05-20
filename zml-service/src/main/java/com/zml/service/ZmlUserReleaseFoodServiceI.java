package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserReleaseFoodEntity;

public interface ZmlUserReleaseFoodServiceI extends CommonService{
	
 	public void delete(ZmlUserReleaseFoodEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserReleaseFoodEntity entity, MultipartHttpServletRequest mrequest) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserReleaseFoodEntity entity, MultipartHttpServletRequest mrequest) throws Exception;
 	//查询分页
 	public List<Map<String, Object>> findDatagrid(ZmlUserReleaseFoodEntity entity, DataGrid dataGrid);
}
