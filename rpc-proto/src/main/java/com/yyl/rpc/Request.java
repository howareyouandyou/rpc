package com.yyl.rpc;

import lombok.Data;

/**
 * 表示RPC的一个请求
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 20:22
 */
@Data
public class Request {
    private ServiceDescriptor service;
    private Object[] parameters;

}
