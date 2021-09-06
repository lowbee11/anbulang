package com.example.entity;

import com.example.util.CountDirSizeUtil;

import java.io.File;
import java.text.SimpleDateFormat;

public class MyFile
{
    private String name = null;
    private long size = 0l;
    private String lastModified = null;
    private String dir = null;

    public MyFile()
    {

    }

    public MyFile(File file)
    {
        this.name = file.getName();
        this.size = CountDirSizeUtil.getCountSize(file.getPath());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");//日期格式
        this.lastModified = dateFormat.format(file.lastModified());
        this.dir = "false";
        if (file.isDirectory())
        {
            this.dir = "true";
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getSize()
    {
        return size;
    }

    public void setSize(long size)
    {
        this.size = size;
    }

    public String getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(String lastModified)
    {
        this.lastModified = lastModified;
    }

    public String getDir()
    {
        return dir;
    }

    public void setDir(String dir)
    {
        this.dir = dir;
    }
}
