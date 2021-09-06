package com.example.util;

import java.io.File;

public class GetFileListUtil
{
    private String filePath = "C:" + File.separator + "Users" + File.separator + "Lenovo" + File.separator + "IdeaProjects" + File.separator + "springboot-mybatis-demo" + File.separator + "src" + File.separator + "main" + File.separator + "resources";
    private File file = null;
    private File[] files = null;

    public GetFileListUtil(String Dir)
    {
        file = new File(filePath + File.separator + Dir);
        files = file.listFiles();
//        System.out.println(file.getPath());
    }

    public File[] getFiles()
    {
        return files;
    }

//    public static void main(String[] args)
//    {
//        File[] files = new GetFileList("/public").getFiles();
//        for (File file:files
//             )
//        {
//            System.out.println(file.getPath());
//        }
//    }
}
