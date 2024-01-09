package com.yyl.rpc.server;

import com.yyl.rpc.Request;
import com.yyl.rpc.ServiceDescriptor;
import com.yyl.rpc.common.utils.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理rpc暴露的服务
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/9 15:49
 */
@Slf4j
public class ServiceManager {
    /*
    Map：存储method的描述与实例的对应关系，方便通过client的传参进行get
    ServiceDescriptor--->class, method 类和方法
    ServiceInstance----->target, method 需要执行某个method的目标对象，需要执行的method
     */
    private Map<ServiceDescriptor,ServiceInstance> services;

    public ServiceManager()
    {
        this.services=new ConcurrentHashMap<>();
    }
    //注册实现指定接口的类的方法（参数：一个接口类，一个实现了该接口的类的实例）,
    //并且获取之前的ServiceDescriptor实例与ServiceInstance作为键值对的形式存储
    public <T> void register(Class<T> interfaceClass,T bean)
    {
        //通过反射获取该接口类中的所有公共方法
        Method[] methods= ReflectionUtils.getPublicMethods(interfaceClass);
        for(Method method:methods)
        {
            //将每个方法与对应的实例封装成ServiceInstance对象和ServiceDescriptor对象
            ServiceInstance sis=new ServiceInstance(bean,method);
            ServiceDescriptor sdp=ServiceDescriptor.from(interfaceClass,method);

            //最后将这些映射关系存储在Map中
            services.put(sdp,sis);
            log.info("register service: {} {}",sdp.getClazz(),sdp.getMethod());
        }

    }

    public ServiceInstance lookup(Request request)
    {
        ServiceDescriptor sdp=request.getServiceDescriptor();

        return services.get(sdp);
    }


}
