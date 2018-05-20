package com.jce.framework.web.demo.service.task;

import com.zml.base.weixin.entity.WeixinAccountEntity;

public interface WeiXinAccountTokenTask {
	public void autoResetToken();
	//从设置微信账号信息 
	public void reSetWeiXinToken(WeixinAccountEntity account);
}
