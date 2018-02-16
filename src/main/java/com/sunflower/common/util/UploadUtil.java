package com.sunflower.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * 
 * 类名称：MD5Util 创建时间：Feb 15, 2018
 * 
 * @version 1.0.0
 * 
 */
public class UploadUtil {

	/**
	 * 允许上传的文件类型
	 */
	private static final String allowedFileType = "|7z|aiff|asf|avi|bmp|csv|doc|docx|docm|fla|flv|gif|gz|gzip|jpeg|jpg|mid|mov|mp3|mp4|mpc|mpeg|mpg|ods|odt|pdf|png|ppt|pxd|qt|ram|rar|rm|rmi|rmvb|rtf|sdc|sitd|swf|sxc|sxw|tar|tgz|tif|tiff|txt|vsd|wav|wma|wmv|xls|xlsx|xml|zip|";

	/**
	 * 检查图片文件格式（".jpeg", ".jpg", ".gif", ".bmp", ".png"）
	 * 
	 * @param fileFormat
	 * @return
	 */
	public static boolean checkImgFormat(String fileFormat) {
		fileFormat = fileFormat.toLowerCase();
		String fileType[] = { ".jpeg", ".jpg", ".gif", ".bmp", ".png" };
		for (int j = 0; j < fileType.length; j++) {
			if (fileType[j].equals(fileFormat)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查文件类型字符串是否合法
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean checkAllowedFile(String fileType) {
		fileType = "|".concat(fileType.toLowerCase()).concat("|");
		if (!allowedFileType.contains(fileType)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 获取文件类型
	 * 
	 * @param mf
	 * @return
	 */
	public static String getType(MultipartFile mf) {
		String type = mf.getOriginalFilename().substring(mf.getOriginalFilename().lastIndexOf(".") + 1);
		return type;
	}

	/**
	 * 检查文件是否允许上传
	 * 
	 * @param mf
	 * @return
	 */
	public static boolean checkAllowedFile(MultipartFile mf) {
		return checkAllowedFile(getType(mf));
	}
	
	/**
	 * 生成前缀路径
	 * 
	 * @return
	 */
	public static String prefixPath() {
		String fileCatalog = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			fileCatalog = sdf.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		StringBuffer buf = new StringBuffer();
		buf.append("/uploads/");
		buf.append(fileCatalog);
		buf.append("/");
		return buf.toString();
	}

	/**
	 * 生成后缀路径
	 * 
	 * @param type
	 * @return
	 */
	public static String suffixPath(String type, String parrten) {
		String filenName = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(parrten);
			filenName = sdf.format(new Date());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		StringBuffer buf = new StringBuffer();
		buf.append(filenName);
		buf.append(".");
		buf.append(new Random().nextInt(1000));
		buf.append(".");
		buf.append(type);
		return buf.toString();
	}
	
	/**
     * 获取系统绝对路径
     *
     * @return
     */
    public static final String getAppPath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        path = path.substring(0, path.indexOf("WEB-INF"));
        if (System.getProperties().get("os.name").toString().toLowerCase().contains("windows")) {
            path = path.substring(1, path.length());
        } else {
            path = File.separator.concat(path);
        }
        return path;
    }
    
    /**
	 * 将字节流转成图片
	 * 
	 * @param input 输入流
	 * @param target 目标目录地址
	 * @throws Exception
	 */
	public static void writeFile(InputStream input, String target) throws Exception {
		OutputStream output = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(target));
			byte[] buffer = new byte[1024];
			int n;
			while ((n = input.read(buffer)) != (-1)) {
				output.write(buffer, 0, n);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("writeFile error");
		} finally {
			try {
				input.close();
				output.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new Exception("writeFile error");
			}
		}
	}
	
    /**
     * 保存文件，并返回图片保存位置
     * @param mf
     * @param uriDirector
     * @return
     * @throws Exception
     */
	public static String saveFile(MultipartFile mf, String uriDirector) throws Exception {
		String type = getType(mf);
		if (!checkAllowedFile(type)) {
			throw new Exception("不允许上传该类型文件！");
		}
		
		StringBuffer buf = new StringBuffer(uriDirector);
		File file = new File(getAppPath().concat(buf.toString()));
		if (!file.isDirectory()) {
			file.mkdirs();
		}
		buf.append(suffixPath(type, "yyyyMMDDHHmmss"));

		writeFile(mf.getInputStream(), getAppPath().concat(buf.toString()));
		return buf.toString();
	}
	
	/**
	 * 生成指定长度的由数字和大小写字母组成的随机字符串
	 * @param length
	 * @return
	 */
	public static String getCharAndNumr(int length) {
		String val = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char) (choice + random.nextInt(26));
			} else if ("num".equalsIgnoreCase(charOrNum)) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	}
	
	/**
	 * 保存文件
	 * 
	 * @param mf
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(MultipartFile mf) throws Exception {
		String path = saveFile(mf, prefixPath());
		return path;
	}

}
