package com.zml.service.impl;
import com.zml.service.ZmlBannerServiceI;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.zml.base.entity.ZmlBannerEntity;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.cache.RedisUtilTool;
import com.zml.common.Constant;
import com.zml.enums.BannerCategory;
import com.zml.enums.DocumentDirName;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.FileUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.PasswordUtil;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;

@Service("zmlBannerService")
@Transactional
public class ZmlBannerServiceImpl extends CommonServiceImpl implements ZmlBannerServiceI {
 	public void delete(ZmlBannerEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlBannerEntity entity, MultipartHttpServletRequest mrequest) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		//保存图片
 		try {
 			boolean coverFalg = true;
			// 封面图片
			/*
			MultipartFile imgFile = mrequest.getFile("coverImg");
			if (imgFile != null) {
				String coverPath = "";
				try {
					FileOutputStream out = null;
					String[] filename = imgFile.getOriginalFilename().split("\\.");
					String suffix = filename[filename.length - 1];
					//String path = BaseEntity.class.getResource("").getPath();
					//path = path.split("WEB-INF")[0];
					String path = Constant.IMG_FILE_PATH + Constant.IMG_FILE_PATH_ONE ;
					String pathParam = DateUtils.formatDateToStr(DateUtils.FORMAT_YYYYMM, new Date());
					coverPath =  DocumentDirName.BANNER.getStatusValue() + "/" + pathParam + "/"+ entity.getId();
					entity.setPath(coverPath + "/min." + suffix);
					path = path + coverPath;
					//存大图
					File max = new File(path + "/max." + suffix);
					File dir = max.getParentFile();
					if (dir != null && !dir.exists()) {
						dir.mkdirs();
					}
					max.createNewFile();
					out = new FileOutputStream(max);
					out.write(imgFile.getBytes());
					out.flush();
					out.close();
					// 保存缩略图
					File mini = new File(path + "/min." + suffix);
					FileUtils.writeImgFile(imgFile, mini, 100, 0);

				} catch (Exception e) {
					e.printStackTrace();
				}
				coverFalg = false;
			}
			*/
 		}catch(Exception e){
 			e.printStackTrace();
 		}
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlBannerEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlBannerEntity t) throws Exception{
		
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlBannerEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlBannerEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlBannerEntity t){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", t.getId());
		map.put("create_name", t.getCreateName());
		map.put("create_by", t.getCreateBy());
		map.put("create_date", t.getCreateDate());
		map.put("update_name", t.getUpdateName());
		map.put("update_by", t.getUpdateBy());
		map.put("update_date", t.getUpdateDate());
		map.put("sys_org_code", t.getSysOrgCode());
		map.put("sys_company_code", t.getSysCompanyCode());
		map.put("version", t.getVersion());
		map.put("title", t.getTitle());
		map.put("path", t.getPath());
		map.put("content", t.getContent());
		map.put("is_view", t.getIsView());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlBannerEntity t){
 		sql  = sql.replace("#{id}",String.valueOf(t.getId()));
 		sql  = sql.replace("#{create_name}",String.valueOf(t.getCreateName()));
 		sql  = sql.replace("#{create_by}",String.valueOf(t.getCreateBy()));
 		sql  = sql.replace("#{create_date}",String.valueOf(t.getCreateDate()));
 		sql  = sql.replace("#{update_name}",String.valueOf(t.getUpdateName()));
 		sql  = sql.replace("#{update_by}",String.valueOf(t.getUpdateBy()));
 		sql  = sql.replace("#{update_date}",String.valueOf(t.getUpdateDate()));
 		sql  = sql.replace("#{sys_org_code}",String.valueOf(t.getSysOrgCode()));
 		sql  = sql.replace("#{sys_company_code}",String.valueOf(t.getSysCompanyCode()));
 		sql  = sql.replace("#{version}",String.valueOf(t.getVersion()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{path}",String.valueOf(t.getPath()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{is_view}",String.valueOf(t.getIsView()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 执行JAVA增强
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//因新增时已经校验了实例化是否可以成功，所以这块就不需要再做一次判断
					obj = MyClassLoader.getClassByScn(cgJavaValue).newInstance();
				}else if("spring".equals(cgJavaType)){
					obj = ApplicationContextUtil.getContext().getBean(cgJavaValue);
				}
				if(obj instanceof CgformEnhanceJavaInter){
					CgformEnhanceJavaInter javaInter = (CgformEnhanceJavaInter) obj;
					javaInter.execute(data);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("执行JAVA增强出现异常！");
			} 
		}
 	}
 	
 	@Override
	public List<ZmlBannerEntity> bannerListGet(String category, int num) {
//		Map rsMap = new Hashtable();
 		String query = " from ZmlBannerEntity t where category ='"+ category +"' and isView='Y' order by createDate desc limit " + num;
		Query queryObject = getSession().createQuery(query);
		List<ZmlBannerEntity> banners = queryObject.list();
//		rsMap.put("key", banners);
//		return rsMap;
		return banners;
	}

	@Override
	public String setCacheBannerData(RedisUtilTool redisUtilTool, ZmlBannerEntity zmlBanner) {
		String rsStr = null;
		try {
			int size = 0;
			/*
			Map temp = new HashMap();
			temp.put("id", zmlBanner.getId());
			temp.put("name", zmlBanner.getTitle());
			temp.put("type", zmlBanner.getCategory());
			*/
			if(BannerCategory.TOP_BANNER.getStatusValue().equals(zmlBanner.getCategory())){
				List topBannerList = (List)redisUtilTool.get("topBannerList");
				if(topBannerList == null ){
					topBannerList = new ArrayList();
				}else{
					size = topBannerList.size();
					if(size >= Constant.HEADLINES_BANNER_NUM_MAX){
						size = Constant.HEADLINES_BANNER_NUM_MAX;
						rsStr = (String)((Map)topBannerList.get(size)).get("id");
						topBannerList.remove(size);
					}
				}
				topBannerList.add(0, zmlBanner);
				redisUtilTool.set("topBannerList", topBannerList);
			}else if(BannerCategory.HEADLINES_BANNER.getStatusValue().equals(zmlBanner.getCategory())){
				List headlinesList = (List)redisUtilTool.get("headlinesList");
				if(headlinesList == null ){
					headlinesList = new ArrayList();
				}else{
					size = headlinesList.size();
					if(size >= Constant.HEADLINES_BANNER_NUM_MAX){
						size = Constant.HEADLINES_BANNER_NUM_MAX;
						rsStr = (String)((Map)headlinesList.get(size)).get("id");
						headlinesList.remove(size);
					}
				}
				headlinesList.add(0, zmlBanner);
				redisUtilTool.set("headlinesList", headlinesList);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsStr;
	}

	@Override
	public String setCancelCacheBannerData(RedisUtilTool redisUtilTool, ZmlBannerEntity zmlBanner) {
		String rsStr = null;
		try {
			int size = 0;
			String idVal = zmlBanner.getId();
			if(BannerCategory.TOP_BANNER.getStatusValue().equals(zmlBanner.getCategory())){
				List topBannerList = (List)redisUtilTool.get("topBannerList");
				if(topBannerList != null && topBannerList.size()>0){
					for (int i = 0; i < topBannerList.size(); i++) {
						ZmlBannerEntity tempZmlBanner = (ZmlBannerEntity)topBannerList.get(i);
						if(tempZmlBanner.getId().equals(idVal)){
							topBannerList.remove(i);
							break;
						}
					}
				}
				redisUtilTool.set("topBannerList", topBannerList);
			}else if(BannerCategory.HEADLINES_BANNER.getStatusValue().equals(zmlBanner.getCategory())){
				List headlinesList = (List)redisUtilTool.get("headlinesList");
				if(headlinesList != null && headlinesList.size()>0){
					for (int i = 0; i < headlinesList.size(); i++) {
						ZmlBannerEntity tempZmlBanner = (ZmlBannerEntity)headlinesList.get(i);
						if(tempZmlBanner.getId().equals(idVal)){
							headlinesList.remove(i);
							break;
						}
					}
				}
				redisUtilTool.set("headlinesList", headlinesList);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsStr;
	}
}