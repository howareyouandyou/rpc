package com.yyl.rpc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射工具类
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 20:28
 */
public class ReflectionUtils {
    /**
     * 根据class创建对象
     *
     * @param clazz 待创建对象的类
     * @return 创建好的对象
     * @param <T> 对象类型
     * @throws IllegalAccessException
     */
    public static <T> T newInstance(Class<T> clazz) throws IllegalStateException
    {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个class的共有方法
     *
     * @param clazz
     * @return 当前类声明的共有（public）方法
     */
    public static Method[] getPublicMethods(Class clazz)
    {
        Method[] methods=clazz.getDeclaredMethods();//返回当前类的所有方法，但还包括private、public等等
        List<Method> pmethods=new ArrayList<>();
        //把public的方法过滤出来放到pmethods中
        for(Method m:methods)
        {
            if(Modifier.isPublic(m.getModifiers()))
            {
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     *
     * @param obj 被调用方法的对象
     * @param method 被调用的方法
     * @param args 方法的参数
     * @return 返回结果
     * @throws IllegalStateException
     */
    public static Object invoke(Object obj,Method method,Object... args)throws IllegalStateException
    {
        try {
            return method.invoke(obj,args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
