package com.yyl.rpc.example;

import com.yyl.rpc.client.RpcClient;
import com.yyl.rpc.client.RpcClientConfig;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 16:40
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client=new RpcClient(new RpcClientConfig());
        CalcService service=client.getProxy(CalcService.class);

        int r1 = service.add(1,2);
        int r2 = service.minus(10,8);

        System.out.println(r1);
        System.out.println(r2);
    }
}
