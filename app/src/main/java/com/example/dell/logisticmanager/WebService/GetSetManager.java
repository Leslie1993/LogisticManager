package com.example.dell.logisticmanager.WebService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by dell on 2016/5/14.
 */
public class GetSetManager {

    public static void setFieldValue(Object target, String fname, Class ftype,
                                     Object fvalue) { //设置字段值  如：username 字段,setUsername(String username)
        if (target == null
                || fname == null
                || "".equals(fname)
                || (fvalue != null && !ftype.isAssignableFrom(fvalue.getClass()))) {//如果类型不匹配，直接退出
            return;
        }
        Class clazz = target.getClass();
        try {  //先通过setXxx()方法设置类属性值
            String methodname="set"  + Character.toUpperCase(fname.charAt(0)) + fname.substring(1);
            System.out.println(methodname);
            Method method = clazz.getDeclaredMethod(methodname, ftype); //获取定义的方法
            if (!Modifier.isPublic(method.getModifiers())) {   //设置非共有方法权限
                method.setAccessible(true);
            }
            method.invoke(target, fvalue);  //执行方法回调
        } catch (Exception me) {//如果set方法不存在，则直接设置类属性值

            try {
                Field field = clazz.getDeclaredField(fname);   //获取定义的类属性
                if (!Modifier.isPublic(field.getModifiers())) {   //设置非共有类属性权限
                    field.setAccessible(true);
                }
                field.set(target, fvalue); //设置类属性值

            } catch (Exception fe) {

            }
        }
    }

}
