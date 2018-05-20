package com.jce.framework.web.demo.service.test;

import java.util.List;

import com.jce.framework.web.demo.entity.test.JeecgOrderCustomEntity;
import com.jce.framework.web.demo.entity.test.JeecgOrderMainEntity;
import com.jce.framework.web.demo.entity.test.JeecgOrderProductEntity;

import com.jce.framework.core.common.service.CommonService;


public interface JeecgOrderMainServiceI extends CommonService{
	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(JeecgOrderMainEntity jeecgOrderMain,List<JeecgOrderProductEntity> jeecgOrderProducList,List<JeecgOrderCustomEntity> jeecgOrderCustomList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(JeecgOrderMainEntity jeecgOrderMain,List<JeecgOrderProductEntity> jeecgOrderProducList,List<JeecgOrderCustomEntity> jeecgOrderCustomList,boolean jeecgOrderCustomShow) ;
	public void delMain (JeecgOrderMainEntity jeecgOrderMain);
}
