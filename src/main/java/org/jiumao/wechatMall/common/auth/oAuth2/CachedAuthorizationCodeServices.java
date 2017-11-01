package org.jiumao.wechatMall.common.auth.oAuth2;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

import static org.jiumao.wechatMall.common.constant.CacheConstants.*;

/**
 * 2016/7/23
 *
 * @author Shengzhao Li
 */
public class CachedAuthorizationCodeServices extends JdbcAuthorizationCodeServices {


    public CachedAuthorizationCodeServices(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    @Cacheable(value = AUTHORIZATION_CODE_CACHE, key = "#code")
    protected void store(String code, OAuth2Authentication authentication) {
        super.store(code, authentication);
    }

    @Override
    @CacheEvict(value = AUTHORIZATION_CODE_CACHE, key = "#code")
    public OAuth2Authentication remove(String code) {
        return super.remove(code);
    }
}
