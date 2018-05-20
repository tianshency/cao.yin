package weixin.idea.huodong.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jce.framework.core.common.service.impl.CommonServiceImpl;

import weixin.idea.huodong.service.HuodongServiceI;

@Service("huodongService")
@Transactional
public class HuodongServiceImpl extends CommonServiceImpl implements HuodongServiceI {
	
}