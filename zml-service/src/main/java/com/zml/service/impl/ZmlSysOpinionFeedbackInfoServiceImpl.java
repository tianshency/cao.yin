package com.zml.service.impl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.FileUtils;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.entity.ZmlSysOpinionFeedbackInfoEntity;
import com.zml.common.Constant;
import com.zml.enums.DocumentDirName;
import com.zml.service.ZmlSysOpinionFeedbackInfoServiceI;
import com.zml.util.GenerateNo;
import com.zml.util.file.UploadImage;

@Service("zmlSysOpinionFeedbackInfoService")
@Transactional
public class ZmlSysOpinionFeedbackInfoServiceImpl extends CommonServiceImpl implements ZmlSysOpinionFeedbackInfoServiceI {

	
 	public void delete(ZmlSysOpinionFeedbackInfoEntity entity) throws Exception{
 		super.delete(entity);
 		//执行删除操作增强业务
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlSysOpinionFeedbackInfoEntity entity,MultipartFile[] uploadFiles) throws Exception{
 		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
 		try {
			// 保存选项图片
			if (uploadFiles != null && uploadFiles.length > 0) {
				String id = entity.getId();
				// System.out.println(files.size());
				for (int i=0; i< uploadFiles.length; i++) {
					/*ZmlUserReleaseDocEntity releaseDoc = new ZmlUserReleaseDocEntity();
					releaseDoc.setFileFlag(FileType.IMG.getStatusValue());
					releaseDoc.setReleaseType(ReleaseDocType.FOOD.getStatusValue());
					releaseDoc.setCreateDate(new Date());
					releaseDoc.setUpdateDate(new Date());
					releaseDoc.setReleaseId(id);*/
					MultipartFile file = uploadFiles[i];
					if (file != null) {
						FileOutputStream out = null;
						String[] filename = file.getOriginalFilename().split("\\.");
						if (filename == null || filename.length == 0 || "".equals(filename[0])) {
							continue;
						}
						String suffix = filename[filename.length - 1];
						/*String path = BaseEntity.class.getResource("").getPath();
						path = path.split("WEB-INF")[0];*/
						String path = Constant.IMG_FILE_PATH + Constant.IMG_FILE_PATH_ONE;
						String yyyyMM = DateUtils.formatDateToStr(DateUtils.FORMAT_YYYYMM, new Date());
						String tempPath =    DocumentDirName.FEED_BACK_INFO.getStatusValue() + "/" + yyyyMM + "/"
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
							if(i == 0){
								entity.setImgOne(minFilePath);
							}else if(i == 1){
								entity.setImgTwo(minFilePath);
							}else if(i == 2){
								entity.setImgThree(minFilePath);
							}
							// 缩略图
							File minFile = new File(path + tempFile + "_min." + suffix);
							FileUtils.writeImgFile(file, minFile, Constant.MAX_IMG_FILE_WIDTH, Constant.MINI_IMG_FILE_HEIGHT);
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
 	
 	public void saveOrUpdate(ZmlSysOpinionFeedbackInfoEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//执行更新操作增强业务
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 新增操作增强业务
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlSysOpinionFeedbackInfoEntity t) throws Exception{
 	}
 	/**
	 * 更新操作增强业务
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlSysOpinionFeedbackInfoEntity t) throws Exception{
 	}
 	/**
	 * 删除操作增强业务
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlSysOpinionFeedbackInfoEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlSysOpinionFeedbackInfoEntity t){
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
		map.put("user_id", t.getUserId());
		map.put("title", t.getTitle());
		map.put("phone", t.getPhone());
		map.put("content", t.getContent());
		map.put("img_one", t.getImgOne());
		map.put("img_two", t.getImgTwo());
		map.put("img_three", t.getImgThree());
		return map;
	}
 	
 	/**
	 * 替换sql中的变量
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlSysOpinionFeedbackInfoEntity t){
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
 		sql  = sql.replace("#{user_id}",String.valueOf(t.getUserId()));
 		sql  = sql.replace("#{title}",String.valueOf(t.getTitle()));
 		sql  = sql.replace("#{phone}",String.valueOf(t.getPhone()));
 		sql  = sql.replace("#{content}",String.valueOf(t.getContent()));
 		sql  = sql.replace("#{img_one}",String.valueOf(t.getImgOne()));
 		sql  = sql.replace("#{img_two}",String.valueOf(t.getImgTwo()));
 		sql  = sql.replace("#{img_three}",String.valueOf(t.getImgThree()));
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
	public void saveOpinion(ZmlSysOpinionFeedbackInfoEntity entity, MultipartFile[] upload, String showPic)
			throws Exception {
		Map<String,String> map =UploadImage.MultiImages(upload,null,showPic);
		if(map!=null&& map.size()>0){
			String[] arr = showPic.split(",");
			for(int i=0;i<arr.length;i++){
				if("upload1".equals(arr[0])){
					entity.setImgOne(map.get(arr[i]));
				}
				else if("upload2".equals(arr[0])){
					entity.setImgTwo(map.get(arr[i]));
				}
				else{
					entity.setImgThree(map.get(arr[i]));
				}
			}
		}
		
		Serializable t = super.save(entity);
 		//执行新增操作增强业务
 		this.doAddBus(entity);
	}
}