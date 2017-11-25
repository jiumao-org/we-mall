package org.jiumao.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public final class RegexUtils {

    public static boolean isMobileNO(String mobiles) {
        if (null == mobiles)
            return false;
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        System.out.println(m.matches() + "---");

        return m.matches();

    }


    /**
     * 判断是否是姓名(2~7个中文或者3~10个英文)
     */
    public static boolean isName(String name) {
        if (null == name)
            return false;
        String regx = "(([\u4E00-\u9FA5]{2,7})|([a-zA-Z]{3,10}))";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(name);

        return m.find();
    }


    public static boolean isIP(String ip) {
        if (null == ip)
            return false;
        Pattern pattern =
                Pattern
                    .compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
        Matcher matcher = pattern.matcher(ip); // 以验证127.400.600.2为例
        return matcher.find();
    }


    public static boolean isDate(String date) {
        if (null == date)
            return false;
        Pattern pattern =
                Pattern
                    .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher matcher = pattern.matcher(date);
        return matcher.find();
    }


    public static boolean isEmail(String email) {
        if (null == email)
            return false;
        String str =
                "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }


    public static boolean isAge(String age) {
        if (null == age)
            return false;
        String str = "^[0-9]*[1-9][0-9]*$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(age);
        return m.matches();
    }


    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
}
