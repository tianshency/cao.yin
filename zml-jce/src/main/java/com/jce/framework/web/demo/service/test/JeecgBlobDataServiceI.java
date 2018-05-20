package com.jce.framework.web.demo.service.test;

import com.jce.framework.core.common.service.CommonService;
import org.springframework.web.multipart.MultipartFile;

public interface JeecgBlobDataServiceI extends CommonService{
	public void saveObj(String documentTitle, MultipartFile file);

}
