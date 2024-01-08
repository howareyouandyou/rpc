package com.yyl.rpc.common.utils;

import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Method;

public class ReflectionUtilsTest extends TestCase {

    /*
    测试创建类
     */
    @Test
    public void testNewInstance() throws Exception {
        TestClass t=ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(t);
    }

    /*
    测试获取public方法
     */
    @Test
    public void testGetPublicMethods()throws Exception {
        Method[] methods=ReflectionUtils.getPublicMethods(TestClass.class);
        assertEquals(1,methods.length);

        String mname=methods[0].getName();
        assertEquals("b",mname);
    }

    /*
    测试调用对象的指定方法
     */
    @Test
    public void testInvoke() throws Exception{
        Method[] methods=ReflectionUtils.getPublicMethods(TestClass.class);
        Method method=methods[0];

        TestClass t=new TestClass();
        Object r=ReflectionUtils.invoke(t,method);

        assertEquals("b",r);
    }
}