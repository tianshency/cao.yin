package com.zml.service;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlUserReleaseInfoEntity;

public interface ZmlUserReleaseInfoServiceI extends CommonService{
	
 	public void delete(ZmlUserReleaseInfoEntity entity) throws Exception;
 	
 	public Serializable save(ZmlUserReleaseInfoEntity entity, MultipartHttpServletRequest mrequest) throws Exception;
 	
 	public void saveOrUpdate(ZmlUserReleaseInfoEntity entity) throws Exception;
 	
 	//查询分页
 	public List<Map<String, Object>> findDatagrid(ZmlUserReleaseInfoEntity entity, DataGrid dataGrid);
 	
 	//根据用户ID查询发布数量，返回各种类型发布的数量
 	public Map<String,String> findCountByUserId(String userId);
 	
}
