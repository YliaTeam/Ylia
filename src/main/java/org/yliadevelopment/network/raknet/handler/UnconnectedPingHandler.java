package org.yliadevelopment.network.raknet.handler;

import org.yliadevelopment.network.raknet.RaknetSocket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPingPacket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPongPacket;

public class UnconnectedPingHandler extends PacketHandler {

    @PacketHandlerEntry
    public void handler(UnconnectedPingPacket packet, RaknetSocket socket) {
        var pongPacket = new UnconnectedPongPacket(new byte[0x100]);

        pongPacket.serverName = "MCPE;Bro;0.0.0;0.15.10.0;1000;0";
        pongPacket.serverId = 0xcafebabe;
        pongPacket.pingId = System.currentTimeMillis();
        pongPacket.setAddress(packet.getAddress(), packet.getPort());

        socket.sendPacket(pongPacket);
    }

}
