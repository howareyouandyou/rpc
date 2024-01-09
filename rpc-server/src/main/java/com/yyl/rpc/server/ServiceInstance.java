package com.yyl.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * 表示一个具体服务
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/9 15:47
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;//服务由哪个对象提供
    private Method method;//对象暴露的方法
}
