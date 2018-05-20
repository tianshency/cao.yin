package com.jce.framework.web.system.service;

import java.util.List;

import com.jce.framework.web.system.pojo.base.TSAttachment;

import com.jce.framework.core.common.service.CommonService;

/**
 * 
* @author  bobo
 *
 */
public interface DeclareService extends CommonService{
	
	public List<TSAttachment> getAttachmentsByCode(String businessKey,String description);
	
}
