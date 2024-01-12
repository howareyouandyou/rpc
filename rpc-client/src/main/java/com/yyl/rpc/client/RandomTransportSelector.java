package com.yyl.rpc.client;

import com.yyl.rpc.Peer;
import com.yyl.rpc.common.utils.ReflectionUtils;
import com.yyl.rpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 随机选择一个网络端点
 *
 * @author 86152
 * @version 1.0
 * Create by 2024/1/12 15:25
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector{
    /*
    已经连接好的client
     */
    private List<TransportClient> clients;

    public RandomTransportSelector()
    {
        clients=new ArrayList<>();
    }

    /*
    每个方法加上synchronized保证线程安全
     */

    /*
    初始化
     */
    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count=Math.max(count,1);

        for(Peer peer:peers)
        {
            for (int i = 0; i < count; i++) {
                TransportClient client= ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}",peer);
        }
    }

    /*
    从client池中随机取一个数返回
     */
    @Override
    public synchronized TransportClient select() {
        int i=new Random().nextInt(clients.size());
        return clients.remove(i);
    }

    /*
    添加
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    /*
    关闭所有的client
     */
    @Override
    public synchronized void close() {
        for(TransportClient client:clients)
        {
            client.close();
        }
        clients.clear();
    }
}
