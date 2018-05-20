package com.jce.framework.web.demo.service.impl.test;

import com.jce.framework.core.common.dao.impl.CommonDao;
import com.jce.framework.core.common.dao.jdbc.JdbcDao;
import com.jce.framework.web.demo.dao.test.JeecgMinidaoDao;
import com.jce.framework.web.demo.entity.test.JeecgDemo;
import com.jce.framework.web.demo.entity.test.JeecgMinidaoEntity;
import com.jce.framework.web.demo.entity.test.JeecgOrderCustomEntity;
import com.jce.framework.web.demo.service.test.TransactionTestServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/6.
 */
@Service("transactionTestService")
@Transactional(rollbackFor = Exception.class)
public class TransactionTestServiceImpl  implements TransactionTestServiceI {
    @Autowired
    private JeecgMinidaoDao jeecgMinidaoDao;
    @Autowired
    private CommonDao commonDao;
    @Autowired
    private JdbcDao jdbcDao;
    @Override
    public Map<String,Integer> getCounts() {
        Map<String,Integer> counts=new HashMap<String, Integer>();
        counts.put("minidao",jeecgMinidaoDao.getCount());
        counts.put("jdbc",(int) jdbcDao.findForLong("select count(*) from jeecg_demo", new HashMap()));
        Long count=commonDao.getCountForJdbc("select count(*) from jeecg_order_custom");
        if(count!=null){
            counts.put("hibernate",count.intValue());
        }else {
            counts.put("hibernate",0);
        }
        return counts;
    }

    @Override
    public Map<String,Integer> insertData(JeecgMinidaoEntity entity, JeecgDemo demo, JeecgOrderCustomEntity customEntity, boolean rollback)throws Exception{
        Map<String,Integer> counts = new HashMap<String, Integer>();
        entity.setUserName("test");
        //jeecgMinidaoDao.saveByHiber(entity);
        counts.put("minidao",1);
        demo.setId(new Date().getTime()+"");
        counts.put("jdbc",jdbcDao.executeForObject("insert into jeecg_demo (ID,MOBILE_PHONE,OFFICE_PHONE,EMAIL,AGE,USER_NAME) values(:id,:mobilePhone,:officePhone,:email,:age,'test')", demo));
        counts.put("hibernate",commonDao.save(customEntity)==null?0:1);
        if(rollback){
            throw new RuntimeException();
        }
        return counts;
    }
}
