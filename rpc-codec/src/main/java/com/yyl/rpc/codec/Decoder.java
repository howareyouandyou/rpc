package com.yyl.rpc.codec;

/**
 * 反序列化（二进制数组t->对象Objec）
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 20:59
 */
public interface Decoder {
    <T> T decode(byte[] bytes,Class<T> clazz);
}
