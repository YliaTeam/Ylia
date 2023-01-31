package org.yliadevelopment.network.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.yliadevelopment.network.IService;
import org.yliadevelopment.network.server.handler.ProxyServerHandler;

public class ProxyServer implements IService {
    String serverAddress;
    int serverPort;

    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ChannelFuture channel;


    public ProxyServer(String serverAddress, int serverPort) {
        this.serverPort = serverPort;
        this.serverAddress = serverAddress;
    }

    public void start() {
        Bootstrap b = new Bootstrap()
                .group(this.workerGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ProxyServerHandler());

        this.channel = b.bind(this.serverAddress, this.serverPort).channel().closeFuture();
    }

    public void waitFinish() throws RuntimeException {
        if (this.channel == null) {
            throw new RuntimeException("Not started");
        }

        while (!this.channel.isDone()) {
        }
    }

    public void shutdown() {

    }
}
