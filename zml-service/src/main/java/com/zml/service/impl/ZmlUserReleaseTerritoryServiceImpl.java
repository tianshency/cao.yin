package com.zml.service.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.FileUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.entity.BaseEntity;
import com.zml.base.entity.ZmlUserReleaseDocEntity;
import com.zml.base.entity.ZmlUserReleaseTerritoryEntity;
import com.zml.common.Constant;
import com.zml.enums.DocumentDirName;
import com.zml.enums.FileType;
import com.zml.enums.ReleaseDocType;
import com.zml.service.ZmlUserReleaseTerritoryServiceI;
import com.zml.util.GenerateNo;

@Service("zmlUserReleaseTerritoryService")
@Transactional
public class ZmlUserReleaseTerritoryServiceImpl extends CommonServiceImpl implements ZmlUserReleaseTerritoryServiceI {
 	public void delete(ZmlUserReleaseTerritoryEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlUserReleaseTerritoryEntity entity ,MultipartHttpServletRequest mrequest) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		String id = entity.getId();
 		try {
			// 保存选项图片
			List<MultipartFile> files = mrequest.getFiles("upload");
			//List<String> imgPathList = new ArrayList();
			boolean coverFalg = true;
			if (files != null) {
				// System.out.println(files.size());
				for (int i=0; i< files.size(); i++) {
					ZmlUserReleaseDocEntity releaseDoc = new ZmlUserReleaseDocEntity();
					releaseDoc.setReleaseId(id);
					releaseDoc.setFileFlag(FileType.IMG.getStatusValue());
					releaseDoc.setReleaseType(ReleaseDocType.TERRITORY.getStatusValue());
					releaseDoc.setCreateDate(new Date());
					releaseDoc.setUpdateDate(new Date());
					MultipartFile file = files.get(i);
					if (file != null) {
						FileOutputStream out = null;
						String[] filename = file.getOriginalFilename().split("\\.");
						if (filename == null || filename.length == 0 || "".equals(filename[0])) {
							continue;
						}
						String suffix = filename[filename.length - 1];
						/*
						String path = BaseEntity.class.getResource("").getPath();
						path = path.split("WEB-INF")[0];
						*/
						String path = Constant.IMG_FILE_PATH + Constant.IMG_FILE_PATH_ONE;
						String yyyyMM = DateUtils.formatDateToStr(DateUtils.FORMAT_YYYYMM, new Date());
						String tempPath =  DocumentDirName.RELEASE_TERRITORY.getStatusValue() + "/" + yyyyMM + "/"
								+ id + "/";
						String tempFile = System.currentTimeMillis() + GenerateNo.getRandomNum(Constant.FILE_NAME_RAND_LEN);
						path += tempPath;
						//存大图
						File maxFile = new File(path + tempFile + "_max." + suffix);
						File dir = maxFile.getParentFile();
						if (dir != null && !dir.exists()) {
							dir.mkdirs();
						}
						try {
							maxFile.createNewFile();
							out = new FileOutputStream(maxFile);
							out.write(file.getBytes());
							out.flush();
							out.close();
							String minFilePath = tempPath + tempFile + "_min." + suffix;
							//强第一张图片作为封面
							if(coverFalg){
								entity.setCoverImg(minFilePath);
								coverFalg = false;
							}
							releaseDoc.setImagePath(minFilePath);
							// 缩略图
							File minFile = new File(path + tempFile + "_min." + suffix);
							FileUtils.writeImgFile(file, minFile, Constant.MAX_IMG_FILE_WIDTH, Constant.MINI_IMG_FILE_HEIGHT);
							//imgPathList.add(tempPath + tempFile + "_min." + suffix);
							save(releaseDoc);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlUserReleaseTerritoryEntity entity, MultipartHttpServletRequest mrequest) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlUserReleaseTerritoryEntity t) throws Exception{
		
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlUserReleaseTerritoryEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlUserReleaseTerritoryEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlUserReleaseTerritoryEntity t){
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
		map.put("type", t.getType());
		map.put("title", t.getTitle());
		map.put("user_id", t.getUserId());
		map.put("release_type", t.getReleaseType());
		map.put("position", t.getPosition());
		map.put("cover_img", t.getCoverImg());
		map.put("area", t.getArea());
		map.put("price", t.getPrice());
		map.put("land_type", t.getLandType());
		map.put("terrain", t.getTerrain());
		map.put("valid_start_date", t.getValidStartDate());
		map.put("valid_end_date", t.getValidEndDate());
		map.put("remarks", t.getRemarks());
		map.put("status", t.getStatus());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlUserReleaseTerritoryEntity t){
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
 		sql  = sql.replace("#{type}",String.valueOf(t.getType()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{release_type}",String.valueOf(t.getReleaseType()));
 		sql  = sql.replace("#{position}",String.valueOf(t.getPosition()));
 		sql  = sql.replace("#{cover_img}",String.valueOf(t.getCoverImg()));
 		sql  = sql.replace("#{area}",String.valueOf(t.getArea()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{land_type}",String.valueOf(t.getLandType()));
 		sql  = sql.replace("#{terrain}",String.valueOf(t.getTerrain()));
 		sql  = sql.replace("#{valid_start_date}",String.valueOf(t.getValidStartDate()));
 		sql  = sql.replace("#{valid_end_date}",String.valueOf(t.getValidEndDate()));
 		sql  = sql.replace("#{remarks}",String.valueOf(t.getRemarks()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
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
	public List<Map<String, Object>> findDatagrid(ZmlUserReleaseTerritoryEntity entity, DataGrid dataGrid) {
		String sqlWhere = getSqlWhere(entity);
		// 取出总数据条数（为了分页处理, 如果不用分页，取iCount值的这个处理可以不要）
		String sqlCnt = "select count(1) from zml_user_release_territory t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		
		// 取出当前页的数据 
		String sql = "select t.id,t.user_id userId,t.create_name userName,t.type, t.title,t.area,t.cover_img coverImg,t.valid_start_date validStartDate,t.valid_end_date validEndDate " +
		"  ,t.update_date updateDate,t.create_date createDate,t.view_count viewCount,t.status " 
		+" from zml_user_release_territory t ";
		if (!sqlWhere.isEmpty()) {
			sql += " where" + sqlWhere;
		}
		//查询非删除状态的数据
		//sql += " status ='1'";
		sql += " ORDER BY t.create_date desc";
		List<Map<String, Object>> mapList = findForJdbc(sql, dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setTotal(iCount.intValue());
		return mapList;
	}
	
	// 拼查询条件（where语句）
	private String getSqlWhere(ZmlUserReleaseTerritoryEntity pageObj) {
		// 拼出条件语句
		String sqlWhere = " t.status in ('1') ";
		if (StringUtil.isNotEmpty(pageObj.getUserId())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.user_id = '" + pageObj.getUserId() + "'";
		}
		if (StringUtil.isNotEmpty(pageObj.getType())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.type = '" + pageObj.getType() + "'";
		}
		
		return sqlWhere;
	}
}