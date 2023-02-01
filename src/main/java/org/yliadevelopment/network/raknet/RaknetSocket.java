package org.yliadevelopment.network.raknet;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

import org.jetbrains.annotations.NotNull;
import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.BinaryStream;
import org.yliadevelopment.network.raknet.handler.PacketHandler;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.DatagramPacket;

public class RaknetSocket {

    static MainLogger logger = MainLogger.get();

    private SocketAddress address;
    private DatagramSocket socket;

    public RaknetSocket(SocketAddress address) {
        this.address = address;
        
        try {
            this.socket = new DatagramSocket(address);
        } catch (SocketException exception) {
            logger.error("Could not create datagram socket: %s", exception.toString());
        }
    }

    public void sendPacket(RaknetPacket raknetPacket, SocketAddress address) {
        var packet = new DatagramPacket(raknetPacket.getBuffer(), raknetPacket.getBuffer().length);

        this.socket.send(packet);
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

            var packetId = stream.readUnsignedByte();

            if (!RaknetPacket.PACKETS.containsKey(packetId)) {
                logger.error("Packet id not handled: %d", packetId);
                continue;
            }

            var raknetPacketClazz = RaknetPacket.PACKETS.get(packetId);
            RaknetPacket raknetPacket;

            try {
                raknetPacket = raknetPacketClazz
                    .getConstructor(byte[].class)
                    .newInstance((Object) buffer);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                logger.error("Could not instantiate %s: %s", raknetPacketClazz.getName(), e.toString());
                continue;
            }

            raknetPacket.decode();

            PacketHandler.invokeHandlerFor(this, raknetPacket);
        }
    }
}
