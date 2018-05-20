package com.jce.framework.web.demo.service.impl.task;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.LogUtil;
import com.jce.framework.web.demo.service.task.WeiXinAccountTokenTask;
import com.jce.framework.web.system.service.SystemService;
import com.jce.framework.weixin.api.coupon.qrcode.JwQrcodeAPI;
import com.jce.framework.weixin.api.coupon.qrcode.model.GetticketRtn;
import com.jce.framework.weixin.util.WeiXinOpenConstants;
import com.jce.framework.weixin.util.WeixinUtil;
import com.zml.base.weixin.entity.WeixinAccountEntity;
import com.zml.cache.RedisUtilTool;
import com.zml.common.WeiXinConstants;
@Service("weixinAccountTokenTask")
public class WeiXinAccountTokenTaskImpl implements WeiXinAccountTokenTask {
	@Autowired
	private SystemService systemService;
	
	@Autowired
	public RedisUtilTool redisUtilTool;
	
	@Override
	public void autoResetToken() {
		//String currTime = "" + System.currentTimeMillis();
		//redisUtilTool.set(currTime, "val:" + currTime);
		//LogUtil.info(new Date().getTime());
		LogUtil.info("----weixinAccountTokenTask------任务开始执行-------");
		//String rsStr = (String)redisUtilTool.get(currTime);
		//System.out.println("rsStr*******************:" + rsStr);
		long start = System.currentTimeMillis();
		
		//提前半小时重置Token
		long currentTime = new Date().getTime() - 1000 * 3600 * 2 + 30*60*1000;
		String currentDatetime = DateUtils.date2Str(DateUtils.getDate(currentTime), DateUtils.datetimeFormat);
		String hql = "from WeixinAccountEntity where addtoekntime < '"+currentDatetime+"'";
		List<WeixinAccountEntity> list = systemService.findHql(hql);
		
		LogUtil.info("===================定时任务【重置超过2小时失效token】开始===================");
		
		for(WeixinAccountEntity account : list){
			try {
				restWeiXinToken(account);
			} catch (Exception e) {
				LogUtil.info("---------定时任务【重置超过2小时失效token】失败公众号------------------"+account.getAccountname());
			}
		}
		long end = System.currentTimeMillis();
		long times = end - start;
		LogUtil.info("====================定时任务【重置超过2小时失效token】结束，" + "====执行重置公众号数量："+ (list!=null?list.size():0) +" ========总耗时"+times+"毫秒==========");
	    
	}

	/**
	 * 重置restWeiXinToken
	 * @param account
	 */
	private void restWeiXinToken(WeixinAccountEntity account){
		String token = null;
		// 失效 重新获取
		String requestUrl = WeixinUtil.access_token_url.replace("APPID", account.getAccountappid()).replace("APPSECRET", account.getAccountappsecret());
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);
		Date getAccessTokenDate = new Date();
		if (null != jsonObject) {
			try {
				//[1].获取token凭证
				token = jsonObject.getString("access_token");
				// 重置token
				account.setAccountaccesstoken(token);
				// 重置获取token时间
				account.setAddtoekntime(getAccessTokenDate);
					//------------------------------------------------------------------------------------------------
					try {
						//[2].获取api凭证
						GetticketRtn getticketRtn = JwQrcodeAPI.doGetticket(token);
						if (null != getticketRtn) {
							try {
								// 重置token
								account.setApiticket(getticketRtn.getTicket());
								// 重置事件
								account.setApiticketttime(getAccessTokenDate);
								LogUtil.info("---------定时任务重置超过2小时失效token------------------"+"获取Apiticket成功");
							} catch (Exception e) {
								// 获取api凭证失败
								String wrongMessage = "获取api凭证失败 errcode:{"+ getticketRtn.getErrcode()+"} errmsg:{"+getticketRtn.getErrmsg()+"}";
								LogUtil.info(wrongMessage);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
						LogUtil.info("---------------------定时任务异常--【获取api凭证】--------------"+e.toString());
					}
					//------------------------------------------------------------------------------------------------
					//[3].获取jsapi凭证
					try {
						String jsapiticket = null;
						String jsapi_ticket_url = WeiXinOpenConstants.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", token);
						JSONObject jsapi_ticket_json = WeixinUtil.httpRequest(jsapi_ticket_url, "GET", null);
						if (null != jsapi_ticket_json) {
							try {
								jsapiticket = jsapi_ticket_json.getString("ticket");
								// 重置token
								account.setJsapiticket(jsapiticket);
								// 重置事件
								account.setJsapitickettime(getAccessTokenDate);
								LogUtil.info("---------定时任务重置超过2小时失效token------------------"+"获取Jsapiticket成功");
							} catch (Exception e) {
								//获取jsapi凭证失败
								String wrongMessage = "获取jsapi凭证失败 errcode:{"+ (jsonObject.containsKey("errcode")?jsonObject.get("errcode"):"") +"} errmsg:{"+ (jsonObject.containsKey("errmsg")?jsonObject.getString("errmsg"):"") +"}";
								LogUtil.info(wrongMessage);
							}
						}
					} catch (Exception e) {
						LogUtil.info("---------------------定时任务异常--【获取jsapi凭证】--------------"+e.toString());
					}
					//------------------------------------------------------------------------------------------------
					//存入redis缓存
					redisUtilTool.set(WeiXinConstants.WEIXIN_ACCOUNT, account, WeiXinConstants.WEIXIN_ACCOUNT_TIME_OUT);
					systemService.saveOrUpdate(account);
				    LogUtil.info("---2------定时任务定时任务【重置超过2小时失效token】成功公众号------------------" + account.getAccountname());
				} catch (Exception e) {
					// 获取token失败
					String wrongMessage = "获取token失败 errcode:{"+ (jsonObject.containsKey("jsonObject")?jsonObject.get("errcode"):"")+"} errmsg:{"+ (jsonObject.containsKey("errmsg")?jsonObject.getString("errmsg"):"") +"}";
					LogUtil.info(wrongMessage);
					// 重置获取token时间【上次定时任务获取Token时间-失败保存】
					account.setAddtoekntime(getAccessTokenDate);
					// 重置获取token时间【上次定时任务获取Token时间-失败保存】
					account.setAddtoekntime(getAccessTokenDate);
					systemService.saveOrUpdate(account);
					LogUtil.info("---------定时任务定时任务【重置超过2小时失效token】失败保存公众号------------------" + account.getAccountname());
				}
			}
	}

	@Override
	public void reSetWeiXinToken(WeixinAccountEntity account) {
		try {
			restWeiXinToken(account);
		} catch (Exception e) {
			LogUtil.info("---------定时任务【重置超过2小时失效token】失败公众号------------------"+account.getAccountname());
		}
	}
	
	public static void main(String[] args) {
		long currentTime = new Date().getTime() - 1000 * 3600 * 2 + 30*60*1000;
		String currentDatetime = DateUtils.date2Str(DateUtils.getDate(currentTime), DateUtils.datetimeFormat);
		System.out.println(currentDatetime);
	}
}
