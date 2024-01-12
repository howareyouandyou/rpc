package com.yyl.rpc.example;

import com.yyl.rpc.server.RPCServerConfig;
import com.yyl.rpc.server.RpcServer;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 16:40
 */
public class Server {
    public static void main(String[] args) {
        RpcServer server=new RpcServer(new RPCServerConfig());
        server.register(CalcService.class,new CalcServiceImpl());
        server.start();
    }

}
