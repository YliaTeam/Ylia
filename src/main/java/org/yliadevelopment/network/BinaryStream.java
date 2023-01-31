package org.yliadevelopment.network;

public class BinaryStream {

    private int offset = 0x00;
    private int pointer = 0x00;
    private byte[] buffer;

    public BinaryStream() {
        this(new byte[0xff], 0x00);
    }

    public BinaryStream(byte[] buffer) {
        this(buffer, 0x00);
    }

    public BinaryStream(byte[] buffer, int offset) {
        this.buffer = buffer;
        this.offset = offset;
    }

    public int getPointer() {
        return this.pointer;
    }

    public int getOffset() {
        return this.offset;
    }

    public byte[] getBuffer() {
        return this.buffer;
    }

    public void resetPointer() {
        this.pointer = 0x00;
    }

    public void resetOffset() {
        this.offset = 0x00;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public void setBuffer(byte[] buffer) {
        this.buffer = buffer;
    }

    public byte read() {
        return this.buffer[this.offset++];
    }

    public byte[] read(int c) {
        byte[] b = new byte[c];

        for (int i = 0; i < c; i++)
            b[i] = this.read();

        return b;
    }

    public boolean readBoolean() {
        return BinaryHelper.readBoolean(this.read());
    }

    public int readUnsignedByte() {
        return BinaryHelper.readUnsignedByte(this.read());
    }

    public int readUnsignedShort() {
        return BinaryHelper.readUnsignedShort(this.read(2));
    }

    public short readShort() {
        return BinaryHelper.readShort(this.read(2));
    }

    public int readUnsignedLShort() {
        return BinaryHelper.readUnsignedLShort(this.read(2));
    }

    public short readLShort() {
        return BinaryHelper.readLShort(this.read(2));
    }

    public int readTriad() {
        return BinaryHelper.readTriad(this.read(3));
    }

    public int readLTriad() {
        return BinaryHelper.readLTriad(this.read(3));
    }

    public int readInt() {
        return BinaryHelper.readInt(this.read(4));
    }

    public int readLInt() {
        return BinaryHelper.readLInt(this.read(4));
    }

    public float readFloat() {
        return BinaryHelper.readFloat(this.read(4));
    }

    public float readLFloat() {
        return BinaryHelper.readLFloat(this.read(4));
    }

    public long readLong() {
        return BinaryHelper.readLong(this.read(8));
    }

    public long readLLong() {
        return BinaryHelper.readLLong(this.read(8));
    }

    public double readDouble() {
        return BinaryHelper.readDouble(this.read(8));
    }

    public double readLDouble() {
        return BinaryHelper.readLDouble(this.read(8));
    }

    public void write(byte b) {
        this.buffer[this.pointer++] = b;
    }

    public void write(byte[] buf) {
        for (byte b : buf) {
            this.write(b);
        }
    }

    public void writeBoolean(boolean b) {
        this.write(BinaryHelper.writeBoolean(b));
    }

    public void writeUnsignedByte(byte b) {
        this.write(BinaryHelper.writeUnsignedByte(b));
    }

    public void writeShort(int s) {
        this.write(BinaryHelper.writeShort(s));
    }

    public void writeLShort(int s) {
        this.write(BinaryHelper.writeLShort(s));
    }

    public void writeInt(int i) {
        this.write(BinaryHelper.writeInt(i));
    }

    public void writeLInt(int i) {
        this.write(BinaryHelper.writeLInt(i));
    }

    public void writeFloat(float f) {
        this.write(BinaryHelper.writeFloat(f));
    }

    public void writeLFloat(float f) {
        this.write(BinaryHelper.writeLFloat(f));
    }

    public void writeLong(long l) {
        this.write(BinaryHelper.writeLong(l));
    }

    public void writeLLong(long l) {
        this.write(BinaryHelper.writeLLong(l));
    }

    public void writeDouble(double d) {
        this.write(BinaryHelper.writeDouble(d));
    }

    public void writeLDouble(double d) {
        this.write(BinaryHelper.writeLDouble(d));
    }

}
