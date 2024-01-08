package com.yyl.rpc.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author 86152
 * @version 1.0
 * Create by 2024/1/8 22:22
 */
@Slf4j
public class HTTPTransportServer implements TransportServer{
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler=handler;
        this.server=new Server(port);

        //servlet接受请求
        ServletContextHandler ctx=new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder holder=new ServletHolder(new RequestServlet());
        //"/*"作为Servlet的映射路径，表示接受所有的请求
        ctx.addServlet(holder,"/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }

    }

    class RequestServlet extends HttpServlet
    {
        //重写doPost方法来处理客户端发起的HTTP POST请求
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect");

            InputStream in= req.getInputStream();
            OutputStream out =resp.getOutputStream();

            //调用了传入的RequestHandler来处理请求
            if(handler!=null)
            {
                handler.onRequest(in,out);
            }

            out.flush();
        }
    }
}
