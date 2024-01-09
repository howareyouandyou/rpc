package com.yyl.rpc.server;

import com.yyl.rpc.codec.Decoder;
import com.yyl.rpc.codec.Encoder;
import com.yyl.rpc.codec.JSONDecoder;
import com.yyl.rpc.codec.JSONEncoder;
import com.yyl.rpc.transport.HTTPTransportServer;
import com.yyl.rpc.transport.TransportServer;
import lombok.Data;

/**
 * server配置
 * 使用的网络模块，使用的序列化模块，监听的端口
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/9 15:42
 */
@Data
public class RPCServerConfig {
    private Class<? extends TransportServer> transportClass=HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass= JSONEncoder.class;
    private Class<? extends Decoder> decoderClass= JSONDecoder.class;
    private int port=3000;
}
