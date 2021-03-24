package org.sunt.util;

public class ZWCCipher {

    private ZWCCipher() { }

    /**
     *  ZWSP \u200b &#8203;
     *  ZWNJ \u200c &#8204;|&zwnj;
     *  ZWJ  \u200d &#8205;|&zwj;
     *  LRM  \u200e &#8206;|&lrm;|&#x200E;
     *  RLM  \u200f &#8207;|&rlm;|&#x200F;
     *  BOM  \ufeff
     */
    private static final byte[] map = new byte[]{(byte) '\u200b', (byte) '\u200c', (byte) '\u200d', (byte) '\u200e', (byte) '\u200f', (byte) '\uFEFF'};

    private static final byte start = (byte) '\u200c';

    private static byte map(int index) {
        return (byte) (start + index);
    }

    private static byte reMap(byte byte0, byte byte1, byte byte2, byte byte3) {
        return (byte) (((byte0-start)<<6) + ((byte1-start)<<4) + ((byte2-start)<<2) + (byte3-start));
    }

    public static byte[] encode(byte[] plain) {
        byte[] result = new byte[plain.length*4];
        for (int i = 0; i < plain.length; i++) {
            result[4*i] = map(plain[i]>>6);
            result[4*i+1] = map(plain[i]>>4&3);
            result[4*i+2] = map(plain[i]>>2&3);
            result[4*i+3] = map(plain[i]&3);
        }
        return result;
    }

    public static byte[] decode(byte[] cipher) {
        byte[] result = new byte[cipher.length/4 + cipher.length%4];
        int i = 0;
        for (; i < cipher.length/4; i++) {
            result[i] = reMap(cipher[4*i], cipher[4*i+1], cipher[4*i+2], cipher[4*i+3]);
        }
        for (int j = 0; j < cipher.length%4; j++ ) {
            result[i++] = cipher[4*i+j];
        }
        return result;
    }

    public static String encode(String plain) {
        return new String(encode(plain.getBytes()));
    }

    public static String decode(String cipher) {
        return new String(decode(cipher.getBytes()));
    }

}
