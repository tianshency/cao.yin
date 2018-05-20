package com.jce.framework.weixin.api.core.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils
{
  public static void readToBuffer(StringBuffer buffer, String filePath)
    throws IOException
  {
    InputStream is = new FileInputStream(filePath);

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    String line = reader.readLine();
    while (line != null) {
      buffer.append(line);
      buffer.append("\n");
      line = reader.readLine();
    }
    reader.close();
    is.close();
  }

  public static String readFile(String filePath)
    throws IOException
  {
    StringBuffer sb = new StringBuffer();
    readToBuffer(sb, filePath);
    return sb.toString();
  }
}