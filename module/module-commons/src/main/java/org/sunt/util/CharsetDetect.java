package org.sunt.util;

import org.mozilla.universalchardet.UniversalDetector;

public class CharsetDetect {

    private static final ThreadLocal<UniversalDetector> detectorThreadLocal = new ThreadLocal<>();

    public static String detect(byte[] bytes, int offset, int length) {
        UniversalDetector detector;
        if ((detector = detectorThreadLocal.get()) == null) {
            detector = new UniversalDetector(null);
            detectorThreadLocal.set(detector);
        }
        detector.handleData(bytes, offset, length);
        detector.dataEnd();
        String charset = detector.getDetectedCharset();
        detector.reset();
        return charset;
    }

    public static String detect(byte[] bytes) {
        return detect(bytes, 0, bytes.length);
    }

    public static String detect(String string) {
        return detect(string.getBytes());
    }

}
