package org.yliadevelopment.network.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.yliadevelopment.network.client.handler.ProxyConnectorHandler;

public class ProxyConnector {
    int port;
    String address;
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    public ProxyConnector(String address, int port) {
        this.port = port;
        this.address = address;
    }

    public ChannelFuture startup() {
        Bootstrap builder = new Bootstrap()
                .group(this.workerGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ProxyConnectorHandler());

        return builder.connect(this.address, this.port);
    }

    public void shutdown() {
        this.workerGroup.shutdownGracefully();
    }
}
