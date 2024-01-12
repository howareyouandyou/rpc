package com.yyl.rpc.transport;

import com.yyl.rpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 22:09
 */
public class HTTPTransportClient implements TransportClient{
    private String url;


    @Override
    public void connect(Peer peer) {
        this.url="http://"+peer.getHost()+":"+peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection httpConn=(HttpURLConnection) new URL(url).openConnection();
            //设置可以输入输出,post
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            httpConn.setUseCaches(false);
            httpConn.setRequestMethod("POST");

            //连接，输入的数据流复制到httpConn的输出流中，即将数据发送到server
            httpConn.connect();
            IOUtils.copy(data,httpConn.getOutputStream());

            int resultCode=httpConn.getResponseCode();
            if(resultCode==HttpURLConnection.HTTP_OK)//http状态码200，返回服务器的输入流，否则返回错误流
            {
                return httpConn.getInputStream();
            }
            else
            {
                return httpConn.getErrorStream();
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void close() {

    }
}
