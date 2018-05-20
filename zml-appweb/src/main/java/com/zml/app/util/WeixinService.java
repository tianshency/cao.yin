package com.zml.app.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.zml.app.robot.TulingApiProcess;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.service.ZmlUserServiceI;

import net.sf.json.JSONObject;

public class WeixinService{
	/**
	 * Logger for this class
	 */
	private static final Logger log = Logger.getLogger(WeixinService.class);

	public static String processRequest(HttpServletRequest request,ZmlUserServiceI zmlUserService){
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "欢迎关注‘助民乐’服务平台！";
			System.out.println("respContent=============="+respContent);
			// xml请求解析
			// 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里；
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			log.info("发送来的信息："+requestMap.toString());
			
			// 从HashMap中取出消息中的字段；
			// 1.发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 2.公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 3.消息类型
			String msgType = requestMap.get("MsgType");
			// 4.消息内容
			String content = requestMap.get("Content");
			
			log.info("fromUserName is:" +fromUserName+" toUserName is:" +toUserName+" msgType is:" +msgType);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				//微信聊天机器人测试 2015-3-31
				if(content!=null){
					respContent = TulingApiProcess.getTulingResult(content);
					if(respContent.equals("")||null==respContent){
						MessageResponse.getTextMessage(fromUserName , toUserName , "服务号暂时无法回复，请稍后再试！");
					}
					return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
				}
			} 
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
				String eventType = requestMap.get("Event");// 事件类型
				
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
					//调用保存用户openId的方法
					saveWeixinUserInfo(zmlUserService,fromUserName);
					request.getSession().setAttribute("openId", fromUserName);
					log.info("推送用户访问信息！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
					return MessageResponse.getTextMessage(fromUserName , toUserName , respContent);
				} 
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				
				} 
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件
					String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					log.info("eventKey is:" +eventKey);
					return "redirect: /signal/signalList";
				}
			}
			
			
			//开启微信声音识别测试 2015-3-30
			else if(msgType.equals("voice"))
			{
				String recvMessage = requestMap.get("Recognition");
				//respContent = "收到的语音解析结果："+recvMessage;
				if(recvMessage!=null){
					respContent = TulingApiProcess.getTulingResult(recvMessage);
				}else{
					respContent = "您说的太模糊了，能不能重新说下呢？";
				}
				return MessageResponse.getTextMessage(fromUserName , toUserName , respContent); 
			}
			
			//拍照功能
			else if(msgType.equals("pic_sysphoto"))
			{
				
			}
			else
			{
				return MessageResponse.getTextMessage(fromUserName , toUserName , "返回为空"); 
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
	
	/**
	 * 微信获取用户信息
	 * 获取用户信息 </br>
	 * @param accessToken 接口访问凭证 </br>
	 * @param openId 用户标识 </br>
	 * @return WeixinUserInfo </br>
	 */
	public static  ZmlUserEntity getUserInfo(String accessToken, String openId) {
		ZmlUserEntity zmlUser = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);
		if (null != jsonObject) {
			log.info("jsonObject 获取用户基本信息：--------------------"+jsonObject);
			try {
				zmlUser = new ZmlUserEntity();
				// 用户的标识
				zmlUser.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				zmlUser.setSubscribe(jsonObject.getInt("subscribe"));
				log.info("jsonObject.getInt(subscribe)：--------------------"+jsonObject.getInt("subscribe"));
				// 用户关注时间
				 SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				    Long time=new Long(jsonObject.getString("subscribe_time"));  
				    String d = format.format(time);  
				    Date date=format.parse(d);  
				zmlUser.setCreateDate(date);
				log.info("jsonObject.getInt(subscribe_time)：--------------------"+jsonObject.getString("subscribe_time"));
				// 昵称
				zmlUser.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				zmlUser.setSex(jsonObject.getInt("sex")+"");
				//用户所在国家
				//zmlUser.setCountry(jsonObject.getString("country"));
				//用户所在省份
				//zmlUser.setProvince(jsonObject.getString("province"));
				//用户所在城市
				//zmlUser.setCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				//zmlUser.setLanguage(jsonObject.getString("language"));
				// 用户头像
				zmlUser.setAvatar(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == zmlUser.getSubscribe()) {
					log.error("用户"+ zmlUser.getOpenId()+"已取消关注");
				} else {
					log.error("获取用户信息失败 errcode:"+jsonObject.toString());
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:"+errorCode+" errmsg:"+errorMsg);
					
				}
			}
		}
		return zmlUser;
	}
	
	/**
	 * 修改用户信息
	 */
	public static void updateUserInfo(ZmlUserServiceI zmlUserService,ZmlUserEntity zmlUser,String accessToken, String openId){
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl,"GET", null);
		
		// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
		zmlUser.setSubscribe(jsonObject.getInt("subscribe"));
		// 用户关注时间
		zmlUser.setCreateDate(new Date(jsonObject.getString("subscribe_time")));
		// 昵称
		zmlUser.setNickname(jsonObject.getString("nickname"));
		// 用户的性别（1是男性，2是女性，0是未知）
		zmlUser.setSex(jsonObject.getInt("sex")+"");
		//用户所在国家
		//zmlUser.setCountry(jsonObject.getString("country"));
		//用户所在省份
		//zmlUser.setProvince(jsonObject.getString("province"));
		//用户所在城市
		//zmlUser.setCity(jsonObject.getString("city"));
		// 用户的语言，简体中文为zh_CN
		//zmlUser.setLanguage(jsonObject.getString("language"));
		// 用户头像
		zmlUser.setAvatar(jsonObject.getString("headimgurl"));
		zmlUserService.saveOrUpdate(zmlUser);
		
	}
	
	
	/**
	 * 保存用户信息
	 * @param zmlUser
	 * @param openId
	 * @throws IOException
	 */
	public static void saveWeixinUserInfo(ZmlUserServiceI zmlUserService,String openId) throws IOException{
		// 1.获取接口访问凭证
		AccessToken accessToken = WeixinUtil.getAccessToken(Constants.APPID, Constants.SECRET);
		
		//判断用户是否已经关注过公众号了
		ZmlUserEntity zmlUser =  zmlUserService.findUserByOpenId(openId);
		if(zmlUser!=null){
			//修改用户信息
			updateUserInfo(zmlUserService,zmlUser,accessToken.getAccessToken(),openId);
			return;
		}
		
		
		// 2.获取用户基本信息
		log.info("获取用户基本信息");
		ZmlUserEntity userInfo = WeixinService.getUserInfo(accessToken.getAccessToken(), openId);
		log.info("保存用户基本信息");
		userInfo = (ZmlUserEntity) zmlUserService.save(userInfo);
		log.info("保存用户基本信息===="+userInfo.getId());
		
	}

}
