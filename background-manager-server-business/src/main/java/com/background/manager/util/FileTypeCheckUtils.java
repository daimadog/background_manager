package com.background.manager.util;

import com.background.manager.exception.BackgroundManagementResultCodeEnum;
import com.background.manager.exception.BadRequestException;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 校验文件格式工具类
 * @Author: 杜黎明
 * @Date: 2022/11/02 15:58:04
 * @Version: 1.0.0
 */
public class FileTypeCheckUtils {


    /**
     * Description: 校验图片格式
     * @param url 上传的文件url地址
     * @author 杜黎明
     * @date 2022/10/27 15:02:08
     */
    public static void checkImageFormat(String url) {
        //通过后缀名进行判断
        String type = FileTypeUtils.getFileType(url);
        List<String> imageExtension = Arrays.asList(MimeTypeUtils.IMAGE_EXTENSION);
        if (!imageExtension.contains(type)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.IMAGE_TYPE_INCORRECT_ERROR);
        }
    }

    /**
     * Description: 校验图片格式
     * @param url 上传的文件url地址
     * @author 杜黎明
     * @date 2022/10/27 15:02:08
     */
    public static void checkVideoFormat(String url) {
        //通过后缀名进行判断
        String type = FileTypeUtils.getFileType(url);
        List<String> imageExtension = Arrays.asList(MimeTypeUtils.VIDEO_EXTENSION);
        if (!imageExtension.contains(type)){
            throw new BadRequestException(BackgroundManagementResultCodeEnum.IMAGE_TYPE_INCORRECT_ERROR);
        }



    }


}
