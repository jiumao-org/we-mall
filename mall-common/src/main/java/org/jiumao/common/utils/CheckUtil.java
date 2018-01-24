package org.jiumao.common.utils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckUtil {

    public static boolean isEmpty(List<?> list) {
        return null == list || Collections.emptyList() == list;
    }

    public static boolean isEmpty(Set<?> set) {
        return null == set || Collections.emptySet() == set;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || Collections.emptyMap() == map;
    }

    public static <T> boolean isEmpty(T[] array) {
        return null == array || 0 == array.length;
    }
}
