package weixin.cms.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;

import weixin.cms.entity.CmsMenuEntity;

public interface CmsMenuServiceI extends CommonService {
	@Override
	public <T> Serializable save(T entity);

	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity);

	public List<CmsMenuEntity> list(CmsMenuEntity cmsMenuEntity, int page,
			int rows);

	public List<CmsMenuEntity> list(Map params);

	public List<CmsMenuEntity> list(Map params, int page, int rows);
}
