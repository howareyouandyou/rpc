package com.yyl.rpc.server;

import com.yyl.rpc.Request;
import com.yyl.rpc.Response;
import com.yyl.rpc.codec.Decoder;
import com.yyl.rpc.codec.Encoder;
import com.yyl.rpc.common.utils.ReflectionUtils;
import com.yyl.rpc.transport.RequestHandler;
import com.yyl.rpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/9 17:04
 */
@Slf4j
public class RpcServer {
    private RPCServerConfig config;
    private TransportServer net;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    public RpcServer()
    {

    }

    public RpcServer(RPCServerConfig config)
    {
        this.config=config;

        //net
        this.net= ReflectionUtils.newInstance(config.getTransportClass());
        this.net.init(config.getPort(), this.handler);

        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());

        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();

    }

    public <T> void register(Class<T> interfaceClass,T bean)
    {
        serviceManager.register(interfaceClass,bean);
    }

    public void start()
    {
        this.net.start();
    }

    public void stop()
    {
        this.net.stop();
    }

    private RequestHandler handler=new RequestHandler() {
        //InputStream和OutputStream获取来自Client的数据
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response resp=new Response();


            try {
                //从client传来的byte，将其反序列化
                byte[] inBytes = new byte[recive.available()];
                IOUtils.readFully(recive,inBytes,0,recive.available());

//                byte[] inBytes= new byte[0];
//                inBytes = IOUtils.readFully(recive,recive.available());
                Request request=decoder.decode(inBytes, Request.class);
                log.info("get request: {}",request);

                /*
                client传来request对象，serviceManager注册（register）时
                已经通过 class和实例bean 将 serviceDescriptor与ServiceInstance 用map装配起来

                然后根据request对象查找对应的ServiceInstance（map的键值对）
                 */
                ServiceInstance sis=serviceManager.lookup(request);//request封装了ServiceDescriptor描述字符串

                //反射调用两个参数 方法+实例 刚好是serviceManager的存储了
                //Request对象中包含有Client获取到的实际参数，因此将上述参数一起传递到ServiceInvoker对象进行执行
                Object ret=serviceInvoker.invoke(sis,request);
                resp.setData(ret);

            } catch (Exception e) {
                log.warn(e.getMessage(),e);
                resp.setCode(1);
                resp.setMessage("RpcServer got error: "
                        +e.getClass().getName()
                        +" : "+e.getMessage());
            }
            finally {
                try {
                    byte[] outBytes = encoder.encode(resp);
                    toResp.write(outBytes);
                    log.info("response client");
                } catch (IOException e) {
                    log.warn(e.getMessage(),e);
                }
            }

        }
    };
}
