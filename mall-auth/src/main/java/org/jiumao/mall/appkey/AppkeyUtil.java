package org.jiumao.mall.appkey;


import org.apache.commons.lang3.RandomUtils;


public final class AppkeyUtil {


    static int startInclusive = 100 * 1000;
    static int endExclusive = Integer.MAX_VALUE >> 11;

    public static final IdGenerator<Integer> DefaultGenerator = () -> {
        return RandomUtils.nextInt(startInclusive, endExclusive);
    };

    

    

}
