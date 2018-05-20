package weixin.guanjia.core.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jce.framework.core.online.util.FreemarkerHelper;
import com.jce.framework.core.util.LogUtil;
import com.jce.framework.core.util.oConvertUtils;
import com.jce.framework.web.system.service.SystemService;
import com.zml.base.entity.ZmlUserEntity;
import com.zml.base.loan.entity.ZmlUserRecordEntity;
import com.zml.base.weixin.entity.WeixinAccountEntity;
import com.zml.cache.RedisUtilTool;
import com.zml.common.WeiXinConstants;
import com.zml.common.Constant;
import com.zml.service.ZmlUserServiceI;

import net.sf.json.JSONObject;
import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.base.entity.Subscribe;
import weixin.guanjia.base.entity.WeixinExpandconfigEntity;
import weixin.guanjia.base.service.SubscribeServiceI;
import weixin.guanjia.base.service.WeixinExpandconfigServiceI;
import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.menu.entity.MenuEntity;
import weixin.guanjia.message.dao.TextTemplateDao;
import weixin.guanjia.message.entity.AutoResponse;
import weixin.guanjia.message.entity.NewsItem;
import weixin.guanjia.message.entity.NewsTemplate;
import weixin.guanjia.message.entity.ReceiveText;
import weixin.guanjia.message.entity.TextTemplate;
import weixin.guanjia.message.service.AutoResponseServiceI;
import weixin.guanjia.message.service.NewsItemServiceI;
import weixin.guanjia.message.service.NewsTemplateServiceI;
import weixin.guanjia.message.service.ReceiveTextServiceI;
import weixin.guanjia.message.service.TextTemplateServiceI;
import weixin.idea.extend.function.KeyServiceI;
import weixin.util.DateUtils;

@Service("wechatService")
public class WechatService {
	@Autowired
	private TextTemplateDao textTemplateDao;
	@Autowired
	private AutoResponseServiceI autoResponseService;
	@Autowired
	private TextTemplateServiceI textTemplateService;
	@Autowired
	private NewsTemplateServiceI newsTemplateService;
	@Autowired
	private ReceiveTextServiceI receiveTextService;
	@Autowired
	private NewsItemServiceI newsItemService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private SubscribeServiceI subscribeService;
	@Autowired
	private WeixinExpandconfigServiceI weixinExpandconfigService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;
	
	@Autowired
	private ZmlUserServiceI zmlUserService;
	
	@Autowired
	public RedisUtilTool redisUtilTool;

	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			String msgId = requestMap.get("MsgId");
			//消息内容
			String content = requestMap.get("Content");
			LogUtil.info("------------微信客户端发送请求---------------------   |   fromUserName:"+fromUserName+"   |   ToUserName:"+toUserName+"   |   msgType:"+msgType+"   |   msgId:"+msgId+"   |   content:"+content);
			//根据微信ID,获取配置的全局的数据权限ID
			LogUtil.info("-toUserName--------"+toUserName);
			String sys_accountId = weixinAccountService.findByToUsername(toUserName).getId();
			LogUtil.info("-sys_accountId--------"+sys_accountId);
			ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
			// 默认回复此文本消息
			TextMessageResp textMessage = new TextMessageResp();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setContent(getMainMenu());
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);
			LogUtil.info("respMessage==" + respMessage);
			//【微信触发类型】文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				LogUtil.info("------------微信客户端发送请求------------------【微信触发类型】文本消息---");
				respMessage = doTextResponse(content,toUserName,textMessage,bundler,
						sys_accountId,respMessage,fromUserName,request,msgId,msgType);
			}
			//【微信触发类型】图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "已收到您发送的是图片消息！";
				return respContent;
			}
			//【微信触发类型】地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "已收到您发送的是地理位置消息！";
				return respContent;
			}
			//【微信触发类型】链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "已收到您发送的是链接消息！";
				return respContent;
			}
			//【微信触发类型】音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "已收到您发送的是音频消息！";
				return respContent;
			}
			//【微信触发类型】事件推送  关注、取消关注、已关注 搜索点击进入
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				request.getSession().setAttribute("openId", fromUserName);
				// 事件类型
				String eventType = requestMap.get("Event");
				LogUtil.info("------------微信客户端发送请求------------------【微信触发类型】事件推送  eventType:" + eventType);
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					//判断用户是否已经存在  不存在新增用户，存在时增加重复关注 日志
					ZmlUserEntity user = zmlUserService.findUserByOpenId(fromUserName);
					if(user == null){
						user = new ZmlUserEntity();
						user.setOpenId(fromUserName);
						user.setCreateDate(new Date());
						user.setAvatar("");//默认头像地址
						zmlUserService.save(user);
						try {
							WeixinAccountEntity account = (WeixinAccountEntity)redisUtilTool.get(WeiXinConstants.WEIXIN_ACCOUNT);
							JSONObject objUserInfo = WeixinUtil.getUserInfo(account.getAccountaccesstoken(),fromUserName);
							LogUtil.info("得到的用户信息:"+objUserInfo.toString());
							//{"sex":1,"nickname":"小豹子","remark":"","city":"昌平","country":"中国","subscribe_time":1492531722,
							//"tagid_list":[],"subscribe":1,"province":"北京","openid":"oEXj-whsl-HAlK4tBGDbMmfRGidM","language":"zh_CN","groupid":0,"headimgurl":""}
							ZmlUserRecordEntity uerRecord = new ZmlUserRecordEntity();
							uerRecord.setWxCity(objUserInfo.getString("city"));
							uerRecord.setWxCountry(objUserInfo.getString("country"));
							uerRecord.setWxGroupid("" + objUserInfo.getInt("groupid"));
							uerRecord.setWxHeadimgurl(objUserInfo.getString("headimgurl"));
							uerRecord.setWxLanguage(objUserInfo.getString("language"));
							uerRecord.setWxNickname(objUserInfo.getString("nickname"));
							uerRecord.setWxOpenid(objUserInfo.getString("openid"));
							uerRecord.setWxProvince(objUserInfo.getString("province"));
							uerRecord.setWxRemark(objUserInfo.getString("remark"));
							uerRecord.setWxSex("" + objUserInfo.getInt("sex"));
							uerRecord.setWxSubscribe("" + objUserInfo.getInt("subscribe"));
							uerRecord.setWxSubscribeTime("" + objUserInfo.getInt("subscribe_time"));
							//uerRecord.setWxTagidList("" + objUserInfo.getInt("subscribe_time"));
							uerRecord.setUserId(user.getId());
							systemService.save(uerRecord);
							user.setNickname(objUserInfo.getString("nickname"));
							if("1".equals("" + objUserInfo.getInt("sex")))
								user.setSex("M");
							else if("0".equals("" + objUserInfo.getInt("sex")))
								user.setSex("F");
							if(objUserInfo.getString("headimgurl") != null){
								user.setAvatar(objUserInfo.getString("headimgurl"));
							}
							user.setUpdateDate(new Date());
							zmlUserService.saveOrUpdate(user);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						LogUtil.info("user come in sys: userid=" + user.getId() + ",openId=" + fromUserName);
					}
					//将用户信息，openId存入缓存
					redisUtilTool.set(fromUserName, user);
					//
					respMessage = doDingYueEventResponse(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId);
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					//增加 取消订阅  日志
					ZmlUserEntity user = zmlUserService.findUserByOpenId(fromUserName);
					if(user != null){
						//systemService.addLog("", "", "用户取消关注", Globals.APP_Log_Type_Cancel_Attention, Globals.Log_Leavel_INFO);
						LogUtil.info("user cancel attention: userid=" + user.getId() + ",openId=" + fromUserName);
						//将用户信息，openId移除缓存
						redisUtilTool.set(fromUserName, null);
						request.getSession().setAttribute("openId", fromUserName);
					}
					
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					respMessage = doMyMenuEvent(requestMap, textMessage, bundler, respMessage, toUserName, fromUserName, respContent, sys_accountId, request);
				}
				// 
				else if (eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)
						|| eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION.toUpperCase())) {
					//增加判断是否给用户推送过 设置版本 提示信息，如果推送过不再推送
					String flag = (String)redisUtilTool.get(fromUserName + Constant.AUTO_PUSH_V);
					if(!"Y".equals(flag)){
						redisUtilTool.set(fromUserName + Constant.AUTO_PUSH_V, "Y");
					}else
						respMessage = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}


	/**
	 * Q译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("微译使用指南").append("\n\n");
		buffer.append("微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}

	/**
	 * 遍历关键字管理中是否存在用户输入的关键字信息
	 * 
	 * @param content
	 * @return
	 */
	private AutoResponse findKey(String content, String toUsername) {
		LogUtil.info("---------sys_accountId--------"+toUsername+"|");
		//获取全局的数据权限ID
		String sys_accountId = weixinAccountService.findByToUsername(toUsername).getId();
		LogUtil.info("---------sys_accountId--------"+sys_accountId);
		// 获取关键字管理的列表，匹配后返回信息
		List<AutoResponse> autoResponses = autoResponseService.findByProperty(AutoResponse.class, "accountId", sys_accountId);
		LogUtil.info("---------sys_accountId----关键字查询结果条数：----"+autoResponses!=null?autoResponses.size():0);
		for (AutoResponse r : autoResponses) {
			// 如果包含关键字
			String kw = r.getKeyWord();
			String[] allkw = kw.split(",");
			for (String k : allkw) {
				if (k.equals(content)) {
					LogUtil.info("---------sys_accountId----查询结果----"+r);
					return r;
				}
			}
		}
		return null;
	}

	/**
	 * 针对文本消息
	 * @param content
	 * @param toUserName
	 * @param textMessage
	 * @param bundler
	 * @param sys_accountId
	 * @param respMessage
	 * @param fromUserName
	 * @param request
	 * @throws Exception 
	 */
	String doTextResponse(String content,String toUserName,TextMessageResp textMessage,ResourceBundle bundler,
			String sys_accountId,String respMessage,String fromUserName,HttpServletRequest request,String msgId,String msgType) throws Exception{
		//=================================================================================================================
		// 保存接收到的信息
		ReceiveText receiveText = new ReceiveText();
		receiveText.setContent(content);
		Timestamp temp = Timestamp.valueOf(DateUtils
				.getDate("yyyy-MM-dd HH:mm:ss"));
		receiveText.setCreateTime(temp);
		receiveText.setFromUserName(fromUserName);
		receiveText.setToUserName(toUserName);
		receiveText.setMsgId(msgId);
		receiveText.setMsgType(msgType);
		receiveText.setResponse("0");
		receiveText.setAccountId(toUserName);
		this.receiveTextService.save(receiveText);
		//=================================================================================================================
		//Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复
		LogUtil.info("------------微信客户端发送请求--------------Step.1 判断关键字信息中是否管理该文本内容。有的话优先采用数据库中的回复---");
		AutoResponse autoResponse = findKey(content, toUserName);
		// 根据系统配置的关键字信息，返回对应的消息
		if (autoResponse != null) {
			String resMsgType = autoResponse.getMsgType();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(resMsgType)) {
				//根据返回消息key，获取对应的文本消息返回给微信客户端
				TextTemplate textTemplate = textTemplateDao.getTextTemplate(sys_accountId, autoResponse.getTemplateName());
				textMessage.setContent(textTemplate.getContent());
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(resMsgType)) {
				List<NewsItem> newsList = this.newsItemService.findByProperty(NewsItem.class,"newsTemplate.id", autoResponse.getResContent());
				NewsTemplate newsTemplate = newsTemplateService.getEntity(NewsTemplate.class, autoResponse.getResContent());
				List<Article> articleList = new ArrayList<Article>();
				for (NewsItem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("domain") + "/"+ news.getImagePath());
					String url = "";
					if (oConvertUtils.isEmpty(news.getUrl())) {
						url = bundler.getString("domain")+ "/newsItemController.do?goContent&id="+ news.getId();
					} else {
						url = news.getUrl();
					}
					article.setUrl(url);
					article.setDescription(news.getDescription());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsResp);
			}
		} else {
			// Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）
			LogUtil.info("------------微信客户端发送请求--------------Step.2  通过微信扩展接口（支持二次开发，例如：翻译，天气）---");
			List<WeixinExpandconfigEntity> weixinExpandconfigEntityLst = weixinExpandconfigService.findByQueryString("FROM WeixinExpandconfigEntity");
			if (weixinExpandconfigEntityLst.size() != 0) {
				for (WeixinExpandconfigEntity wec : weixinExpandconfigEntityLst) {
					boolean findflag = false;// 是否找到关键字信息
					// 如果已经找到关键字并处理业务，结束循环。
					if (findflag) {
						break;// 如果找到结束循环
					}
					String[] keys = wec.getKeyword().split(",");
					for (String k : keys) {
						if (content.indexOf(k) != -1) {
							String className = wec.getClassname();
							KeyServiceI keyService = (KeyServiceI) Class.forName(className).newInstance();
							respMessage = keyService.excute(content,textMessage, request);
							findflag = true;// 改变标识，已经找到关键字并处理业务，结束循环。
							break;// 当前关键字信息处理完毕，结束当前循环
						}
					}
				}
			}
		}
		return respMessage;
	}
	
	/**
	 * 针对事件消息
	 * @param requestMap
	 * @param textMessage
	 * @param bundler
	 * @param respMessage
	 * @param toUserName
	 * @param fromUserName
	 */
	String doDingYueEventResponse(Map<String, String> requestMap,TextMessageResp textMessage ,ResourceBundle bundler,String respMessage
			,String toUserName,String fromUserName,String respContent,String sys_accountId){
		respContent = "谢谢您的关注！回复\"?\"进入主菜单。";
		List<Subscribe> lst = subscribeService.findByProperty(Subscribe.class, "accountid", sys_accountId);
		if (lst.size() != 0) {
			Subscribe subscribe = lst.get(0);
			String type = subscribe.getMsgType();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
				TextTemplate textTemplate = this.textTemplateService
						.getEntity(TextTemplate.class, subscribe
								.getTemplateId());
				String content = textTemplate.getContent();
				textMessage.setContent(content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
				List<NewsItem> newsList = this.newsItemService.findByProperty(NewsItem.class,"newsTemplate.id", subscribe.getTemplateId());
				List<Article> articleList = new ArrayList<Article>();
				NewsTemplate newsTemplate = newsTemplateService.getEntity(NewsTemplate.class, subscribe.getTemplateId());
				for (NewsItem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("domain")+ "/" + news.getImagePath());
					String url = "";
					if (oConvertUtils.isEmpty(news.getUrl())) {
						url = bundler.getString("domain")+ "/newsItemController.do?goContent&id="+ news.getId();
					} else {
						url = news.getUrl();
					}
					article.setUrl(url);
					article.setDescription(news.getDescription());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil.newsMessageToXml(newsResp);
			}
		}
		return respMessage;
	}
	
	/**
	 * 
	 * @param requestMap
	 * @param textMessage
	 * @param bundler
	 * @param respMessage
	 * @param toUserName
	 * @param fromUserName
	 * @param respContent
	 * @param sys_accountId
	 * @param request
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	String doMyMenuEvent(Map<String, String> requestMap,TextMessageResp textMessage ,ResourceBundle bundler,String respMessage
			,String toUserName,String fromUserName,String respContent,String sys_accountId,HttpServletRequest request) throws Exception{
		String key = requestMap.get("EventKey");
		//自定义菜单CLICK类型
		MenuEntity menuEntity = this.systemService.findUniqueByProperty(MenuEntity.class, "menuKey",key);
		if (menuEntity != null&& oConvertUtils.isNotEmpty(menuEntity.getTemplateId())) {
			String type = menuEntity.getMsgType();
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(type)) {
				TextTemplate textTemplate = this.textTemplateService.getEntity(TextTemplate.class, menuEntity.getTemplateId());
				String content = textTemplate.getContent();
				textMessage.setContent(content);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			} else if (MessageUtil.RESP_MESSAGE_TYPE_NEWS.equals(type)) {
				List<NewsItem> newsList = this.newsItemService.findByProperty(NewsItem.class,"newsTemplate.id", menuEntity.getTemplateId());
				List<Article> articleList = new ArrayList<Article>();
				NewsTemplate newsTemplate = newsTemplateService.getEntity(NewsTemplate.class, menuEntity.getTemplateId());
				for (NewsItem news : newsList) {
					Article article = new Article();
					article.setTitle(news.getTitle());
					article.setPicUrl(bundler.getString("domain")+ "/" + news.getImagePath());
					String url = "";
					if (oConvertUtils.isEmpty(news.getUrl())) {
						url = bundler.getString("domain")+ "/newsItemController.do?goContent&id="+ news.getId();
					} else {
						url = news.getContent();
					}
					article.setUrl(url);
					article.setDescription(news.getContent());
					articleList.add(article);
				}
				NewsMessageResp newsResp = new NewsMessageResp();
				newsResp.setCreateTime(new Date().getTime());
				newsResp.setFromUserName(toUserName);
				newsResp.setToUserName(fromUserName);
				newsResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsResp.setArticleCount(newsList.size());
				newsResp.setArticles(articleList);
				respMessage = MessageUtil
						.newsMessageToXml(newsResp);
			} else if ("expand".equals(type)) {
				WeixinExpandconfigEntity expandconfigEntity = weixinExpandconfigService.getEntity(WeixinExpandconfigEntity.class,menuEntity.getTemplateId());
				String className = expandconfigEntity.getClassname();
				KeyServiceI keyService = (KeyServiceI) Class.forName(className).newInstance();
				respMessage = keyService.excute("", textMessage,request);

			}
		}
		return respMessage;
	}
	
	/**
	 * 欢迎语
	 * @return
	 */
	public static String getMainMenu() {
		// 复杂字符串文本读取，采用文件方式存储
		String html = new FreemarkerHelper().parseTemplate("/weixin/welcome.ftl", null);
		return html;
	}
}
