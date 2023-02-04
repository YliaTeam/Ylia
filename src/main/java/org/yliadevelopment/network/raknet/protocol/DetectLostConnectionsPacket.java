package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class DetectLostConnectionsPacket extends RaknetPacket {

    public static final byte NETWORK_ID = (byte) 0x04;

    public DetectLostConnectionsPacket() {
        super(new byte[] {NETWORK_ID});
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
