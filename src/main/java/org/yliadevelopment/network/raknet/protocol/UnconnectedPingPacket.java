package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class UnconnectedPingPacket extends RaknetPacket {

    public static final byte NETWORK_ID = (byte) 0x01;

    public long pingId;
    public byte[] magic = {
            (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0x00,
            (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe,
            (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd,
            (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78
    };

    public UnconnectedPingPacket() {
        super(new byte[] {NETWORK_ID});
    }

    @Override
    public void encode() {
        this.resetPointer();

        this.writeUnsignedByte(NETWORK_ID);
        this.writeLong(this.pingId);
        this.write(this.magic);
    }

    @Override
    public void decode() {
        this.resetOffset();

        this.read(); // NETWORK_ID
        this.pingId = this.readLong();
        this.magic = this.read(16);
    }

}
