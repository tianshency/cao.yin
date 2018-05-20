package com.zml.service.impl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.ApplicationContextUtil;
import com.jce.framework.core.util.MyClassLoader;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.web.cgform.enhance.CgformEnhanceJavaInter;
import com.zml.base.entity.ZmlCropTypeEntity;
import com.zml.service.ZmlCropTypeServiceI;

@Service("zmlCropTypeService")
@Transactional
public class ZmlCropTypeServiceImpl extends CommonServiceImpl implements ZmlCropTypeServiceI {

	
 	public void delete(ZmlCropTypeEntity entity) throws Exception{
 		super.delete(entity);
 		//鎵ц鍒犻櫎鎿嶄綔澧炲己涓氬姟
		this.doDelBus(entity);
 	}
 	
 	public Serializable save(ZmlCropTypeEntity entity) throws Exception{
 		Serializable t = super.save(entity);
 		//鎵ц鏂板鎿嶄綔澧炲己涓氬姟
 		this.doAddBus(entity);
 		return t;
 	}
 	
 	public void saveOrUpdate(ZmlCropTypeEntity entity) throws Exception{
 		super.saveOrUpdate(entity);
 		//鎵ц鏇存柊鎿嶄綔澧炲己涓氬姟
 		this.doUpdateBus(entity);
 	}
 	
 	/**
	 * 鏂板鎿嶄綔澧炲己涓氬姟
	 * @param t
	 * @return
	 */
	private void doAddBus(ZmlCropTypeEntity t) throws Exception{
 	}
 	/**
	 * 鏇存柊鎿嶄綔澧炲己涓氬姟
	 * @param t
	 * @return
	 */
	private void doUpdateBus(ZmlCropTypeEntity t) throws Exception{
 	}
 	/**
	 * 鍒犻櫎鎿嶄綔澧炲己涓氬姟
	 * @param id
	 * @return
	 */
	private void doDelBus(ZmlCropTypeEntity t) throws Exception{
 	}
 	
 	private Map<String,Object> populationMap(ZmlCropTypeEntity t){
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
		map.put("name", t.getName());
		map.put("logo", t.getLogo());
		map.put("description", t.getDescription());
		return map;
	}
 	
 	/**
	 * 鏇挎崲sql涓殑鍙橀噺
	 * @param sql
	 * @param t
	 * @return
	 */
 	public String replaceVal(String sql,ZmlCropTypeEntity t){
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
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{logo}",String.valueOf(t.getLogo()));
 		sql  = sql.replace("#{description}",String.valueOf(t.getDescription()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}
 	
 	/**
	 * 鎵цJAVA澧炲己
	 */
 	private void executeJavaExtend(String cgJavaType,String cgJavaValue,Map<String,Object> data) throws Exception {
 		if(StringUtil.isNotEmpty(cgJavaValue)){
			Object obj = null;
			try {
				if("class".equals(cgJavaType)){
					//鍥犳柊澧炴椂宸茬粡鏍￠獙浜嗗疄渚嬪寲鏄惁鍙互鎴愬姛锛屾墍浠ヨ繖鍧楀氨涓嶉渶瑕佸啀鍋氫竴娆″垽鏂�
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
				throw new Exception("鎵цJAVA澧炲己鍑虹幇寮傚父锛�");
			} 
		}
 	}

	@Override
	public List<Object> findIndexList(int num) {
		Session session = getSession(); 
	    String hql = "select id,name,logo from ZmlCropTypeEntity u  order by createDate desc limit " + num; 
	    Query query = session.createQuery(hql); 
	    List<Object[]> list = query.list(); 
	    System.out.println("------------SQL鎵ц瀹屾瘯---------------"); 
	    List reList = null;
	    if(list != null && list.size() > 0){
	    	reList = new ArrayList();
	    	Map temp = null;
	    	for(Object[] objs : list){
	    		temp = new HashMap();
	    		temp.put("id", objs[0]);
	    		temp.put("name", objs[1]);
	    		temp.put("logo", objs[2]);
	    		reList.add(temp);
	    	}
	    	temp = null;
	    }
	    list = null;
		return reList;
	}
	
	
}