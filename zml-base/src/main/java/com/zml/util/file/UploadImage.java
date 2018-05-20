package com.zml.util.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zml.common.Constant;

/**
 * 上传图片
 * 
 * @author xingzhanling
 * @date 2015-7-10
 */
public class UploadImage {
	/**
	 * 上传图片
	 * 
	 * @author xingzhanling
	 * @param upload
	 * @param imagesSavePath
	 * @throws IOException
	 */
	public static String upload(MultipartFile upload, String imagesSavePath
			) throws IOException {
		String newFileName = "";
		File f = new File(imagesSavePath);
		if (!f.exists() && !f.isDirectory()) {
			f.mkdirs();
		}
		if (upload != null && !upload.isEmpty()) {
			String fileName = upload.getOriginalFilename();
			String extensionName = fileName
					.substring(fileName.lastIndexOf(".") + 1);
			newFileName = String.valueOf(UUID.randomUUID().toString()) + "."
					+ extensionName;
			InputStream is = upload.getInputStream();
			FileOutputStream fos = new FileOutputStream(imagesSavePath + "/"
					+ newFileName);
			byte[] buffer = new byte[8192];
			int count = 0;
			while ((count = is.read(buffer)) > 0) {
				fos.write(buffer, 0, count);
			}
			fos.close();
			is.close();
		} else {
			newFileName = "";
		}
		return newFileName;
	}

	/**
	 * 
	 * 上传多个图片
	 * 
	 * @param upload
	 * @param imagesSavePath
	 * @return
	 */
	public static String[] MultiImages(MultipartFile upload[],
									   String imagesSavePath) {
		String fileName[] = new String[10];
		String extensionName[] = new String[10];
		String newFileName[] = new String[10];

		try {
			for (int i = 0; i < upload.length; i++) {
				if (upload[i].isEmpty()) {
					continue;
				}
				fileName[i] = upload[i].getOriginalFilename();// 获取文件名
				extensionName[i] = fileName[i].substring(fileName[i]
						.lastIndexOf(".") + 1);// 获取图片扩展名
				newFileName[i] = String.valueOf(System.currentTimeMillis())
						+ "." + extensionName[i];

				InputStream is = upload[i].getInputStream();
				FileOutputStream fos = new FileOutputStream(imagesSavePath
						+ "/" + newFileName[i]);
				byte[] buffer = new byte[8192]; // 每次读8K字节
				int count = 0;
				// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count); // 向服务端文件写入字节流
				}
				fos.close(); // 关闭FileOutputStream对象
				is.close(); // InputStream对象
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return newFileName;
	}

	public static String[] MultiImages(MultipartFile upload[],
									   HttpSession session) {
		return MultiImages(upload, getSavePath("/static/images/",session));
	}
	
	
	/**
	 * 
	 * 上传多个图片
	 * 
	 * @param upload
	 * @param imagesSavePath
	 * @return
	 */
	public static Map<String,String> MultiImages(MultipartFile upload[],String imagesSavePath,String showPic) {
		String fileName[] = new String[10];
		String extensionName[] = new String[10];
		String newFileName[] = new String[10];
		Map<String,String> map=null;
		try {
			String[] picName = showPic.split(","); 
			map = new HashMap<String,String>();
			for (int i = 0; i < upload.length; i++) {
				if (upload[i].isEmpty()) {
					continue;
				}
				fileName[i] = upload[i].getOriginalFilename();// 获取文件名
				extensionName[i] = fileName[i].substring(fileName[i]
						.lastIndexOf(".") + 1);// 获取图片扩展名
				newFileName[i] = String.valueOf(System.currentTimeMillis())
						+ "." + extensionName[i];

				InputStream is = upload[i].getInputStream();
				FileOutputStream fos = new FileOutputStream(imagesSavePath
						+ "/" + newFileName[i]);
				byte[] buffer = new byte[8192]; // 每次读8K字节
				int count = 0;
				// 开始读取上传文件的字节，并将其输出到服务端的上传文件输出流中
				while ((count = is.read(buffer)) > 0) {
					fos.write(buffer, 0, count); // 向服务端文件写入字节流
				}
				fos.close(); // 关闭FileOutputStream对象
				is.close(); // InputStream对象
				map.put(picName[i], newFileName[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}


	/**
	 * 取得文件保存的绝对路径
	 * @param relativePath 相对路径
	 * @param session
	 * @return
	 */
	public static String getSavePath(String relativePath, HttpSession session){
		String imagesSavePath ;
		if(StringUtils.isEmpty(Constant.IMG_FILE_PATH)) {
			imagesSavePath = session.getServletContext().getRealPath("/")
					+ relativePath;
		}else{
			imagesSavePath = Constant.IMG_FILE_PATH + Constant.IMG_FILE_PATH_ONE
					+ relativePath;
		}
		return imagesSavePath;
	}
}
