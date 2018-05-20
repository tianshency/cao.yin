package com.jce.framework.web.demo.service.test;

import com.jce.framework.web.demo.entity.test.WebOfficeEntity;

import com.jce.framework.core.common.service.CommonService;
import org.springframework.web.multipart.MultipartFile;

public interface WebOfficeServiceI extends CommonService{
	public void saveObj(WebOfficeEntity docObj, MultipartFile file);
}
