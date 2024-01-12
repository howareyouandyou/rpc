package com.yyl.rpc.client;

import com.yyl.rpc.Request;
import com.yyl.rpc.Response;
import com.yyl.rpc.ServiceDescriptor;
import com.yyl.rpc.codec.Decoder;
import com.yyl.rpc.codec.Encoder;
import com.yyl.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 调用远程服务的代理类
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 15:56
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector selector) {
        this.clazz=clazz;
        this.encoder=encoder;
        this.decoder=decoder;
        this.selector=selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //创建request
        Request request=new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz,method));
        request.setParameters(args);

        //invokeRemote通过网络传输调用远程服务，获得response
        Response response=invokeRemote(request);
        if(response==null || response.getCode()!=0)
        {
            throw new IllegalStateException("fail to invoke remote: "+response);
        }

        //如果成功返回response的data
        return response.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient client=null;
        Response response=null;

        try {
            //选择一个client
            client=selector.select();

            //request序列化
            byte[] outBytes= encoder.encode(request);
            InputStream revice= client.write(new ByteArrayInputStream(outBytes));

            //读InputStream并反序列化，存入response中
            byte[] inBytes=new byte[revice.available()];
            IOUtils.readFully(revice,inBytes,0,revice.available());
//            byte[] inBytes = IOUtils.readFully(revice , revice.available());

            response=decoder.decode(inBytes, Response.class);

        }
        catch (IOException e)
        {
            log.warn(e.getMessage(),e);
            response=new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error: "+e.getClass()+" : "+e.getMessage());

        }
        finally {
            if(client!=null)
            {
                selector.release(client);
            }
        }
        return response;
    }
}
