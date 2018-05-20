package com.zml.util.file;  

import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.zml.base.entity.BaseEntity;
import com.zml.common.Constant;

/** 
 * @author wcb 
 * 2017-6-1 
 */  
public class ImageTools {  
	private static final int BLACK = 0xFF000000; 
	private static final int WHITE = 0xFFFFFFFF;

	public static BufferedImage toBufferedImage(BitMatrix matrix) { 
		int width = matrix.getWidth(); 
		int height = matrix.getHeight(); 
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); 
		for (int x = 0; x < width; x++) { 
			for (int y = 0; y < height; y++) { 
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE); 
			} 
		} 
		return image; 
	}

	/** 
	 * 获取图片宽度 
	 * @param file  图片文件 
	 * @return 宽度 
	 */  
	public static int getImgWidth(File file) {  
		InputStream is = null;  
		BufferedImage src = null;  
		int ret = -1;  
		try {  
			is = new FileInputStream(file);  
			src = javax.imageio.ImageIO.read(is);  
			ret = src.getWidth(null); // 得到源图宽  
			is.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return ret;  
	}  


	/** 
	 * 获取图片高度 
	 * @param file  图片文件 
	 * @return 高度 
	 */  
	public static int getImgHeight(File file) {  
		InputStream is = null;  
		BufferedImage src = null;  
		int ret = -1;  
		try {  
			is = new FileInputStream(file);  
			src = javax.imageio.ImageIO.read(is);  
			ret = src.getHeight(null); // 得到源图高  
			is.close();  
		} catch (Exception e) {  
			e.printStackTrace();  
		}  
		return ret;  
	}

	/**
	 * 生成二维码
	 * @param groupChat
	 * @throws Exception
	 */
	public static String createQRCode(String id, String content) throws Exception {
		//String pathImg = "";
		String text = content; 
		int width = 300; 
		int height = 300; 
		String format = "png"; 
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>(); 
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); 
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text,BarcodeFormat.QR_CODE, width, height, hints); 
		//String path = Constant.WRW_USERIMG_FILE_PATH;// BaseEntity.class.getResource("").getPath();
		//path = path.split("WEB-INF")[0];
		String path = "userImg/recommenQRcode/" + id + "_index.png";
		File file = new File(Constant.WRW_USERIMG_FILE_PATH + path);
		File dir = file.getParentFile();
		if (dir != null && !dir.exists()) {
			dir.mkdirs();
		}
		ImageTools.writeToFile(bitMatrix, format, file);
		//pathImg = "upload/userImg/orderQRcode/" + id + "/index.png";
		return path;
	}

	public static void writeToFile(BitMatrix matrix, String format, File file) 
			throws IOException { 
		BufferedImage image = toBufferedImage(matrix); 
		if (!ImageIO.write(image, format, file)) { 
			throw new IOException("Could not write an image of format " + format + " to " + file); 
		} 
	}
}  