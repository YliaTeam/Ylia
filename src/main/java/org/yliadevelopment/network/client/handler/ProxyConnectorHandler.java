package org.yliadevelopment.network.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.yliadevelopment.logger.MainLogger;

import java.net.DatagramPacket;

public class ProxyConnectorHandler extends SimpleChannelInboundHandler<DatagramPacket> {
    MainLogger logger = MainLogger.get();


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        String message = packet.toString();
        logger.info("Received Message : " + message);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Channel is active");

        super.channelActive(ctx);
    }

}
