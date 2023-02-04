package org.yliadevelopment.network.raknet;

import org.reflections.Reflections;
import org.yliadevelopment.logger.MainLogger;
import org.yliadevelopment.network.BinaryStream;

import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class RaknetPacket extends BinaryStream {

    static MainLogger logger = MainLogger.get();

    public static final Map<Integer, Class<? extends RaknetPacket>> PACKETS = new LinkedHashMap<>();

    static {
        for (Class<? extends RaknetPacket> clazz : new Reflections(RaknetPacket.class.getPackageName())
                .getSubTypesOf(RaknetPacket.class)) {

            try {
                var idField = clazz.getField("NETWORK_ID");

                if (!Modifier.isStatic(idField.getModifiers())) {
                    throw new IllegalArgumentException(); /* The field needs to be static */
                }

                idField.setAccessible(true);

                var id = (int) idField.get(null);

                PACKETS.put(id, clazz);
            } catch (NoSuchFieldException | IllegalAccessException | SecurityException | IllegalArgumentException e) {
                logger.error("The packet class %s does not have a NETWORK_ID field or it is not acessible!", clazz.getName());
            }
        }
    }

    private InetAddress address;
    private int port;

    public InetAddress getAddress() {
        return this.address;
    }

    public int getPort() {
        return port;
    }

    public void setAddress(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public RaknetPacket(byte[] buffer) {
        super(buffer);
    }

    public static final int NETWORK_ID = -0x01;

    /**
     * Encode the actual data into a binary buffer
     */
    public abstract void encode();

    /**
     * Decode the binary buffer into actual data
     */
    public abstract void decode();

}
