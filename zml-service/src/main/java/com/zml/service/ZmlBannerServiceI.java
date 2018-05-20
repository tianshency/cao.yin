package com.zml.service;

import com.jce.framework.core.common.service.CommonService;
import com.zml.base.entity.ZmlBannerEntity;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.cache.RedisUtilTool;
import com.zml.enums.BannerCategory;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface ZmlBannerServiceI extends CommonService{
	
 	public void delete(ZmlBannerEntity entity) throws Exception;
 	
 	public Serializable save(ZmlBannerEntity entity, MultipartHttpServletRequest mrequest) throws Exception;
 	
 	public void saveOrUpdate(ZmlBannerEntity entity) throws Exception;
 	
 	//bannerList取得 根据类别(顶部、头条 轮播) 、状态为显示的、 最新加入的 5条数据
	public List<ZmlBannerEntity> bannerListGet(String category, int num);
	
	//设置缓存 轮播数据
	public String setCacheBannerData(RedisUtilTool redisUtilTool, ZmlBannerEntity zmlBanner);
	//设置 清除缓存 轮播数据
	public String setCancelCacheBannerData(RedisUtilTool redisUtilTool, ZmlBannerEntity zmlBanner);
}
