package com.zml.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.ConnectException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 微信相关工具类
 */
public class WeixinUtil {
	static Log log = LogFactory.getLog(WeixinUtil.class); 
	
	private static final String TOKEN ="zml_zm";
	/**
	 * 解析request中的xml 并将数据存储到一个Map中返回
	 * @param request
	 */
	public static Map<String, String> parseXml(HttpServletRequest request){
		Map<String, String> map = new HashMap<String, String>();
		try {
			InputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> elementList = root.elements();
			for (Element e : elementList)
				//遍历xml将数据写入map
				map.put(e.getName(), e.getText());
			inputStream.close();
			inputStream = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	/**
	 * sha1加密算法
	 * @param key需要加密的字符串
	 * @return 加密后的结果
	 */
	public static String sha1(String key) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			
			 // 将三个参数字符串拼接成一个字符串进行sha1加密  
			md.update(key.getBytes());
			String pwd = new BigInteger(1, md.digest()).toString(16);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}
	
	/**
	 * 根据token计算signature验证是否为weixin服务端发送的消息
	 * return 返回校验的结果值
	 */
	public static boolean checkSignature(HttpServletRequest request){
		String signature = request.getParameter("signature");// 微信加密签名  
		String timestamp = request.getParameter("timestamp");// 时间戳 
		String nonce = request.getParameter("nonce");// 随机数
		if (signature != null && timestamp != null && nonce != null ) {
			String[] strSet = new String[] { TOKEN, timestamp, nonce };
			
			// 将token、timestamp、nonce三个参数进行字典序排序  
			java.util.Arrays.sort(strSet);
			 StringBuilder content = new StringBuilder();  
			for (String string : strSet) {
				content.append(string);  
			}
			String pwd = sha1(content.toString());
			System.out.println(pwd);
			System.out.println(signature);
			return pwd.equalsIgnoreCase(signature);
		}else {
			return false;
		}
	}
	
	/**
	 * 获取token
	 * @param appid
	 * @param appsecret
	 * @return
	 * @throws IOException
	 */
	public static AccessToken getAccessToken(String appid , String appsecret) throws IOException {
		AccessToken accessToken = null;
		Date s = new Date();
		
		String requestUrl = Constants.GET_ACCESSTOKEN_URL.replace("APPID" , appid).replace("APPSECRET" , appsecret);
		JSONObject jsonObject = WeixinUtil.httpRequest(requestUrl , "GET" , null);
		// 如果请求成功
		if (null != jsonObject) {
			accessToken = new AccessToken();
			accessToken.setAccessToken(jsonObject.getString("access_token"));
			accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return accessToken;
	}	
	public static JSONObject httpRequest(String requestUrl , String requestMethod , String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL" , "SunJSSE");
			sslContext.init(null , tm , new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod)) httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream , "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
			return jsonObject;
		} catch (ConnectException ce) {
			System.out.println("Weixin server connection timed out.");
		} catch (Exception e) {
			System.out.println("error.");
		}
		return jsonObject;
	}
	
	/**
     * 获取网页授权的access_token  
     *
     * @param code（ 这个来自于微信端）
     * @return
     */
    public static JSONObject getNetAccessToken(HttpServletRequest request,String code,String appid,String secret) {
        try {
            String url = String.format(Constants.NET_ACCESSTOKEN_URL, appid, secret, code);
            log.info("request accessToken from url:"+url);
            return httpRequest(url,"GET",null);
        } catch (Exception ex) {
            log.error("fail to request wechat access token. [error={}]"+ex);
        }
        return null;
    }
    
    
    /**
     * 获取微信JSSDK的ticket 
     * @author Benson
     */
    public static String getJSSDKTicket(String access_token) {  
        String returnString="";
        String requestUrl = Constants.WEIXIN_JSSDK_TICKET_URL.replace("ACCESS_TOKEN", access_token);  
        JSONObject jsonObject = httpRequest(requestUrl, "GET", null);  
        
        // 如果请求成功   
        if (null != jsonObject) {  
            try {  
                returnString=jsonObject.getString("ticket");  
            } catch (JSONException e) { 
            	log.error("获取微信JSSDK的ticket失败："+e);
                returnString = null;  
            }  
        } 
        
        return returnString;  
    } 
    
    /**
     * 获取微信的配置信息
     * @param jsapi_ticket
     * @param 请求的url
     * @return
     */
    public static Map<String, String> getWxConfig(String jsapi_ticket, String url) {
        Map<String, String> ret = new HashMap<String, String>();
        String nonce_str = create_nonce_str();
        String timestamp = create_timestamp();
        String str;
        String signature = "";
 
        //注意这里参数名必须全部小写，且必须有序
        str = "jsapi_ticket=" + jsapi_ticket +
                  "&noncestr=" + nonce_str +
                  "&timestamp=" + timestamp +
                  "&url=" + url;
 
        try
        {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(str.getBytes("UTF-8"));
            signature = byteToHex(crypt.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
 
        ret.put("url", url);
        ret.put("jsapi_ticket", jsapi_ticket);
        ret.put("nonceStr", nonce_str);
        ret.put("timestamp", timestamp);
        ret.put("signature", signature);
 
        return ret;
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
 
    private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }
 
    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	
	public static void main(String[] args) {
		try {
			//获取accessToken之前判断文件里有没有，没有重新获取，填写过期时间，有判断时间有没有过期
			AccessToken token = new AccessToken() ;
			token = WeixinUtil.getAccessToken(Constants.APPID, Constants.SECRET);
			String ticket = WeixinUtil.getJSSDKTicket(token.getAccessToken());
			Map<String, String> map= WeixinUtil.getWxConfig(ticket,"http://wechatdev.dbhapp.com/found/showNewsDetail?id=14");
			System.out.println(map.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
