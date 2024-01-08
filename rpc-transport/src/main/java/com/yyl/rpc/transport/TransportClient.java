package com.yyl.rpc.transport;

import com.yyl.rpc.Peer;

import java.io.InputStream;

/**
 * 1、创建连接
 * 2、发送数据，并且等待响应
 * 3、关闭连接
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 21:29
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}
