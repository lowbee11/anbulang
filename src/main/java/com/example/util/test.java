package com.example.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class test
{
    private int age;
    private String name;

    private test(String name)
    {
        this.name = name;
        System.out.println("My Name is " +
                name);

    }

    private test()
    {

    }

    private void welcome(String tips)
    {
        System.out.println(tips);
    }

    public static void main(String[] args)
    {
        test test = new test();
        Class c = test.getClass();
        String nameVlues = "";
        Field[] fields = c.getDeclaredFields();
        try
        {
            //得到属性
            Field field = fields[1];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();
            //获取属性值
            field.set(test, "123");
            Object value = field.get(test);
            //一个个赋值
            nameVlues += field.getName() + ":" + value + ",";

        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        //获取最后一个逗号的位置
        int lastIndex = nameVlues.lastIndexOf(",");

        //不要最后一个逗号","
        String result = nameVlues.substring(0, lastIndex);
        System.out.println(result);

    }
}
