package com.yyl.rpc.client;

import com.yyl.rpc.codec.Decoder;
import com.yyl.rpc.codec.Encoder;
import com.yyl.rpc.common.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 15:47
 */
public class RpcClient {
    private RpcClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RpcClient()
    {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig config)
    {
        this.config=config;
        this.encoder= ReflectionUtils.newInstance(this.config.getEncoderClass());
        this.decoder= ReflectionUtils.newInstance(this.config.getDecoderClass());
        this.selector= ReflectionUtils.newInstance(this.config.getSelectorClass());

        //void init(List<Peer> peers,int count,Class<? extends  TransportClient> clazz)
        this.selector.init(
                this.config.getServers(),
                this.config.getConnectCount(),
                this.config.getTransportClass()
        );
    }

    /*
    获取接口的代理对象
     */
    public <T> T getProxy(Class<T> clazz)
    {
        return (T) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz,encoder,decoder,selector)
        );
    }




}
