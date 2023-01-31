package org.yliadevelopment.network.raknet.protocol;

public class ConnectedPingPacket extends RaknetPacket {
    
    public static final int NETWORK_ID = 0x00;

    private long time;

    @Override
    public void encode() {
        this.resetPointer();

        this.writeLong(this.time);
    }

    @Override
    public void decode() {
        this.resetOffset();

        this.time = this.readLong();
    }

}
