package com.jce.framework.web.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.web.demo.service.test.JeecgDemoCkfinderServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;

@Service("jeecgDemoCkfinderService")
@Transactional
public class JeecgDemoCkfinderServiceImpl extends CommonServiceImpl implements
		JeecgDemoCkfinderServiceI {

}