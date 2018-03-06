package com.running.business.util;

import com.google.common.io.FileBackedOutputStream;
import com.running.business.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * @author sunxiaodong on 2018/3/6 15:43
 */
public class FileUploadUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    public static String uploadFile(MultipartFile uploadFile, String path) {
        String fileName = System.currentTimeMillis() + "_" + uploadFile.getOriginalFilename();
        String filePath = "/"+path+"/" + fileName;
        try {
            File file = new File(filePath);
            InputStream is = uploadFile.getInputStream();
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();
        } catch (Exception e) {
            logger.error("文件保存异常", e);
        }
        return filePath;
    }
}
