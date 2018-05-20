package com.zml.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jce.framework.core.util.DateUtils;
import com.jce.framework.core.util.FileUtils;
import com.thoughtworks.xstream.core.BaseException;
import com.zml.base.entity.BaseEntity;
import com.zml.common.Constant;
import com.zml.common.ReMsg;
import com.zml.enums.DocumentDirName;
import com.zml.util.GenerateNo;

//发布信息 控制类
@Controller
@RequestMapping("/uploadController")
public class UploadController {
	@RequestMapping(value = { "upload" })
    public void pushErrorData(HttpServletRequest request,
                HttpServletResponse response) throws BaseException {
        String userID=request.getParameter("userID");
        // 转型为MultipartHttpRequest(重点的所在)这个就是上面ajax中提到如果直接访问此接口而不加"/"，此转型就会报错
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //MultipartFile file = multipartRequest.getFiles("errPic");//获取文件集合  对应  jquery $("#imageFile").get(0).files
        // 获得第1张图片（根据前台的name名称得到上传的文件）
        MultipartFile file = multipartRequest.getFile("errPic"); //对应  jquery $("#imageFile").get(0).files[index]
        Map<String, Object> map = new HashMap<String, Object>();
        if (null!=file && !file.isEmpty()) {
            try {
                //map = uploadFile(file);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }

	/**
	 * 图片上传
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws BaseException
	 */
	public static Map<String, Object> uploadFile(MultipartFile file)
	        throws IOException{
	    String fail = "0";//上传失败状态
	    String success = "1";//上传成功状态
	    String errorMsg = "上传出错了";//上传错误信息
	    String filePath = Constant.IMG_FILE_PATH;//上传路径，本来是相对路径，但是在发布后路径会创建在tomcat的bin目录下，所以接口上传的路径是一个难题，我用的是绝对路径，等到发布到Linux环境中，通过配置文件修改路径为Linux中的资源地址【防止工程删除而资源文件不会丢失】，然后重新发布工程时再通过Linux的命令把我们需要的资源文件导入到我们发布的工程项目中。
	    String size ="65535";
	    String interfaceService="http://127.0.0.1/zml_appweb/";
	     
	    long maxFileSize = (Integer.valueOf(size)) * 1024 * 1024;
	    String suffix = file.getOriginalFilename().substring(
	            file.getOriginalFilename().lastIndexOf("."));
	    long fileSize = file.getSize();
	    Map<String, Object> map = new HashMap<String, Object>();
	    if (suffix.equals(".png") || suffix.equals(".jpg")) {
	        if (fileSize < maxFileSize) {
	            // System.out.println("fileSize"+fileSize);
	            String fileName = file.getOriginalFilename();
	            fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
	            File tempFile = new File(filePath, new Date().getTime()
	                    + fileName);
	
	            try {
	                if (!tempFile.getParentFile().exists()) {
	                    tempFile.getParentFile().mkdirs();//如果是多级文件使用mkdirs();如果就一层级的话，可以使用mkdir()
	                }
	                if (!tempFile.exists()) { 
	                    tempFile.createNewFile();
	                }
	                file.transferTo(tempFile);
	            } catch (IllegalStateException e) {
	            	e.printStackTrace();
	            }
	
	            map.put("SUCESS", success);
	            map.put("data",interfaceService + filePath + new Date().getTime() + tempFile.getName());
	
	        } else {
	            map.put("SUCESS", fail);
	            map.put("data", "Image too big");
	        }
	
	    } else {
	        map.put("SUCESS", fail);
	        map.put("data", "Image format error");
	    }
	    return map;
	}
	
	/**
	 * 上传发布信息图片
	 * @param file
	 * @param docDir 对应枚举  DocumentDirName
	 */
	@RequestMapping(value = { "uploadReleaseFile" })
	@ResponseBody
	public ReMsg uploadReleaseFile(HttpServletRequest request, String docDir) {
		 ReMsg msg = null;
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
	     //MultipartFile file = multipartRequest.getFiles("errPic");//获取文件集合  对应  jquery $("#imageFile").get(0).files
	     // 获得第1张图片（根据前台的name名称得到上传的文件）
	     MultipartFile file = multipartRequest.getFile("imageFile"); 
	     if (file != null) {
	    	String rsPath = "";
			FileOutputStream out = null;
			String[] filename = file.getOriginalFilename().split("\\.");
			if (filename == null || filename.length == 0 || "".equals(filename[0])) {
				
			}
			String suffix = filename[filename.length - 1];
			//String path = BaseEntity.class.getResource("").getPath();
			//path = path.split("WEB-INF")[0];
			String path = Constant.IMG_FILE_PATH;
			String yyyyMM = DateUtils.formatDateToStr(DateUtils.FORMAT_YYYYMM, new Date());
			String tempPath = Constant.IMG_FILE_PATH_ONE + docDir + File.separator + yyyyMM + File.separator;
			String tempFile = System.currentTimeMillis() + GenerateNo.getRandomNum(Constant.FILE_NAME_RAND_LEN);
			path += tempPath;
			//存大图
			File maxFile = new File(path + tempFile + "_max." + suffix);
			File dir = maxFile.getParentFile();
			if (dir != null && !dir.exists()) {
				dir.mkdirs();
			}
			try {
				maxFile.createNewFile();
				out = new FileOutputStream(maxFile);
				out.write(file.getBytes());
				out.flush();
				out.close();
				rsPath = tempPath + tempFile + "_min." + suffix;
				rsPath = rsPath.replace("\\", "/");
				String minFilePath = path + tempFile + "_min." + suffix;
				// 缩略图
				File minFile = new File(minFilePath);
				FileUtils.writeImgFile(file, minFile, Constant.MAX_IMG_FILE_WIDTH, Constant.MINI_IMG_FILE_HEIGHT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			msg = new ReMsg(rsPath, true);
		}else{
			msg = new ReMsg("file is null", false);
		}
	    return msg;
	}
}
