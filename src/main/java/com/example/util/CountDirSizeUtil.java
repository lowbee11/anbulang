package com.example.util;

import java.io.File;

public class CountDirSizeUtil
{
    static long countSize = 0;

    static void countDirSize(String path)
    {
        File file = new File(path);
        String[] list = file.list();
        if (file.isDirectory())
        {
            for (String items : list)
            {
                String subItem = path + File.separator + items;
                countDirSize(subItem);
            }
        } else
        {
            countSize += file.length();
        }
    }

    public static long getCountSize(String path)
    {
        countSize = 0;
        countDirSize(path);
        return countSize / 1024;
    }
}
