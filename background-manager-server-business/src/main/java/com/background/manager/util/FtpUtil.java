package com.background.manager.util;

import com.background.manager.exception.BadRequestException;
import com.background.manager.exception.enums.ResultCodeEnum;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.*;
import java.util.*;

/**
 * @Description: FtpUtil工具类
 * @Author: 杜黎明
 * @Date: 2022/11/02 11:12:41
 * @Version: 1.0.0
 */
public class FtpUtil {

    private static ChannelSftp sftp = null;

    private static Session session=null;

    private static final Logger log = LoggerFactory.getLogger(FtpUtil.class);

    /**
     * Description: 连接sftp服务器
     * @param host 主机ip地址
     * @param port 端口
     * @param username 用户名
     * @param password 密码
     * @author 杜黎明
     * @date 2022/11/08 15:49:58
     */
    public static void connect(String host, int port, String username, String password){
        JSch jSch = new JSch();
        try {
            Session schSession = jSch.getSession(username, host, port);
            schSession.setPassword(password);
            Properties sshConfig = new Properties();
            //严格主机密钥检查
            sshConfig.put("StrictHostKeyChecking", "no");
            schSession.setConfig(sshConfig);
            //开启sshSession链接
            schSession.connect();
            //获取sftp通道
            Channel channel = schSession.openChannel("sftp");
            //开启
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            log.info(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 关闭sftp服务器
     * @author 杜黎明
     * @date 2022/11/08 15:53:42
     */
    public static void  close() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
            }
        }
    }

    public static  void mkdirDir(String[] dirs, String tempPath, int length, int index) {
        // 以"/a/b/c/d"为例按"/"分隔后,第0位是"";故下标从1开始
        index++;
        if (index < length) {
            // 目录不存在，则创建文件夹
            tempPath += "/" + dirs[index];
        }
        try {
            sftp.cd(tempPath);
            if (index < length) {
                mkdirDir(dirs, tempPath, length, index);
            }
        } catch (SftpException ex) {
            try {
                sftp.mkdir(tempPath);
                sftp.cd(tempPath);
            } catch (SftpException e) {
                e.printStackTrace();

            }
            mkdirDir(dirs, tempPath, length, index);
        }
    }

    /**
     * Description: 上传文件
     * @param basePath 基本路径
     * @param filename 文件名
     * @param input    输入
     * @author 杜黎明
     * @date 2022/11/08 15:55:28
     */
    public static void uploadFile(String basePath, String filename, InputStream input) {
        try {
            sftp.cd(basePath);
            sftp.put(input,filename);
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建目录
     * @param createpath
     * @return
     */
    public static boolean createDir(String createpath)
    {
        try
        {
            if (isDirExist(createpath))
            {
                sftp.cd(createpath);
                return true;
            }
            String pathArry[] = createpath.split("/");
            StringBuffer filePath = new StringBuffer("/");
            for (String path : pathArry)
            {
                if (path.equals(""))
                {
                    continue;
                }
                filePath.append(path + "/");
                if (isDirExist(filePath.toString()))
                {
                    sftp.cd(filePath.toString());
                }
                else
                {
                    // 建立目录
                    sftp.mkdir(filePath.toString());
                    // 进入并设置为当前目录
                    sftp.cd(filePath.toString());
                }
            }
            sftp.cd(createpath);
            return true;
        }
        catch (SftpException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断目录是否存在
     * @param directory
     * @return
     */
    public static boolean isDirExist(String directory)
    {
        boolean isDirExistFlag = false;
        try
        {
            SftpATTRS sftpATTRS = sftp.lstat(directory);
            isDirExistFlag = true;
            return sftpATTRS.isDir();
        }
        catch (Exception e)
        {
            if (e.getMessage().toLowerCase().equals("no such file"))
            {
                isDirExistFlag = false;
            }
        }
        return isDirExistFlag;
    }

    /**
     * Description: 查询服务器下指定目录的所有文件
     * @param directory 指定目录
     * @return {@link List }<{@link String }>
     * @author 杜黎明
     * @date 2022/11/09 09:45:32
     */
    public static List<String> listFiles(String directory){
        List<String> ftpFileNameList=new ArrayList<>();
        ChannelSftp.LsEntry isEntry=null;
        String fileName=null;
        try {
            Vector<ChannelSftp.LsEntry> stpFile = sftp.ls(directory);
            Iterator<ChannelSftp.LsEntry> stpFileIterator = stpFile.iterator();
            while (stpFileIterator.hasNext()){
                isEntry=stpFileIterator.next();
                fileName=isEntry.getFilename();
                ftpFileNameList.add(fileName);
            }
            return ftpFileNameList;
        } catch (SftpException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 删除服务器指定文件名
     * @param fileName 文件名称
     * @author 杜黎明
     * @date 2022/11/09 10:03:02
     */
    public static void  deleteFile(String directory,String fileName){
        if (listFiles(directory).contains(fileName)){
            try {
                sftp.cd(directory);
                sftp.rm(fileName);
                log.info("删除文件名为:"+fileName+"成功");
            } catch (SftpException e) {
                throw new BadRequestException(ResultCodeEnum.DELETE_FAIL.getErrorCode(),e.getMessage());
            }
        }
    }

    /**
     * Description: 下载文件
     * @param remotePath 远程路径
     * @param remoteFileName 远程文件名称
     * @param localPath 本地路径
     * @param localFileName 地文件名称
     * @return {@link Boolean }
     * @author 杜黎明
     * @date 2023/01/10 14:33:05
     */
    public static Boolean download(String remotePath, String remoteFileName, String localPath,String localFileName) {
        String ftpFilePath=remotePath+"/"+remoteFileName;
        String localFilePath =localPath + File.separatorChar+localFileName;
        try {
            sftp.get(ftpFilePath,localFilePath);
            File file = new File(localFilePath);
        if (!file.exists()){
            return false;
        }
        return true;
        } catch (SftpException e) {
            log.info("下载失败");
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 读取文件
     * @param fileUrl 文件绝对路径地址
     * @author 杜黎明
     * @date 2023/01/11 13:37:42
     */
    public static String extractFile(String basePath,String fileUrl) {
        String remoteFileUrl=basePath+"/"+fileUrl;
        File fileName = new File(remoteFileUrl);
        FileInputStream in = null;
        String result="";
        PdfReader pdfReader=null;
        try {
            in = new FileInputStream(fileName);
            pdfReader=new PdfReader(remoteFileUrl);
            pdfReader.setAppendable(true);
            int numberOfPages = pdfReader.getNumberOfPages();
            for (int i = 1; i <numberOfPages+1 ; i++) {
                String textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, numberOfPages);
                result = result + textFromPage + "\n" + "PDF解析第"+ (i)+ "页\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (pdfReader!=null){
                pdfReader.close();
            }
        }
        return result.toString();
    }

    /**
     * Description: 读取文件
     * @param fileUrl 文件绝对路径地址
     * @author 杜黎明
     * @date 2023/01/11 13:37:42
     */
    public static String extractLocalFile(String fileUrl) {
        File fileName = new File(fileUrl);
        FileInputStream in = null;
        String result="";
        PdfReader pdfReader=null;
        try {
            in = new FileInputStream(fileName);
            pdfReader=new PdfReader(fileUrl);
            pdfReader.setAppendable(true);
            int numberOfPages = pdfReader.getNumberOfPages();
            for (int i = 1; i <numberOfPages+1 ; i++) {
                String textFromPage = PdfTextExtractor.getTextFromPage(pdfReader, numberOfPages);
                result = result + textFromPage + "\n" + "PDF解析第"+ (i)+ "页\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (pdfReader!=null){
                pdfReader.close();
            }
        }
        return result.toString();
    }
}
