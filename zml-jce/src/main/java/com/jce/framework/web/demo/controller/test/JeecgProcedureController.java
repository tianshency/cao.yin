package com.jce.framework.web.demo.controller.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jce.framework.core.common.controller.BaseController;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.extend.sqlsearch.SqlGenerateUtil;
import com.jce.framework.tag.easyui.TagUtil;
import com.jce.framework.web.demo.entity.test.JeecgDemo;
import com.jce.framework.web.demo.service.test.JeecgProcedureServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@Scope("prototype")
@Controller
@RequestMapping("/jeecgProcedureController")
public class JeecgProcedureController extends BaseController{
	@Autowired
	private JeecgProcedureServiceI jeecgProcedureService;
	
	@RequestMapping(params = "procedure")
	public String procudure(HttpServletRequest request) {
		return "sys/demo/base/procedure/procedure";
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(JeecgDemo demo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		List dealFields = new ArrayList();
		String field = dataGrid.getField();
		if(field.length()>0) field = field.substring(0,field.length()-1);
		
		String tableName = SqlGenerateUtil.generateTable(demo);//查询的表
		StringBuffer dbFields = SqlGenerateUtil.generateDBFields(demo,field,dealFields);//sql中需要查询的列
		StringBuffer whereSql = SqlGenerateUtil.generateWhere(demo, request.getParameterMap());//sql查询的条件
		
		List<Map<String,Object>> datas = null;
		//scott 采用springjdbc 直接调用存储过程
		try {
			datas = jeecgProcedureService.queryDataByProcedure(tableName,dbFields.toString(),whereSql.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.write(TagUtil.getJsonByMap(dealFields,datas));
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				pw.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
