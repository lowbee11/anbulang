package com.example.util;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UploadUtil
{
    private String[] fileNames = null;

    public UploadUtil(HttpServletRequest request) throws IOException
    {
        MultipartHttpServletRequest params = (MultipartHttpServletRequest) request;
        String path = "C:" + File.separator + "Users" + File.separator + "Lenovo" + File.separator + "IdeaProjects" + File.separator + "springboot-mybatis-demo" + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        List<MultipartFile> multipartFiles = params.getFiles("uploadFile");//多文件
        String dir = params.getParameter("dir");
        fileNames = new String[multipartFiles.size()];
        for (int i = 0; i < multipartFiles.size(); i++)
        {
            MultipartFile file = multipartFiles.get(i);
//            if (!file.isEmpty())
//            {
            fileNames[i] = file.getOriginalFilename();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                    new FileOutputStream(
                            new File(path + File.separator + dir + File.separator + fileNames[i])
                    )
            );
            bufferedOutputStream.write(file.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
//            }
        }
    }

    public String[] getFileNames()
    {
        return fileNames;
    }
}
