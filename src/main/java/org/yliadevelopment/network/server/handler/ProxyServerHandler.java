package org.yliadevelopment.network.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.State;

import java.util.NoSuchElementException;

public class ProxyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> implements Runnable {
    MainLogger logger = MainLogger.get();
    State state;
    ChannelHandlerContext ctx;

    public ProxyServerHandler(State state) {
        this.state = state;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception {
        if (packet.content().isReadable()) {
            logger.info("Found packet: %s", packet.toString());
            state.connectorQueue.add(packet.copy());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("Server handler is active");
        super.channelActive(ctx);
        this.ctx = ctx;
        var thread = new Thread(this);
        thread.setName("Server thread");
        thread.start();
    }

    @Override
    public void run() {
        logger.info("Server thread started");
        while (true) {
            try {
                DatagramPacket packet = state.serverQueue.remove();
                DatagramPacket newPacket = new DatagramPacket(packet.content(), state.target);
                logger.info("Found packet 3.0: %s", packet.toString());
                ctx.writeAndFlush(newPacket);
            } catch (NoSuchElementException _e) {

            }
        }
    }
}
