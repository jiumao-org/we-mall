package org.jiumao.mall.db;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * Created by 13682 on 2017/11/14.
 */
public abstract class PasswordHandler {
    private PasswordHandler() {
    }


    public static String md5(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, null);
    }
}
