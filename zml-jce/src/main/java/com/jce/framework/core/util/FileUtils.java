package com.jce.framework.core.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.DataException;
import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 文件操作工具类
 */
public class FileUtils {
	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename) {
		return getExtend(filename, "");
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtend(String filename, String defExt) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');

			if ((i > 0) && (i < (filename.length() - 1))) {
				return (filename.substring(i+1)).toLowerCase();
			}
		}
		return defExt.toLowerCase();
	}

	/**
	 * 获取文件名称[不含后缀名]
	 * 
	 * @param
	 * @return String
	 */
	public static String getFilePrefix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
	}
	
	/**
	 * 获取文件名称[不含后缀名]
	 * 不去掉文件目录的空格
	 * @param
	 * @return String
	 */
	public static String getFilePrefix2(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(0, splitIndex);
	}
	
	/**
	 * 文件复制
	 *方法摘要：这里一句话描述方法的用途
	 *@param
	 *@return void
	 */
	public static void copyFile(String inputFile,String outputFile) throws FileNotFoundException{
		File sFile = new File(inputFile);
		File tFile = new File(outputFile);
		FileInputStream fis = new FileInputStream(sFile);
		FileOutputStream fos = new FileOutputStream(tFile);
		int temp = 0;  
		byte[] buf = new byte[10240];
        try {  
        	while((temp = fis.read(buf))!=-1){   
        		fos.write(buf, 0, temp);   
            }   
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally{
            try {
            	fis.close();
            	fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        } 
	}
	/**
	 * 判断文件是否为图片<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isPicture(String filename) {
		// 文件名称为空的场合
		if (oConvertUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		//String tmpName = getExtend(filename);
		String tmpName = filename;
		// 声明图片后缀名数组
		String imgeArray[][] = { { "bmp", "0" }, { "dib", "1" },
				{ "gif", "2" }, { "jfif", "3" }, { "jpe", "4" },
				{ "jpeg", "5" }, { "jpg", "6" }, { "png", "7" },
				{ "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
		// 遍历名称数组
		for (int i = 0; i < imgeArray.length; i++) {
			// 判断单个类型文件的场合
			if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断文件是否为DWG<br>
	 * <br>
	 * 
	 * @param filename
	 *            文件名<br>
	 *            判断具体文件类型<br>
	 * @return 检查后的结果<br>
	 * @throws Exception
	 */
	public static boolean isDwg(String filename) {
		// 文件名称为空的场合
		if (oConvertUtils.isEmpty(filename)) {
			// 返回不和合法
			return false;
		}
		// 获得文件后缀名
		String tmpName = getExtend(filename);
		// 声明图片后缀名数组
		if (tmpName.equals("dwg")) {
			return true;
		}
		return false;
	}
	
	/**
	 * 删除指定的文件
	 * 
	 * @param strFileName
	 *            指定绝对路径的文件名
	 * @return 如果删除成功true否则false
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			LogUtil.info("错误: " + strFileName + "不存在!");
			return false;
		}

		LogUtil.info("--------成功删除文件---------"+strFileName);
		return fileDelete.delete();
	}
	
	/**
	 * 
	* @Title: encodingFileName 
	* @Description: 防止文件名中文乱码含有空格时%20 
	* @param @param fileName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String encodingFileName(String fileName) {
        String returnFileName = "";
        try {
            returnFileName = URLEncoder.encode(fileName, "UTF-8");
            returnFileName = StringUtils.replace(returnFileName, "+", "%20");
            if (returnFileName.length() > 150) {
                returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
                returnFileName = StringUtils.replace(returnFileName, " ", "%20");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LogUtil.info("Don't support this encoding ...");
        }
        return returnFileName;
    }
	
	public static void writeImgFile(MultipartFile mfile ,File file, int outputWidth, int height) throws ImageFormatException, IOException{
		File dir = file.getParentFile();
		if (dir != null && !dir.exists()) {
			dir.mkdirs();
		}
		FileOutputStream out = null;
		InputStream is = null;
		try {
			
			//int outputWidth = 600;
			is = mfile.getInputStream();
			Image img = ImageIO.read(is);
			int newWidth;
			int newHeight;
			int w = img.getWidth(null);
			if (w <= outputWidth) {
				outputWidth = w;
			}
			double rate1 = ((double) img.getWidth(null)) / (double) outputWidth;
			double rate = rate1;
			newWidth = (int) (((double) img.getWidth(null)) / rate);
			newHeight = (int) (((double) img.getHeight(null)) / rate);
			BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);

			tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
			out = new FileOutputStream(file);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.flush();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				//throw new DataException("保存失败,图片处理异常.");
			}
		}
	}
}
