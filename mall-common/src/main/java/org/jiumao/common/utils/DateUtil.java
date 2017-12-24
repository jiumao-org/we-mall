package org.jiumao.common.utils;


import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;


/**
 * 日期操作
 * 
 * @author Vincent
 * @author ppf@jiumao.org
 */
public class DateUtil extends DateUtils {
    private static ThreadLocal<Calendar> calendar = new ThreadLocal<>();

    public static int getDayOfYear() {
        Calendar c = calendar.get();
        if (c == null) {
            c = Calendar.getInstance();
            calendar.set(c);
        }
        Date date = new Date();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_YEAR);
    }
}
