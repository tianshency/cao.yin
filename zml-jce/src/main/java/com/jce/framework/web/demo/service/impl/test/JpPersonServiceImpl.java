package com.jce.framework.web.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.web.demo.service.test.JpPersonServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;

@Service("jpPersonService")
@Transactional
public class JpPersonServiceImpl extends CommonServiceImpl implements JpPersonServiceI {
	
}