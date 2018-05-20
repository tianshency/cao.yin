package com.zml.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.exception.BusinessException;
import com.jce.framework.core.common.model.json.DataGrid;
import com.jce.framework.core.common.service.impl.CommonServiceImpl;
import com.jce.framework.core.util.MyBeanUtils;
import com.jce.framework.core.util.StringUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.zml.base.entity.ZmlCommodityDocEntity;
import com.zml.base.entity.ZmlCommodityEntity;
import com.zml.base.entity.ZmlCommodityStandardEntity;
import com.zml.service.ZmlCommodityServiceI;
import com.zml.util.DbToPage;
import com.zml.util.IMyDataExchanger;
import com.zml.util.data_exchanger.MyDataExchangerAmountUnit;
import com.zml.util.data_exchanger.MyDataExchangerPovUnit;

import net.sf.json.JSONObject;


@Service("zmlCommodityService")
@Transactional
public class ZmlCommodityServiceImpl extends CommonServiceImpl implements ZmlCommodityServiceI {
	
 	public <T> void delete(T entity) {
 		super.delete(entity);
 		//鎵ц鍒犻櫎鎿嶄綔閰嶇疆鐨剆ql澧炲己
		this.doDelSql((ZmlCommodityEntity)entity);
 	}
	
	public void addMain(ZmlCommodityEntity zmlCommodity,
	        List<ZmlCommodityDocEntity> zmlCommodityDocList,List<ZmlCommodityStandardEntity> zmlCommodityStandardList){
			//鑾峰彇鍟嗘埛瀵瑰簲鏈烘瀯淇℃伅
			
			//淇濆瓨涓讳俊鎭�
			zmlCommodity.setReserveAmount(zmlCommodity.getTotalAmount());
			this.save(zmlCommodity);
			
			zmlCommodity.setMerchantsId(zmlCommodity.getSysCompanyCode());
			//zmlCommodity.setManufacturers(zmlCommodity.getsys);
			
			/**淇濆瓨-鍟嗗搧鏂囨。*/
			for(ZmlCommodityDocEntity zmlCommodityDoc:zmlCommodityDocList){
				//澶栭敭璁剧疆
				zmlCommodityDoc.setCommodityId(zmlCommodity.getId());
				this.save(zmlCommodityDoc);
			}
			//鏇存柊鍟嗗搧灏侀潰鍥剧墖
			if(zmlCommodityDocList != null && zmlCommodityDocList.size() > 0){
				ZmlCommodityDocEntity zmlCommodityDoc = zmlCommodityDocList.get(0);
				zmlCommodity.setCoverImg(zmlCommodityDoc.getFilePath());
			}
			/**淇濆瓨-閫傜敤鏍囧噯*/
			for(ZmlCommodityStandardEntity zmlCommodityStandard:zmlCommodityStandardList){
				//澶栭敭璁剧疆
				zmlCommodityStandard.setCommodityId(zmlCommodity.getId());
				this.save(zmlCommodityStandard);
			}
			//鎵ц鏂板鎿嶄綔閰嶇疆鐨剆ql澧炲己
 			this.doAddSql(zmlCommodity);
	}

	
	public void updateMain(ZmlCommodityEntity zmlCommodity,
	        List<ZmlCommodityDocEntity> zmlCommodityDocList,List<ZmlCommodityStandardEntity> zmlCommodityStandardList) {
		//淇濆瓨涓昏〃淇℃伅
		this.saveOrUpdate(zmlCommodity);
		//===================================================================================
		//鑾峰彇鍙傛暟
		Object id0 = zmlCommodity.getId();
		Object id1 = zmlCommodity.getId();
		//===================================================================================
		//1.鏌ヨ鍑烘暟鎹簱鐨勬槑缁嗘暟鎹�-鍟嗗搧鏂囨。
	    String hql0 = "from ZmlCommodityDocEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    List<ZmlCommodityDocEntity> zmlCommodityDocOldList = this.findHql(hql0,id0);
		//2.绛涢�夋洿鏂版槑缁嗘暟鎹�-鍟嗗搧鏂囨。
		if(zmlCommodityDocList!=null&&zmlCommodityDocList.size()>0){
		for(ZmlCommodityDocEntity oldE:zmlCommodityDocOldList){
			boolean isUpdate = false;
				for(ZmlCommodityDocEntity sendE:zmlCommodityDocList){
					//闇�瑕佹洿鏂扮殑鏄庣粏鏁版嵁-鍟嗗搧鏂囨。
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//濡傛灉鏁版嵁搴撳瓨鍦ㄧ殑鏄庣粏锛屽墠鍙版病鏈変紶閫掕繃鏉ュ垯鏄垹闄�-鍟嗗搧鏂囨。
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.鎸佷箙鍖栨柊澧炵殑鏁版嵁-鍟嗗搧鏂囨。
			for(ZmlCommodityDocEntity zmlCommodityDoc:zmlCommodityDocList){
				if(oConvertUtils.isEmpty(zmlCommodityDoc.getId())){
					//澶栭敭璁剧疆
					zmlCommodityDoc.setCommodityId(zmlCommodity.getId());
					this.save(zmlCommodityDoc);
				}
			}
		}
		//===================================================================================
		//1.鏌ヨ鍑烘暟鎹簱鐨勬槑缁嗘暟鎹�-閫傜敤鏍囧噯
	    String hql1 = "from ZmlCommodityStandardEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    List<ZmlCommodityStandardEntity> zmlCommodityStandardOldList = this.findHql(hql1,id1);
		//2.绛涢�夋洿鏂版槑缁嗘暟鎹�-閫傜敤鏍囧噯
		if(zmlCommodityStandardList!=null&&zmlCommodityStandardList.size()>0){
		for(ZmlCommodityStandardEntity oldE:zmlCommodityStandardOldList){
			boolean isUpdate = false;
				for(ZmlCommodityStandardEntity sendE:zmlCommodityStandardList){
					//闇�瑕佹洿鏂扮殑鏄庣粏鏁版嵁-閫傜敤鏍囧噯
					if(oldE.getId().equals(sendE.getId())){
		    			try {
							MyBeanUtils.copyBeanNotNull2Bean(sendE,oldE);
							this.saveOrUpdate(oldE);
						} catch (Exception e) {
							e.printStackTrace();
							throw new BusinessException(e.getMessage());
						}
						isUpdate= true;
		    			break;
		    		}
		    	}
	    		if(!isUpdate){
		    		//濡傛灉鏁版嵁搴撳瓨鍦ㄧ殑鏄庣粏锛屽墠鍙版病鏈変紶閫掕繃鏉ュ垯鏄垹闄�-閫傜敤鏍囧噯
		    		super.delete(oldE);
	    		}
	    		
			}
			//3.鎸佷箙鍖栨柊澧炵殑鏁版嵁-閫傜敤鏍囧噯
			for(ZmlCommodityStandardEntity zmlCommodityStandard:zmlCommodityStandardList){
				if(oConvertUtils.isEmpty(zmlCommodityStandard.getId())){
					//澶栭敭璁剧疆
					zmlCommodityStandard.setCommodityId(zmlCommodity.getId());
					this.save(zmlCommodityStandard);
				}
			}
		}
		//鎵ц鏇存柊鎿嶄綔閰嶇疆鐨剆ql澧炲己
 		this.doUpdateSql(zmlCommodity);
	}

	
	public void delMain(ZmlCommodityEntity zmlCommodity) {
		//鍒犻櫎涓昏〃淇℃伅
		this.delete(zmlCommodity);
		//===================================================================================
		//鑾峰彇鍙傛暟
		Object id0 = zmlCommodity.getId();
		Object id1 = zmlCommodity.getId();
		//===================================================================================
		//鍒犻櫎-鍟嗗搧鏂囨。
	    String hql0 = "from ZmlCommodityDocEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    List<ZmlCommodityDocEntity> zmlCommodityDocOldList = this.findHql(hql0,id0);
		this.deleteAllEntitie(zmlCommodityDocOldList);
		//===================================================================================
		//鍒犻櫎-閫傜敤鏍囧噯
	    String hql1 = "from ZmlCommodityStandardEntity where 1 = 1 AND cOMMODITY_ID = ? ";
	    List<ZmlCommodityStandardEntity> zmlCommodityStandardOldList = this.findHql(hql1,id1);
		this.deleteAllEntitie(zmlCommodityStandardOldList);
	}
	
 	
 	/**
	 * 榛樿鎸夐挳-sql澧炲己-鏂板鎿嶄綔
	 * @param id
	 * @return
	 */
 	public boolean doAddSql(ZmlCommodityEntity t){
	 	return true;
 	}
 	/**
	 * 榛樿鎸夐挳-sql澧炲己-鏇存柊鎿嶄綔
	 * @param id
	 * @return
	 */
 	public boolean doUpdateSql(ZmlCommodityEntity t){
	 	return true;
 	}
 	/**
	 * 榛樿鎸夐挳-sql澧炲己-鍒犻櫎鎿嶄綔
	 * @param id
	 * @return
	 */
 	public boolean doDelSql(ZmlCommodityEntity t){
	 	return true;
 	}
 	
 	/**
	 * 鏇挎崲sql涓殑鍙橀噺
	 * @param sql
	 * @return
	 */
 	public String replaceVal(String sql,ZmlCommodityEntity t){
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
 		sql  = sql.replace("#{merchants_id}",String.valueOf(t.getMerchantsId()));
 		sql  = sql.replace("#{manufacturers}",String.valueOf(t.getManufacturers()));
 		sql  = sql.replace("#{name}",String.valueOf(t.getName()));
 		sql  = sql.replace("#{batch_number}",String.valueOf(t.getBatchNumber()));
 		sql  = sql.replace("#{introduction}",String.valueOf(t.getIntroduction()));
 		sql  = sql.replace("#{total_amount}",String.valueOf(t.getTotalAmount()));
 		sql  = sql.replace("#{amount_unit}",String.valueOf(t.getAmountUnit()));
 		sql  = sql.replace("#{commercial_specification}",String.valueOf(t.getCommercialSpecification()));
 		sql  = sql.replace("#{reserve_amount}",String.valueOf(t.getReserveAmount()));
 		sql  = sql.replace("#{price}",String.valueOf(t.getPrice()));
 		sql  = sql.replace("#{real_price}",String.valueOf(t.getRealPrice()));
 		sql  = sql.replace("#{production_date}",String.valueOf(t.getProductionDate()));
 		sql  = sql.replace("#{period_of_validity}",String.valueOf(t.getPeriodOfValidity()));
 		sql  = sql.replace("#{pov_unit}",String.valueOf(t.getPovUnit()));
 		sql  = sql.replace("#{is_recommend}",String.valueOf(t.getIsRecommend()));
 		sql  = sql.replace("#{is_hot}",String.valueOf(t.getIsHot()));
 		sql  = sql.replace("#{classify_one_level}",String.valueOf(t.getClassifyOneLevel()));
 		sql  = sql.replace("#{classify_two_level}",String.valueOf(t.getClassifyTwoLevel()));
 		sql  = sql.replace("#{details}",String.valueOf(t.getDetails()));
 		sql  = sql.replace("#{status}",String.valueOf(t.getStatus()));
 		sql  = sql.replace("#{fare}",String.valueOf(t.getFare()));
 		sql  = sql.replace("#{UUID}",UUID.randomUUID().toString());
 		return sql;
 	}

	@Override
	public List<Object> findRecommendList() {
		Session session = getSession(); 
	    String hql = "select id,name,price,realPrice,createDate from ZmlCommodityEntity u where u.isRecommend='1' order by createDate desc limit 8 "; 
	    Query query = session.createQuery(hql); 
	    List list = query.list(); 
	    System.out.println("------------SQL鎵ц瀹屾瘯---------------"); 
	    List reList = new ArrayList();
	    Map tempMap = null;
	    if(list != null && list.size() >0){
	    	for(Object obj : list){
		    	tempMap = new HashMap();
		        Object[] arrObj = (Object[])obj; 
		        tempMap.put("id", arrObj[0]);
		        tempMap.put("name", arrObj[1]);
		        tempMap.put("price", arrObj[2]);
		        tempMap.put("realPrice", arrObj[3]);
		        //entity.setCreateDate((Date)arrObj[4]);
		        //System.out.println(arrObj[0] + "/t-->/t" + arrObj[1]); 
		        reList.add(tempMap);
		    }
	    	tempMap = null;
	    	list = null;
	    }
		return reList;
	}

	@Override
	public List<Object> findByClassifyList(String classifyId) {
		Session session = getSession(); 
	    String hql = "select id,name,price,realPrice,createDate from ZmlCommodityEntity u where u.classifyOneLevel=:classifyOneLevel order by createDate desc limit  "; 
	    Query query = session.createQuery(hql); 
	    query.setParameter("classifyOneLevel", classifyId);
	    List list = query.list(); 
	    System.out.println("------------SQL鎵ц瀹屾瘯---------------"); 
	    /*for(Object obj : list){ 
	        Object[] arrObj = (Object[])obj; 
	        System.out.println(arrObj[0] + "/t-->/t" + arrObj[1]); 
	    } */
		return list;
	}
	
	/**
	 * 分页 带排序查询
	 * orders.put("salesNum",request.getParameter("salesNum"));
			orders.put("salesPrice", request.getParameter("salesPrice"));
			orders.put("commodityType", request.getParameter("commodityType"));
	 */
	
	public List<Map<String, Object>> getDatagrid(ZmlCommodityEntity pageObj, DataGrid dataGrid,Map<String,Object> orders) {
		String sqlWhere = getSqlWhere(pageObj);
		
		// 鍙栧嚭鎬绘暟鎹潯鏁帮紙涓轰簡鍒嗛〉澶勭悊, 濡傛灉涓嶇敤鍒嗛〉锛屽彇iCount鍊肩殑杩欎釜澶勭悊鍙互涓嶈锛�
		String sqlCnt = "select count(1) from zml_commodity t";
		if (!sqlWhere.isEmpty()) {
			sqlCnt += " where" + sqlWhere;
		}
		Long iCount = getCountForJdbcParam(sqlCnt, null);
		
		// 鍙栧嚭褰撳墠椤电殑鏁版嵁 
		StringBuffer sql = new StringBuffer("select t.id, t.name, t.manufacturers,t.total_amount,t.reserve_amount,t.price,t.real_price,t.amount_unit,t.production_date,t.period_of_validity,t.pov_unit,t.fare"); 
		sql.append(" from zml_commodity t");
		boolean whereFlag=false;
		if (!sqlWhere.isEmpty()) {
			sql.append(" where" + sqlWhere);
			whereFlag=true;
		}
		//3.按商品类型
		//已经有where条件 
		if(orders.get("commodityType")!=null && !"".equals(orders.get("commodityType")) &&whereFlag){
			sql.append(" and classify_one_level in(").append(orders.get("commodityType")+")");
		} 
		//无where 条件
		else if(orders.get("commodityType")!=null&& !"".equals(orders.get("commodityType")) && !whereFlag){
			sql.append(" where classify_one_level in(").append(orders.get("commodityType")+")");
			whereFlag=true;
		}
		
		if(orders.get("queryValue")!=null && !"".equals(orders.get("queryValue")) && whereFlag){
			sql.append(" name like '%").append(orders.get("queryValue")).append("%'");
			sql.append(" and introduction like '%").append(orders.get("queryValue")).append("%'");
		}
		else if(orders.get("queryValue")!=null && !"".equals(orders.get("queryValue")) &&  !whereFlag){
			sql.append(" where name like '%").append(orders.get("queryValue")).append("%'");
			sql.append(" and introduction like '%").append(orders.get("queryValue")).append("%'");
		}
		
		//处理排序
		//1.按销量
		if(orders.get("salesNum")!=null){
			
		}
		//2.按价格
		else if (orders.get("salesPrice")!=null){
			sql.append(" order by real_price ").append(orders.get("salesPrice"));
		}
		
		List<Map<String, Object>> mapList = findForJdbc(sql.toString(), dataGrid.getPage(), dataGrid.getRows());
		dataGrid.setTotal(iCount.intValue());
		return mapList;
		// 灏嗙粨鏋滈泦杞崲鎴愰〉闈笂瀵瑰簲鐨勬暟鎹泦
		/*DbToPage[] dbToPages = {
				new DbToPage("id")
				,new DbToPage("name", "name", null)
				,new DbToPage("manufacturers", "manufacturers", null)
				,new DbToPage("totalAmount", "total_amount", null)
				,new DbToPage("reserveAmount", "reserve_amount", null)
				,new DbToPage("price")
				,new DbToPage("realPrice")
				,new DbToPage("amountUnit", null, new MyDataExchangerAmountUnit())
				,new DbToPage("productionDate", "total_amount", null)//鐢熶骇鏃ユ湡
				,new DbToPage("periodOfValidity", "perio_of_validity", null)//鏈夋晥鏈�
				,new DbToPage("povUnit", null, new MyDataExchangerPovUnit())//鏈夋晥鏈熷崟浣�
				,new DbToPage("fare")
		};
		JSONObject jObject = getJsonDatagridEasyUI(mapList, iCount.intValue(), dbToPages);
		return jObject;*/
		// end of 鏂瑰紡3 ========================================= */
	}
	
	// 鎷兼煡璇㈡潯浠讹紙where璇彞锛�
	String getSqlWhere(ZmlCommodityEntity pageObj) {
		// 鎷煎嚭鏉′欢璇彞
		String sqlWhere = "";
		if (StringUtil.isNotEmpty(pageObj.getClassifyOneLevel())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.classify_one_level = '" + pageObj.getClassifyOneLevel() + "'";
		}
		
		if (StringUtil.isNotEmpty(pageObj.getClassifyTwoLevel())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.classify_two_level = '" + pageObj.getClassifyTwoLevel() + "'";
		}
		
		if (StringUtil.isNotEmpty(pageObj.getName())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.name like '%" + pageObj.getName() + "%'";
		}
		if (StringUtil.isNotEmpty(pageObj.getDetails())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.details like '%" + pageObj.getDetails() + "%'";
		}
		if (StringUtil.isNotEmpty(pageObj.getMerchantsId())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.merchants_id = '" + pageObj.getMerchantsId() + "'";
		}
		if (StringUtil.isNotEmpty(pageObj.getBatchNumber())) {
			if (!sqlWhere.isEmpty()) {
				sqlWhere += " and";
			}
			sqlWhere += " t.batch_number = '" + pageObj.getBatchNumber() + "'";
		}
		
		return sqlWhere;
	}
	// 浠ヤ笅鍚勫嚱鏁板彲浠ユ彁鎴愬叡鐢ㄩ儴浠� (Add by Quainty)
	/**
	 * 杩斿洖easyUI鐨凞ataGrid鏁版嵁鏍煎紡鐨凧SONObject瀵硅薄
	 * @param mapList : 浠庢暟鎹簱鐩存帴鍙栧緱鐨勭粨鏋滈泦鍒楄〃
	 * @param iTotalCnt : 浠庢暟鎹簱鐩存帴鍙栧緱鐨勭粨鏋滈泦鎬绘暟鎹潯鏁�
	 * @param dataExchanger : 椤甸潰琛ㄧず鏁版嵁涓庢暟鎹簱瀛楁鐨勫搴斿叧绯诲垪琛�
	 * @return JSONObject
	 */
	public JSONObject getJsonDatagridEasyUI(List<Map<String, Object>> mapList, int iTotalCnt, DbToPage[] dataExchanger) {
		//easyUI鐨刣ataGrid鏂瑰紡  锛嶏紞锛嶏紞杩欓儴鍒嗗彲浠ユ彁鍙栨垚缁熶竴澶勭悊
		String jsonTemp = "{\'total\':" + iTotalCnt + ",\'rows\':[";
		for (int j = 0; j < mapList.size(); j++) {
			Map<String, Object> m = mapList.get(j);
			if (j > 0) {
				jsonTemp += ",";
			}
			jsonTemp += "{";
			for (int i = 0; i < dataExchanger.length; i++) {
				if (i > 0) {
					jsonTemp += ",";
				}
				jsonTemp += "'" + dataExchanger[i].getKey() + "'" + ":";
				Object objValue = dataExchanger[i].getData(m);
				if (objValue == null) {
					jsonTemp += "null";
				} else {
					jsonTemp += "'" + objValue + "'";
				}
			}
			jsonTemp += "}";
		}
		jsonTemp += "]}";
		JSONObject jObject = JSONObject.fromObject(jsonTemp);
		return jObject;
	}

	@Override
	public List<Map<String, Object>> commodityStandardList(String commodityId) {
		String sql = "select ct.name, t.dosage_start,t.dosage_end,t.dosage_unit"
	    +" from zml_commodity_standard t LEFT JOIN zml_crop_type ct on t.corp_id = ct.id "
	    +" where commodity_id ='"+ commodityId +"'";
		
		List<Map<String, Object>> mapList = findForJdbc(sql, null);
		return mapList;
	}
	
}