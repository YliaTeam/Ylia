package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.BinaryStream;

public abstract class RaknetPacket extends BinaryStream {

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
