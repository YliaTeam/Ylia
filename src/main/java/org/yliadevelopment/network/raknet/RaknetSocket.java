package org.yliadevelopment.network.raknet;

import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.net.SocketException;

import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.BinaryStream;
import org.yliadevelopment.network.raknet.protocol.RaknetPacket;
import org.yliadevelopment.network.raknet.protocol.RaknetPacketList;

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

    public void startListening() {
        byte[] buffer = new byte[0xFFFF];
        DatagramPacket packet = null;

        while (true) {
            packet = new DatagramPacket(buffer, buffer.length);

            try {
                this.socket.receive(packet);
            } catch (IOException e) {
                logger.error("Could not receive packet: %s", e.toString());
                continue;
            }

            BinaryStream stream = new BinaryStream(buffer);

            int packetId = stream.readUnsignedByte();

            if (!RaknetPacketList.PACKETS.containsKey(packetId)) {
                logger.warn("Packet id not handled: %d", packetId);
                continue;
            }

            var raknetPacketClazz = RaknetPacketList.PACKETS.get(packetId);

            try {
                var raknetPacket = raknetPacketClazz
                    .getConstructor(byte[].class)
                    .newInstance(buffer);
                
                raknetPacket.decode();

                printDebugPacketInfo(raknetPacket);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                logger.error("Could not instantiate %s: %s", raknetPacketClazz.getName(), e.toString());
                continue;
            }
        }
    }

    private void printDebugPacketInfo(RaknetPacket raknetPacket) {
        for (Field f : raknetPacket.getClass().getDeclaredFields()) {
            try {
                f.setAccessible(true);

                logger.info("%s: %s = %s", raknetPacket.getClass().getName(), f.getName(), f.get(raknetPacket));
            } catch (Exception ignore) {
                continue;
            }
        }
    }
}
