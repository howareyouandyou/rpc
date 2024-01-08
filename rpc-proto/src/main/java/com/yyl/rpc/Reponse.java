package com.yyl.rpc;

import lombok.Data;

/**
 * 表示RPC的返回
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 20:23
 */
@Data
public class Reponse {
    /**
     * 服务返回编码，0-成功 ，非0失败
     */
    private int code=0;//表示成功与否
    /**
     * 具体的错误信息
     */
    private String message="ok";//返回的错误信息
    /**
     * 返回的数据
     */
    private Object data;
}
