package com.yyl.rpc.server;

import com.yyl.rpc.Request;
import com.yyl.rpc.common.utils.ReflectionUtils;

/**
 * 调用具体服务
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/9 17:02
 */
public class ServiceInvoker {
    public Object invoke(ServiceInstance service, Request request)
    {
        return ReflectionUtils.invoke(service.getTarget(),service.getMethod(),request.getParameters());
    }
}
