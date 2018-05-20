package weixin.guanjia.core.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zml.base.weixin.entity.WeixinAccountEntity;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.service.impl.WechatService;
import weixin.guanjia.core.util.SignUtil;

@Controller
@RequestMapping("/wechatController")
public class WechatController {
	@Autowired
	private WechatService wechatService;
	@Autowired
	private WeixinAccountServiceI weixinAccountService;

	@RequestMapping(params="wechat", method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "signature") String signature,
			@RequestParam(value = "timestamp") String timestamp,
			@RequestParam(value = "nonce") String nonce,
			@RequestParam(value = "echostr") String echostr) {
		System.out.println("wechat.get..........");
		List<WeixinAccountEntity> weixinAccountEntities = weixinAccountService.getList(WeixinAccountEntity.class);
		for (WeixinAccountEntity account : weixinAccountEntities) {
			if (SignUtil.checkSignature(account.getAccounttoken(), signature,
					timestamp, nonce)) {
				try {
					response.getWriter().print(echostr);
					break;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	//用户关注时请求参数：/wechatController.do,[请求参数]:wechat=&signature=fae0e5f4a5776bdae47513f6a144a4422235a82b&timestamp=1492529067&nonce=57663383&openid=o-qeWwkzDqqYFKgjNnr_iCrIkr5k&
	//取消关注：                         /wechatController.do,[请求参数]:wechat=&signature=48ba13e1c36f0ee2ef3cb213bda6c8631f02f46f&timestamp=1492529444&nonce=1717300675&openid=o-qeWwkzDqqYFKgjNnr_iCrIkr5k&

	@RequestMapping(params = "wechat", method = RequestMethod.POST)
	public void wechatPost(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		System.out.println("wechat.post..........");
		String respMessage = wechatService.coreService(request);
		if(respMessage != null && !"".equals(respMessage)){
			PrintWriter out = response.getWriter();
			out.print(respMessage);
			out.close();
		}
	}

}
