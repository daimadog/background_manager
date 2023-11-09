package com.background.manager.service.impl;

import com.background.manager.model.dto.request.news.DownLoadRequest;
import com.background.manager.service.FileService;
import com.background.manager.util.FileTypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public String upload(MultipartFile multipartFiles, String dirPath) {
        String originalFilename = multipartFiles.getOriginalFilename();
        //按照UUID格式生成新的文件名
        String pikId = UUID.randomUUID().toString().replaceAll("-", "");
        String fileType = FileTypeUtils.getFileType(originalFilename);
        String newFileName = pikId + "." + fileType;
        //保存到本地文件夹
        File file = new File(dirPath);
        if (!file.exists()) {
            boolean mkdirs = file.mkdirs();
            if (!mkdirs) {
                log.error("创建文件夹失败");
            }
        }
        try {
            InputStream inputStream = multipartFiles.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(dirPath + newFileName);
            int copy = FileCopyUtils.copy(inputStream, fileOutputStream);
            log.info("上传成功！");
            return newFileName;
        } catch (IOException e) {
            log.error("文件上传异常");
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean download(DownLoadRequest request) {
        //本地保存文件地址
        String localPath = request.getLocalPath();
        String localFileName = request.getLocalFileName();
        //下载文件的地址
        String downloadFile = request.getDownloadUrl();
        FileOutputStream fos=null;
        BufferedInputStream bis=null;
        boolean flag=false;
        try {
            URL url = new URL(downloadFile);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.connect();
            bis=new BufferedInputStream(connection.getInputStream());
            File file=new File(request.getLocalPath());
            if (!file.exists()){
                boolean mkdirs = file.mkdirs();
                if (!mkdirs){
                    log.error("创建文件夹失败");
                }
            }
            //下载的文件名
            String filePathName=localPath+File.separator+localFileName;
            byte[] buf = new byte[1024];
            int size;
            fos = new FileOutputStream(filePathName);
            while ((size = bis.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
            flag = true;
            log.info("文件下载成功,文件路径[" + filePathName + "]");
        } catch (IOException e) {
            log.info("文件下载异常");
            throw new RuntimeException(e);
        }finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                log.error("关流异常", e);
                e.printStackTrace();
            }
        }
        return flag;
    }

    @Override
    public boolean delete(String imageUrl) {
        boolean flag = false;
        //根据路径创建文件对象
        File file = new File(imageUrl);
        //路径是个文件且不为空时删除文件
        if (file.isFile() && file.exists()) {
            flag = file.delete();
        }
        //删除失败时，返回false
        return flag;
    }

}
