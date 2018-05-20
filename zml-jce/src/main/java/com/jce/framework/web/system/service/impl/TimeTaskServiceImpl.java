package com.jce.framework.web.system.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.web.system.service.TimeTaskServiceI;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;

@Service("timeTaskService")
@Transactional
public class TimeTaskServiceImpl extends CommonServiceImpl implements TimeTaskServiceI {
	
}