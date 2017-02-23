package com.xuema.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class PictureUtil {
	public static String savePicture(CommonsMultipartFile uploadFile){
		String filePath = XuemaUtil.XUEMA_HOME + java.io.File.separator + "images"+java.io.File.separator;
        String uploadFileName = uploadFile.getOriginalFilename();
        int extPos = uploadFileName.lastIndexOf(".");
        String extension = uploadFileName.substring(extPos + 1);
        
        if(CacheConstants.extensionList.contains(extension)){
            String fileName= null;
            fileName = MD5Utils.getMD5(UUID.randomUUID().toString()) + "." + extension;
            String url="/images/"+fileName;
            String savedFileName = filePath+"/" +fileName;
            File savedFile = new File(savedFileName);    
            
            try {
                uploadFile.getFileItem().write(savedFile);
                return url;
              
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
	}
	
	public static String savePictureWithWater(CommonsMultipartFile uploadFile) throws IOException{
	    String url = savePicture(uploadFile);
	    String filePath = XuemaUtil.XUEMA_HOME + url;
	    String water = XuemaUtil.XUEMA_HOME + "/images/water/waterLessAll.png";
	    ImageUtil.addWater2(filePath, water, filePath, 0, 0.15f);
	    return url;
	}
	
	public static String getLocalImgUrl(String img){
        return XuemaUtil.XUEMA_HOME + java.io.File.separator + img;
    }

    public static String downloadImg(String url, String fileName) throws Exception{
        String filePath = XuemaUtil.XUEMA_HOME + java.io.File.separator + "images"+java.io.File.separator;
        String savedFileName = filePath+"/" +fileName;
        String serverUrl="/images/"+fileName;
        doDownloadImg(url, savedFileName);
        return serverUrl;
    }
    
    private static void doDownloadImg(String url, String fileName) throws Exception{
        File f = new File(fileName);
        if (!f.exists()){
            try {
                FileUtils.copyURLToFile(new URL(url), new File(fileName), 5000, 5000);
            } catch (Exception e){
                //ignore
            }
        }
    }
}
