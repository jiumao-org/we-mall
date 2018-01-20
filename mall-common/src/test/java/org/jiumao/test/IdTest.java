package org.jiumao.test;

import static org.junit.Assert.*;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class IdTest {
    public static void main(String[] args) {
        long out = 0;
        long preSixBit = 0b111111;
        long userId = 51234;
        int d = 12;
        for (int i = Integer.MAX_VALUE-1; i < Integer.MAX_VALUE; i++) {
            userId = userId % 10000;
            long sixBit = i & preSixBit;
            long day = d << 6;
            long remainBit = (i ^ preSixBit) << 15;
            out = userId + (sixBit + day + remainBit) * 10000;
        }
        System.out.println(out);

    }
    
    
    @Test
    public void testID() throws Exception {
        System.out.println(RandomUtils.nextLong(0xFFFFFF, 0xFFFFFF_F));
    }
}
