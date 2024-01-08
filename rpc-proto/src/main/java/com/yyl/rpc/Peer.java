package com.yyl.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 表示网络传输的一个端点
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 20:05
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
