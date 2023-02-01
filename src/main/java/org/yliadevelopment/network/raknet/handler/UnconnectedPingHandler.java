package org.yliadevelopment.network.raknet.handler;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.raknet.RaknetSocket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPingPacket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPongPacket;

import java.util.Random;

public class UnconnectedPingHandler extends PacketHandler {

    @PacketHandlerEntry
    public void handler(UnconnectedPingPacket packet, RaknetSocket socket) {
        var pongPacket = new UnconnectedPongPacket(new byte[0x200]);
        var random = new Random();

        pongPacket.serverName = "Bro";
        pongPacket.serverId = random.nextLong();
        pongPacket.pingId = random.nextLong();


    }

}
