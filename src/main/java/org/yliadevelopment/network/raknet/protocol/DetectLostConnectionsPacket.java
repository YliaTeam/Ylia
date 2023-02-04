package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class DetectLostConnectionsPacket extends RaknetPacket {

    public static final int NETWORK_ID = 0x04;

    public DetectLostConnectionsPacket(byte[] buffer) {
        super(buffer);
    }

    @Override
    public void encode() {
        this.writeUnsignedByte(NETWORK_ID);
        this.resetPointer();
    }

    @Override
    public void decode() {
        this.resetOffset();
    }
}
