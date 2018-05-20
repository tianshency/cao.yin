package com.jce.framework.weixin.api.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.log4j.Logger;

public class HttpRequestProxy
{
  private static int connectTimeOut = 5000;

  private static int readTimeOut = 10000;

  public static String requestEncoding = "UTF-8";

  private static Logger logger = Logger.getLogger(HttpRequestProxy.class);

  public static String doGet(String reqUrl, Map parameters, String recvEncoding)
  {
    HttpURLConnection url_con = null;
    String responseContent = null;
    try {
      url_con = doHttpGetConnection(reqUrl, parameters);

      InputStream in = url_con.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(in, 
        recvEncoding));
      String tempLine = rd.readLine();
      StringBuffer temp = new StringBuffer();
      String crlf = System.getProperty("line.separator");
      while (tempLine != null) {
        temp.append(tempLine);
        temp.append(crlf);
        tempLine = rd.readLine();
      }
      responseContent = temp.toString();
      rd.close();
      in.close();
    } catch (IOException e) {
      logger.error("网络故障", e);
    } finally {
      if (url_con != null) {
        url_con.disconnect();
      }
    }

    return responseContent;
  }

  private static HttpURLConnection doHttpGetConnection(String reqUrl, Map parameters) throws IOException
  {
    HttpURLConnection url_con = null;
    String params = getMapParamsToStr(parameters, requestEncoding);

    URL url = new URL(reqUrl);
    url_con = (HttpURLConnection)url.openConnection();
    url_con.setRequestMethod("GET");
    System.setProperty("sun.net.client.defaultConnectTimeout", 
      String.valueOf(connectTimeOut));
    System.setProperty("sun.net.client.defaultReadTimeout", 
      String.valueOf(readTimeOut));

    url_con.setDoOutput(true);
    byte[] b = params.toString().getBytes();
    url_con.getOutputStream().write(b, 0, b.length);
    url_con.getOutputStream().flush();
    url_con.getOutputStream().close();

    return url_con;
  }

  public static String doGet(String reqUrl, String recvEncoding)
  {
    HttpURLConnection url_con = null;
    String responseContent = null;
    try {
      StringBuffer params = new StringBuffer();
      String queryUrl = reqUrl;
      int paramIndex = reqUrl.indexOf("?");

      if (paramIndex > 0) {
        queryUrl = reqUrl.substring(0, paramIndex);
        String parameters = reqUrl.substring(paramIndex + 1, 
          reqUrl.length());
        String[] paramArray = parameters.split("&");
        for (int i = 0; i < paramArray.length; i++) {
          String string = paramArray[i];
          int index = string.indexOf("=");
          if (index > 0) {
            String parameter = string.substring(0, index);
            String value = string.substring(index + 1, 
              string.length());
            params.append(parameter);
            params.append("=");
            params.append(URLEncoder.encode(value, 
              requestEncoding));
            params.append("&");
          }
        }

        params = params.deleteCharAt(params.length() - 1);
      }

      URL url = new URL(queryUrl);
      url_con = (HttpURLConnection)url.openConnection();
      url_con.setRequestMethod("GET");
      System.setProperty("sun.net.client.defaultConnectTimeout", 
        String.valueOf(connectTimeOut));
      System.setProperty("sun.net.client.defaultReadTimeout", 
        String.valueOf(readTimeOut));

      url_con.setDoOutput(true);
      byte[] b = params.toString().getBytes();
      url_con.getOutputStream().write(b, 0, b.length);
      url_con.getOutputStream().flush();
      url_con.getOutputStream().close();
      InputStream in = url_con.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(in, 
        recvEncoding));
      String tempLine = rd.readLine();
      StringBuffer temp = new StringBuffer();
      String crlf = System.getProperty("line.separator");
      while (tempLine != null) {
        temp.append(tempLine);
        temp.append(crlf);
        tempLine = rd.readLine();
      }
      responseContent = temp.toString();
      rd.close();
      in.close();
    } catch (IOException e) {
      logger.error("网络故障", e);
    } finally {
      if (url_con != null) {
        url_con.disconnect();
      }
    }

    return responseContent;
  }

  public static String doPost(String reqUrl, Map parameters, String recvEncoding)
  {
    HttpURLConnection url_con = null;
    String responseContent = null;
    try {
      String params = getMapParamsToStr(parameters, requestEncoding);
      URL url = new URL(reqUrl);
      url_con = (HttpURLConnection)url.openConnection();
      url_con.setRequestMethod("POST");
      System.setProperty("sun.net.client.defaultConnectTimeout", 
        String.valueOf(connectTimeOut));
      System.setProperty("sun.net.client.defaultReadTimeout", 
        String.valueOf(readTimeOut));

      url_con.setDoOutput(true);
      byte[] b = params.toString().getBytes();
      url_con.getOutputStream().write(b, 0, b.length);
      url_con.getOutputStream().flush();
      url_con.getOutputStream().close();

      InputStream in = url_con.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(in, 
        recvEncoding));
      String tempLine = rd.readLine();
      StringBuffer tempStr = new StringBuffer();
      String crlf = System.getProperty("line.separator");
      while (tempLine != null) {
        tempStr.append(tempLine);
        tempStr.append(crlf);
        tempLine = rd.readLine();
      }
      responseContent = tempStr.toString();
      rd.close();
      in.close();
    } catch (IOException e) {
      logger.error("网络故障", e);
    } finally {
      if (url_con != null) {
        url_con.disconnect();
      }
    }
    return responseContent;
  }

  public static String doJsonPost(String reqUrl, Map parameters, String jsonData)
  {
    HttpURLConnection url_con = null;
    String responseContent = null;
    try {
      String params = getMapParamsToStr(parameters, requestEncoding);

      URL url = new URL(reqUrl + "&" + params);
      url_con = (HttpURLConnection)url.openConnection();
      url_con.setRequestMethod("POST");
      System.setProperty("sun.net.client.defaultConnectTimeout", 
        String.valueOf(connectTimeOut));
      System.setProperty("sun.net.client.defaultReadTimeout", 
        String.valueOf(readTimeOut));
      url_con.setDoOutput(true);
      url_con.setRequestProperty("Accept", "application/json");
      url_con.setRequestProperty("Content-Type", "application/json");

      byte[] b = jsonData.toString().getBytes();
      url_con.getOutputStream().write(b, 0, b.length);
      url_con.getOutputStream().flush();
      url_con.getOutputStream().close();

      InputStream in = url_con.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(in, 
        requestEncoding));
      String tempLine = rd.readLine();
      StringBuffer tempStr = new StringBuffer();
      String crlf = System.getProperty("line.separator");
      while (tempLine != null) {
        tempStr.append(tempLine);
        tempStr.append(crlf);
        tempLine = rd.readLine();
      }
      responseContent = tempStr.toString();
      rd.close();
      in.close();
    } catch (IOException e) {
      logger.error("网络故障", e);
    } finally {
      if (url_con != null) {
        url_con.disconnect();
      }
    }
    return responseContent;
  }

  public static String uploadMedia(String reqUrl, Map parameters, String recvEncoding, InputStream fileIn, String fileName, String contentType)
  {
    HttpURLConnection url_con = null;
    String responseContent = null;
    try
    {
      String BOUNDARY = "----------" + System.currentTimeMillis();
      String params = getMapParamsToStr(parameters, requestEncoding);

      URL urlObj = new URL(reqUrl + "&" + params.toString());

      url_con = (HttpURLConnection)urlObj.openConnection();

      url_con.setRequestMethod("POST");
      url_con.setDoInput(true);
      url_con.setDoOutput(true);
      url_con.setUseCaches(false);

      url_con.setRequestProperty("Connection", "Keep-Alive");
      url_con.setRequestProperty("Charset", recvEncoding);

      url_con.setRequestProperty("Content-Type", 
        "multipart/form-data; boundary=" + BOUNDARY);

      StringBuilder sb = new StringBuilder();
      sb.append("--");
      sb.append(BOUNDARY);
      sb.append("\r\n");
      sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + 
        fileName + "\"\r\n");
      sb.append("Content-Type:application/octet-stream\r\n\r\n");

      byte[] head = sb.toString().getBytes(recvEncoding);

      OutputStream out = new DataOutputStream(url_con.getOutputStream());

      out.write(head);

      DataInputStream in = new DataInputStream(fileIn);
      int bytes = 0;
      byte[] bufferOut = new byte[1024];
      while ((bytes = in.read(bufferOut)) != -1) {
        out.write(bufferOut, 0, bytes);
      }
      in.close();

      byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(recvEncoding);

      out.write(foot);
      out.flush();
      out.close();

      InputStream iddn = url_con.getInputStream();
      BufferedReader rd = new BufferedReader(new InputStreamReader(iddn, 
        recvEncoding));
      String tempLine = rd.readLine();
      StringBuffer tempStr = new StringBuffer();
      String crlf = System.getProperty("line.separator");
      while (tempLine != null) {
        tempStr.append(tempLine);
        tempStr.append(crlf);
        tempLine = rd.readLine();
      }
      responseContent = tempStr.toString();
      rd.close();
    } catch (IOException e) {
      logger.error("网络故障", e);
    } finally {
      if (url_con != null) {
        url_con.disconnect();
      }
    }
    return responseContent;
  }

  private static String getMapParamsToStr(Map paramMap, String requestEncoding)
    throws IOException
  {
    StringBuffer params = new StringBuffer();

    for (Iterator iter = paramMap.entrySet().iterator(); iter
      .hasNext(); )
    {
      Map.Entry element = (Map.Entry)iter.next();
      params.append(element.getKey().toString());
      params.append("=");
      params.append(URLEncoder.encode(element.getValue().toString(), 
        requestEncoding));
      params.append("&");
    }

    if (params.length() > 0) {
      params = params.deleteCharAt(params.length() - 1);
    }

    return params.toString();
  }

  public static String downMadGet(String reqUrl, Map parameters, String recvEncoding, String filePath, String media_id)
  {
    HttpURLConnection url_con = null;
    String responseContent = null;
    try {
      url_con = doHttpGetConnection(reqUrl, parameters);
      String fileContentType = url_con.getHeaderField("Content-Type");
      InputStream in = url_con.getInputStream();
      if ("text/plain".equals(fileContentType)) {
        BufferedReader rd = new BufferedReader(new InputStreamReader(in, 
          recvEncoding));
        responseContent = rd.readLine();
        rd.close();
      } else {
        String fileSuffix = WeiXinReqUtil.getFileSuffix(fileContentType);
        BufferedInputStream bis = new BufferedInputStream(in);
        String fileName = media_id + "." + fileSuffix;
        String filePathName = filePath + File.separator + fileName;
        byte[] buf = new byte[8096];
        OutputStream fileOut = new FileOutputStream(new File(filePathName));
        int size = 0;
        while ((size = bis.read(buf)) != -1) {
          fileOut.write(buf, 0, size);
        }
        fileOut.flush();
        fileOut.close();
        bis.close();
        responseContent = "{filepath:'" + filePath + "',fileName:'" + fileName + "'}";
      }
      System.out.println(fileContentType);
      in.close();
    } catch (IOException e) {
      logger.error("网络故障", e);
    } finally {
      if (url_con != null) {
        url_con.disconnect();
      }
    }

    return responseContent;
  }

  public static int getConnectTimeOut()
  {
    return connectTimeOut;
  }

  public static int getReadTimeOut()
  {
    return readTimeOut;
  }

  public static String getRequestEncoding()
  {
    return requestEncoding;
  }

  public static void setConnectTimeOut(int connectTimeOut)
  {
    connectTimeOut = connectTimeOut;
  }

  public static void setReadTimeOut(int readTimeOut)
  {
    readTimeOut = readTimeOut;
  }

  public static void setRequestEncoding(String requestEncoding)
  {
    requestEncoding = requestEncoding;
  }
}