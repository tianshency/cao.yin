package com.jce.framework.web.system.service;

import java.util.List;

import com.jce.framework.core.common.service.CommonService;
import com.jce.framework.web.system.pojo.base.DynamicDataSourceEntity;

public interface DynamicDataSourceServiceI extends CommonService{

	public List<DynamicDataSourceEntity> initDynamicDataSource();

	public void refleshCache();

	//add-begin--Author:luobaoli  Date:20150620 for：增加通过数据源Key获取数据源Type

	public DynamicDataSourceEntity getDynamicDataSourceEntityForDbKey(String dbKey);

	//add-end--Author:luobaoli  Date:20150620 for：增加通过数据源Key获取数据源Type

}
