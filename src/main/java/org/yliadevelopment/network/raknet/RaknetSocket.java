package org.yliadevelopment.network.raknet;

import java.net.*;

import org.jetbrains.annotations.NotNull;
import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.BinaryHelper;
import org.yliadevelopment.network.BinaryStream;
import org.yliadevelopment.network.raknet.handler.PacketHandler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RaknetSocket {

    static MainLogger logger = MainLogger.get();

    private final SocketAddress address;
    private DatagramSocket socket;

    public RaknetSocket(SocketAddress address) {
        this.address = address;
        
        try {
            this.socket = new DatagramSocket(null);

            this.socket.setReuseAddress(true);
            this.socket.setBroadcast(true);

            this.socket.setOption(StandardSocketOptions.SO_SNDBUF, 1);
            this.socket.setOption(StandardSocketOptions.SO_RCVBUF, 1);
            this.socket.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            this.socket.setOption(StandardSocketOptions.SO_REUSEPORT, true);

            this.socket.bind(address);
        } catch (Exception exception) {
            logger.error("Could not create or bind socket!: %s", exception.toString());
        }
    }

    public void sendPacket(@NotNull RaknetPacket raknetPacket) {
        raknetPacket.encode();

        var packet = new DatagramPacket(raknetPacket.getBuffer(), raknetPacket.getBuffer().length);
        packet.setAddress(raknetPacket.getAddress());
        packet.setPort(raknetPacket.getPort());

        logger.info("Trying to send %s packet to address %s:%d", raknetPacket.getClass().getName(),
                raknetPacket.getAddress().getHostAddress(),
                raknetPacket.getPort());
        logger.info("Packet buffer: %s", new String(raknetPacket.getBuffer()));

        try {
            this.socket.send(packet);
        } catch (IOException e) {
            logger.error("Could not send packet %s: %s", raknetPacket.getClass().getName(), e.toString());
        }
    }

    public void startListening() {
        var buffer = new byte[0xFFFF];

        while (true) {
            var packet = new DatagramPacket(buffer, buffer.length);

            try {
                this.socket.receive(packet);
            } catch (IOException e) {
                logger.error("Could not receive packet: %s", e.toString());
                continue;
            }

            var stream = new BinaryStream(buffer);

            var packetId = (byte) stream.readUnsignedByte();

            if (!RaknetPacket.PACKETS.containsKey(packetId)) {
                logger.error("Packet id not handled: %d", packetId);
                continue;
            }

            var raknetPacketClazz = RaknetPacket.PACKETS.get(packetId);
            RaknetPacket raknetPacket;

            try {
                raknetPacket = raknetPacketClazz
                    .getConstructor()
                    .newInstance();

                raknetPacket.setBuffer(buffer);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                logger.error("Could not instantiate %s: %s", raknetPacketClazz.getName(), e.toString());
                continue;
            }

            raknetPacket.setAddress(packet.getAddress(), packet.getPort());
            raknetPacket.decode();

            PacketHandler.invokeHandlerFor(this, raknetPacket);
        }
    }
}
