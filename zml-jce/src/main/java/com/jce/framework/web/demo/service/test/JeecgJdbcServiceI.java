package com.jce.framework.web.demo.service.test;

import com.jce.framework.web.demo.entity.test.JeecgJdbcEntity;
import net.sf.json.JSONObject;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.CommonService;

public interface JeecgJdbcServiceI extends CommonService{
	public void getDatagrid1(JeecgJdbcEntity pageObj, DataGrid dataGrid);
	public void getDatagrid2(JeecgJdbcEntity pageObj, DataGrid dataGrid);
	public JSONObject getDatagrid3(JeecgJdbcEntity pageObj, DataGrid dataGrid);
	public void listAllByJdbc(DataGrid dataGrid);
}
