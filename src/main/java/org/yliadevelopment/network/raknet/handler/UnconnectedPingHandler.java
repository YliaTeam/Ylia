package org.yliadevelopment.network.raknet.handler;

import org.jetbrains.annotations.NotNull;
import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.raknet.RaknetSocket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPingPacket;
import org.yliadevelopment.network.raknet.protocol.UnconnectedPongPacket;

public class UnconnectedPingHandler extends PacketHandler {

    static MainLogger logger = MainLogger.get();

    @PacketHandlerEntry
    public void handler(@NotNull UnconnectedPingPacket packet, @NotNull RaknetSocket socket) {
        var pongPacket = new UnconnectedPongPacket();

        pongPacket.serverName = "MCPE;A RakNet Server;84;0.15.10;32;64;";
        pongPacket.serverId = 0xcafebabeL;
        pongPacket.pingId = packet.pingId;
        pongPacket.setAddress(packet.getAddress(), packet.getPort());

        socket.sendPacket(pongPacket);
    }

}
