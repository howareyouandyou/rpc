package com.yyl.rpc.codec;

/**
 * 序列化（对象Object->二进制数组）
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 20:59
 */
public interface Encoder {
    byte[] encode(Object obj);
}
