package com.yyl.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的反序列化实现
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 21:16
 */
public class JSONDecoder implements Decoder{
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes,clazz);
    }
}
