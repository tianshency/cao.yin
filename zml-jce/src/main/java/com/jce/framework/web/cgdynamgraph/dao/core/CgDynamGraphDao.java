package com.jce.framework.web.cgdynamgraph.dao.core;

import java.util.List;
import java.util.Map;

import org.jeecgframework.minidao.annotation.Arguments;
import org.springframework.stereotype.Repository;

@Repository("cgDynamGraphDao")
public interface CgDynamGraphDao{

	@Arguments("configId")
	List<Map<String,Object>> queryCgDynamGraphItems(String configId);
	
	@Arguments("id")
	Map queryCgDynamGraphMainConfig(String id);
}
