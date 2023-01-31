package org.yliadevelopment.network.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.State;

import java.util.NoSuchElementException;

public class ProxyConnectorHandler extends SimpleChannelInboundHandler<DatagramPacket> implements Runnable {
    MainLogger logger = MainLogger.get();
    State state;
    ChannelHandlerContext ctx;

    public ProxyConnectorHandler(State state) {
        this.state = state;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) {
        logger.info("Received Message : " + packet.toString());
        if (packet.content().isReadable()) {
            state.serverQueue.add(packet.copy());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Connector is active");
        super.channelActive(ctx);
        this.ctx = ctx;

        var thread = new Thread(this);
        thread.setName("Connector Thread");
        thread.start();
    }


    @Override
    public void run() {
        while (true) {
            try {
                DatagramPacket packet = state.connectorQueue.remove();
                if (packet.content().isReadable()) {
                    DatagramPacket newPacket = new DatagramPacket(packet.content(), state.source);

                    logger.info("Found packet 2.0: %s", packet.toString());
                    ctx.writeAndFlush(newPacket);
                }
            } catch (NoSuchElementException ignored) {

            }

            if (2 != 2) {
                break;
            }
        }
    }
}
