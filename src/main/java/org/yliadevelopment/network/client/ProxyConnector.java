package org.yliadevelopment.network.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.yliadevelopment.network.IService;
import org.yliadevelopment.network.client.handler.ProxyConnectorHandler;

public class ProxyConnector implements IService  {
    int port;
    String address;
    EventLoopGroup workerGroup = new NioEventLoopGroup();
    ChannelFuture channel;

    public ProxyConnector(String address, int port) {
        this.port = port;
        this.address = address;
    }

    public void start() {
        Bootstrap builder = new Bootstrap()
                .group(this.workerGroup)
                .channel(NioDatagramChannel.class)
                .handler(new ProxyConnectorHandler());

        this.channel = builder.connect(this.address, this.port).channel().closeFuture();
    }

    public void waitFinish() {
      if (this.channel == null) {
            throw new RuntimeException("Not started");
        }

      while (!this.channel.isDone()) {}
    }

    public void shutdown() {
        this.workerGroup.shutdownGracefully();
    }
}
