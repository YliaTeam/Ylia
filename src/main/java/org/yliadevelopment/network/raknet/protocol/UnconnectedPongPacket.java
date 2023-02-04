package org.yliadevelopment.network.raknet.protocol;

import org.yliadevelopment.network.raknet.RaknetPacket;

public class UnconnectedPongPacket extends RaknetPacket {

    public static final byte NETWORK_ID = (byte) 0x1c;

    public long pingId;
    public long serverId;
    public byte[] offlineMessageDataId = {
            (byte) 0x00, (byte) 0xff, (byte) 0xff, (byte) 0x00,
            (byte) 0xfe, (byte) 0xfe, (byte) 0xfe, (byte) 0xfe,
            (byte) 0xfd, (byte) 0xfd, (byte) 0xfd, (byte) 0xfd,
            (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78
    };
    public String serverName;

    public UnconnectedPongPacket() {
        super(new byte[] {NETWORK_ID});
    }

    @Override
    public void encode() {
        this.resetPointer();

        this.writeUnsignedByte(NETWORK_ID);
        this.writeLong(this.pingId);
        this.writeLong(this.serverId);
        this.write(this.offlineMessageDataId);
        this.writeString(this.serverName);
    }

    @Override
    public void decode() {
        this.resetOffset();

        this.pingId = this.readLong();
        this.serverId = this.readLong();
        this.offlineMessageDataId = this.read(16);
        this.serverName = this.readString();
    }
}
