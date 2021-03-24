package org.sunt.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TestZWCCipher {


    @ParameterizedTest
    @CsvSource(value = {"abcde", "你好啊", " 、3地z fe", "/**WFEF=fwf*/"})
    public void testZWCCipher(String plain) {
        System.out.println("PLAIN:" + plain);
        String encoded = ZWCCipher.encode(plain);
        System.out.println("ENCODE:" + encoded);
        String decoded = ZWCCipher.decode(encoded);
        System.out.println("DECODE:" + decoded);
        Assertions.assertEquals(plain, decoded);

    }

}
