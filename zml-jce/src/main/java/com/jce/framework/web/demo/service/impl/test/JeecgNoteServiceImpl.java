package com.jce.framework.web.demo.service.impl.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.web.demo.service.test.JeecgNoteServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;

@Service("jeecgNoteService")
@Transactional
public class JeecgNoteServiceImpl extends CommonServiceImpl implements JeecgNoteServiceI {
	
}