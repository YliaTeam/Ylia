package org.yliadevelopment.network.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.yliadevelopment.logger.MainLogger;

public class ProxyServerHandler extends SimpleChannelInboundHandler<DatagramPacket>
{
    MainLogger logger = MainLogger.get();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        logger.info("Found packet: %s", msg.toString());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Server handler is active");
        super.channelActive(ctx);
    }
}
