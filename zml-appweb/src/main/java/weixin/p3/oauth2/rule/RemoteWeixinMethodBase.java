package weixin.p3.oauth2.rule;

import java.util.Map;

import com.google.gson.Gson;
import com.jce.framework.core.util.JSONHelper;
import com.jce.framework.core.util.LogUtil;

import net.sf.json.JSONObject;
import weixin.guanjia.core.util.WeixinUtil;

public abstract class RemoteWeixinMethodBase {
	/**
	 * 通用调用远程方法
	 * @param url
	 * @param pojo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> callWeixinRemoteMethod(String url,Object pojo){
		JSONObject json2 =	WeixinUtil.httpRequest(url, "POST", JSONHelper.bean2json(pojo));
		Gson gson = new Gson();
		Map<String,Object> ruleResut = gson.fromJson(json2.toString(), Map.class);
		LogUtil.info(json2);
		return ruleResut;
	}
}
