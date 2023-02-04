package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class ConnectedPongPacket extends RaknetPacket {

    public static final int NETWORK_ID = 0x03;

    public long pingTime, pongTime;

    public ConnectedPongPacket(byte[] buffer) {
        super(buffer);
    }

    @Override
    public void encode() {
        this.resetPointer();

        this.writeUnsignedByte(NETWORK_ID);
        this.writeLong(this.pingTime);
        this.writeLong(this.pongTime);
    }

    @Override
    public void decode() {
        this.resetOffset();

        this.pingTime = this.readLong();
        this.pongTime = this.readLong();
    }
}
