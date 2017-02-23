package com.xuema.util;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VideoUtil {
	public static String saveVideo(CommonsMultipartFile uploadFile){
		String filePath = XuemaUtil.XUEMA_HOME + java.io.File.separator + "video"+java.io.File.separator;
        String uploadFileName = uploadFile.getOriginalFilename();
        int extPos = uploadFileName.lastIndexOf(".");
        String extension = uploadFileName.substring(extPos + 1);
        
        if(CacheConstants.videoExtensionList.contains(extension)){
            //写入本地，注意修改filePath
            String fileName= null;
            fileName = MD5Utils.getMD5(UUID.randomUUID().toString()) + "." + extension;
            String url="/video/"+fileName;
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
}
