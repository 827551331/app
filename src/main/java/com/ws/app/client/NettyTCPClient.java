package com.ws.app.client;

import com.ws.app.client.handler.TestHandler;
import com.ws.app.init.AppCache;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Component
public class NettyTCPClient {


//    @PostConstruct
    public void startClient() {
        System.out.println("启动netty客户端，准备连接服务端...");
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
                ch.pipeline().addLast(new LineBasedFrameDecoder(1024 * 800));
                ch.pipeline().addLast(new TestHandler());
            }
        });
        // 4.建立连接
        this.connect(bootstrap, "47.101.161.236", 9000);
    }

    /**
     * 检查连接是否正常
     */
    private void check() {
        System.out.println("启动连接检查功能，检查间隔：30秒");
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        if (AppCache.times != 0L) {
                            long current_times = System.currentTimeMillis();
                            long result = (current_times - AppCache.times) / 1000;
                            if (result > 30) {
                                //重新连接服务端
                                startClient();
                            }
                        }
                        //检查间隔30秒
                        Thread.sleep(30 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                //启动连接检查
                check();
            } else {
                System.err.println("连接失败，开始重连");
                connect(bootstrap, host, port);
                Thread.sleep(3000);
                System.out.println("休眠3秒钟后再次尝试重连.");
            }
        });
    }

    public static void main(String[] args) throws InterruptedException {
//        new NettyTCPClient().startClient();
    }
}
