package org.yliadevelopment.network;

public final class BinaryHelper {

    private BinaryHelper() {
    }

    public static boolean readBoolean(byte b) {
        return b == 1;
    }

    public static int readUnsignedByte(byte b) {
        return b & 0xff;
    }

    public static int readUnsignedShort(byte[] buf) {
        return ((buf[0] & 0xFF) << 8) +
                ((buf[1] & 0xFF));
    }

    public static short readShort(byte[] buf) {
        return (short) readUnsignedShort(buf);
    }

    public static int readUnsignedLShort(byte[] buf) {
        return ((buf[1] & 0xFF) << 8) +
                ((buf[0] & 0xFF));
    }

    public static short readLShort(byte[] buf) {
        return (short) readUnsignedLShort(buf);
    }

    public static int readTriad(byte[] buf) {
        return readInt(new byte[]{
                0x00,
                buf[0],
                buf[1],
                buf[2]
        });
    }

    public static int readLTriad(byte[] buf) {
        return readInt(new byte[]{
                buf[0],
                buf[1],
                buf[2],
                0x00
        });
    }

    public static int readInt(byte[] buf) {
        return ((buf[0] & 0xFF) << 24) +
                ((buf[1] & 0xFF) << 16) +
                ((buf[2] & 0xFF) << 8) +
                ((buf[3] & 0xFF));
    }

    public static int readLInt(byte[] buf) {
        return ((buf[3] & 0xFF) << 24) +
                ((buf[2] & 0xFF) << 16) +
                ((buf[1] & 0xFF) << 8) +
                ((buf[0] & 0xFF));
    }

    public static float readFloat(byte[] buf) {
        return Float.intBitsToFloat(readInt(buf));
    }

    public static float readLFloat(byte[] buf) {
        return Float.intBitsToFloat(readLInt(buf));
    }

    public static long readLong(byte[] buf) {
        return ((long) (buf[0] & 0xFF) << 56) +
                ((long) (buf[1] & 0xFF) << 48) +
                ((long) (buf[2] & 0xFF) << 40) +
                ((long) (buf[3] & 0xFF) << 32) +
                ((long) (buf[4] & 0xFF) << 24) +
                ((buf[5] & 0xFF) << 16) +
                ((buf[6] & 0xFF) << 8) +
                ((buf[7] & 0xFF));
    }

    public static long readLLong(byte[] buf) {
        return ((long) (buf[7] & 0xFF) << 56) +
                ((long) (buf[6] & 0xFF) << 48) +
                ((long) (buf[5] & 0xFF) << 40) +
                ((long) (buf[4] & 0xFF) << 32) +
                ((long) (buf[3] & 0xFF) << 24) +
                ((buf[2] & 0xFF) << 16) +
                ((buf[1] & 0xFF) << 8) +
                ((buf[0] & 0xFF));
    }

    public static double readDouble(byte[] buf) {
        return Double.longBitsToDouble(readLong(buf));
    }

    public static double readLDouble(byte[] buf) {
        return Double.longBitsToDouble(readLLong(buf));
    }

    public static byte writeBoolean(boolean bool) {
        return (byte) (bool ? 0x01 : 0x00);
    }

    public static byte writeUnsignedByte(int b) {
        return (byte) (b & 0xFF);
    }

    public static byte[] writeShort(int s) {
        s &= 0xFFFF;

        return new byte[]{
                (byte) ((s >> 8) & 0xFF),
                (byte) ((s) & 0xFF)
        };
    }

    public static byte[] writeLShort(int s) {
        s &= 0xFFFF;

        return new byte[]{
                (byte) ((s) & 0xFF),
                (byte) ((s >> 8) & 0xFF)
        };
    }

    public static byte[] writeTriad(int t) {
        return new byte[]{
                (byte) ((t >> 16) & 0xFF),
                (byte) ((t >> 8) & 0xFF),
                (byte) ((t) & 0xFF)
        };
    }

    public static byte[] writeLTriad(int t) {
        return new byte[]{
                (byte) ((t) & 0xFF),
                (byte) ((t >> 8) & 0xFF),
                (byte) ((t >> 16) & 0xFF)
        };
    }

    public static byte[] writeInt(int i) {
        return new byte[]{
                (byte) ((i >> 24) & 0xFF),
                (byte) ((i >> 16) & 0xFF),
                (byte) ((i >> 8) & 0xFF),
                (byte) ((i) & 0xFF)
        };
    }

    public static byte[] writeLInt(int i) {
        return new byte[]{
                (byte) ((i) & 0xFF),
                (byte) ((i >> 8) & 0xFF),
                (byte) ((i >> 16) & 0xFF),
                (byte) ((i >> 24) & 0xFF)
        };
    }

    public static byte[] writeFloat(float f) {
        return writeInt(Float.floatToIntBits(f));
    }

    public static byte[] writeLFloat(float f) {
        return writeLInt(Float.floatToIntBits(f));
    }

    public static byte[] writeLong(long l) {
        return new byte[]{
                (byte) ((l >> 56) & 0xFF),
                (byte) ((l >> 48) & 0xFF),
                (byte) ((l >> 32) & 0xFF),
                (byte) ((l >> 24) & 0xFF),
                (byte) ((l >> 16) & 0xFF),
                (byte) ((l >> 8) & 0xFF),
                (byte) ((l) & 0xFF)
        };
    }

    public static byte[] writeLLong(long l) {
        return new byte[]{
                (byte) ((l) & 0xFF),
                (byte) ((l >> 8) & 0xFF),
                (byte) ((l >> 16) & 0xFF),
                (byte) ((l >> 24) & 0xFF),
                (byte) ((l >> 32) & 0xFF),
                (byte) ((l >> 40) & 0xFF),
                (byte) ((l >> 48) & 0xFF),
                (byte) ((l >> 56) & 0xFF)
        };
    }

    public static byte[] writeDouble(double d) {
        return writeLong(Double.doubleToLongBits(d));
    }

    public static byte[] writeLDouble(double d) {
        return writeLLong(Double.doubleToLongBits(d));
    }

}
