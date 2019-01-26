package com.ws.app.client;

import com.ws.app.client.handler.TestHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyTCPClient {


    @PostConstruct
    public void startClient() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        // 1.指定线程模型
        bootstrap.group(workerGroup);
        // 2.指定 IO 类型为 NIO
        bootstrap.channel(NioSocketChannel.class);
        // 3.IO 处理逻辑
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) {
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024 * 8));
                ch.pipeline().addLast(new TestHandler());
            }
        });
        // 4.建立连接
        this.connect(bootstrap, "827551331wyw.eicp.net", 47918);
    }

    private void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else {
                System.err.println("连接失败，开始重连");
                connect(bootstrap, host, port);
                Thread.sleep(3000);
                System.out.println("休眠3秒钟后再次尝试重连.");
            }
        });
    }

    public static void main(String[] args) {
        new NettyTCPClient().startClient();
    }
}
