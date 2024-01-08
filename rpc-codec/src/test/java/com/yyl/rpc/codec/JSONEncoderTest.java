package com.yyl.rpc.codec;

import junit.framework.TestCase;
import org.junit.Test;

public class JSONEncoderTest extends TestCase {

    @Test
    public void testEncode() throws Exception{
        Encoder encoder=new JSONEncoder();

        TestBean bean=new TestBean();
        bean.setName("yyl");
        bean.setAge(20);

        byte[] bytes= encoder.encode(bean);
        assertNotNull(bytes);
    }
}