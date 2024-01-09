package com.yyl.rpc.server;

import com.yyl.rpc.Request;
import com.yyl.rpc.ServiceDescriptor;
import com.yyl.rpc.common.utils.ReflectionUtils;
import org.junit.Before;
import org.junit.Test;


import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest{

    ServiceManager sm;

    @Before
    public void init()
    {
        sm=new ServiceManager();
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class,bean);
    }

    @Test
    public void testRegister() throws Exception {
        TestInterface bean=new TestClass();
        sm.register(TestInterface.class,bean);
    }

    public void testLookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class,method);

        Request request = new Request();
        request.setServiceDescriptor(sdp);

        ServiceInstance sis = sm.lookup(request);
        assertNotNull(sis);
    }
}