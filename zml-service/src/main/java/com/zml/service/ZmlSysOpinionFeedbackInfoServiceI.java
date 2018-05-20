package com.zml.service;
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlSysOpinionFeedbackInfoEntity;

public interface ZmlSysOpinionFeedbackInfoServiceI extends CommonService{
	
 	public void delete(ZmlSysOpinionFeedbackInfoEntity entity) throws Exception;
 	
 	public Serializable save(ZmlSysOpinionFeedbackInfoEntity entity, MultipartFile[] upload) throws Exception;
 	
 	public void saveOrUpdate(ZmlSysOpinionFeedbackInfoEntity entity) throws Exception;
 	
 	public void saveOpinion(ZmlSysOpinionFeedbackInfoEntity entity,MultipartFile[] upload,String showPic) throws Exception;
}
