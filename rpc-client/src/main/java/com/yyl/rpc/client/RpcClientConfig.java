package com.yyl.rpc.client;

import com.yyl.rpc.Peer;
import com.yyl.rpc.codec.Decoder;
import com.yyl.rpc.codec.Encoder;
import com.yyl.rpc.codec.JSONDecoder;
import com.yyl.rpc.codec.JSONEncoder;
import com.yyl.rpc.transport.HTTPTransportClient;
import com.yyl.rpc.transport.TransportClient;
import lombok.Data;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * rpc client配置类
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 15:36
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass= HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass= JSONEncoder.class;
    private Class<? extends Decoder> decoderClass= JSONDecoder.class;
    private Class<? extends TransportSelector> selectorClass= RandomTransportSelector.class;
    private int connectCount=1;
    private List<Peer> servers= Arrays.asList(
            new Peer("127.0.0.1",3000)
    );
}
