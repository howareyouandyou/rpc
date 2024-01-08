package com.yyl.rpc.transport;

import org.eclipse.jetty.http.HttpParser;

/**
 * 1、启动、监听端口
 * 2、接受请求
 * 3、关闭监听
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 22:04
 */
public interface TransportServer {
    /*
    初始化服务器并处理请求
     */
    void init(int port, RequestHandler handler);

    void start();

    void stop();

}
