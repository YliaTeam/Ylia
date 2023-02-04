package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class ConnectedPingPacket extends RaknetPacket {
    
    public static final byte NETWORK_ID = (byte) 0x00;

    private long time;

    public ConnectedPingPacket() {
        super(new byte[] {NETWORK_ID});
    }

    @Override
    public void encode() {
        this.resetPointer();

        this.writeUnsignedByte(NETWORK_ID);
        this.writeLong(this.time);
    }

    @Override
    public void decode() {
        this.resetOffset();

        this.time = this.readLong();
    }

}
