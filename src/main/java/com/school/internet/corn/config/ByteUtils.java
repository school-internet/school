package com.school.internet.corn.config;

public class ByteUtils {

    private static final char[] CHARS = new char[]{'0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String getHexString(byte[] b) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                builder.append("0");
            }
            builder.append(hex);
        }
        String result = builder.toString();
        result = result.toUpperCase();
        return result;
    }

    public static byte[] getByteArray(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        for (int i = 0; i < CHARS.length; i++) {
            if (c == CHARS[i]) {
                return (byte) i;
            }
        }
        return Byte.MIN_VALUE;
    }

}
