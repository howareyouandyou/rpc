package com.yyl.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 处理网络请求的handler
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 22:07
 */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream toResp);
}
