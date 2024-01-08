package com.yyl.rpc.codec;

import junit.framework.TestCase;
import org.junit.Test;

public class JSONDecoderTest extends TestCase {

    @Test
    public void testDecode() throws Exception{
        Encoder encoder=new JSONEncoder();

        TestBean bean=new TestBean();
        bean.setName("yyl");
        bean.setAge(20);

        byte[] bytes= encoder.encode(bean);

        Decoder decoder=new JSONDecoder();
        TestBean bean2=decoder.decode(bytes, TestBean.class);

        assertEquals(bean.getName(),bean2.getName());
        assertEquals(bean.getAge(),bean2.getAge());
    }
}