package com.yyl.rpc.codec;

import com.alibaba.fastjson.JSON;

/**
 * 基于json的序列化实现
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 21:13
 */
public class JSONEncoder implements Encoder{

    @Override
    public byte[] encode(Object obj) {
        return JSON.toJSONBytes(obj);//对象转成json字符串的byte数组
    }
}
