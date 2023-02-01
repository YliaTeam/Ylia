package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class UnconnectedPingPacket extends RaknetPacket {

    public static final int NETWORK_ID = 0x01;

    public long time;
    public byte[] magic = {
            (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0x00,
            (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe,
            (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd,
            (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78
    };

    public UnconnectedPingPacket(byte[] buffer) {
        super(buffer);
    }

    @Override
    public void encode() {
        this.resetPointer();

        this.writeLong(this.time);
        this.write(this.magic);
    }

    @Override
    public void decode() {
        this.resetOffset();

        this.time = this.readLong();
        this.magic = this.read(16);
    }

}
