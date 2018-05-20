package com.jce.framework.web.demo.service.impl.test;

import com.jce.framework.web.demo.service.test.JeecgDemoServiceI;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("jeecgDemoService")
@Transactional
public class JeecgDemoServiceImpl extends CommonServiceImpl implements JeecgDemoServiceI {
	
}
