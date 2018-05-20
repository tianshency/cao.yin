package weixin.cms.service;

import java.util.List;
import java.util.Map;

import com.jce.framework.core.common.service.CommonService;

import weixin.cms.entity.CmsArticleEntity;

public interface CmsArticleServiceI extends CommonService {
	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity);

	public List<CmsArticleEntity> list(CmsArticleEntity cmsArticleEntity,
			int page, int rows);

	@SuppressWarnings("unchecked")
	public List<CmsArticleEntity> listByMap(Map params);

	public List<CmsArticleEntity> listByMap(Map params, int page, int rows);

	public int getCount(Map params);
	
	public CmsArticleEntity getCmsArticleEntity(String id);
}
