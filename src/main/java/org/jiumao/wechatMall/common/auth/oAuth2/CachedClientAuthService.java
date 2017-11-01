package org.jiumao.wechatMall.common.auth.oAuth2;


import static org.jiumao.wechatMall.common.constant.CacheConstants.*;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;




import javax.sql.DataSource;


/**
 * SQL中添加  <i>archived = 0</i> 条件
 * <p/>
 * 增加缓存支持
 * @author ppf@jiumao.org
 * @date 2017年10月31日
 */
public class CachedClientAuthService extends JdbcClientDetailsService {

    private static final String SELECT_CLIENT_DETAILS_SQL = "select client_id, client_secret, resource_ids, scope, authorized_grant_types, " +
            "web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove " +
            "from oauth_client_details where client_id = ? and archived = 0 ";


    public CachedClientAuthService(DataSource dataSource) {
        super(dataSource);
        setSelectClientDetailsSql(SELECT_CLIENT_DETAILS_SQL);
    }


    @Override
    @Cacheable(value = CLIENT_DETAILS_CACHE, key = "#clientId")
    public ClientDetails loadClientByClientId(String clientId) throws InvalidClientException {
        return super.loadClientByClientId(clientId);
    }


    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#clientDetails.getClientId()")
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        super.updateClientDetails(clientDetails);
    }

    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#clientId")
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        super.updateClientSecret(clientId, secret);
    }

    @Override
    @CacheEvict(value = CLIENT_DETAILS_CACHE, key = "#clientId")
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        super.removeClientDetails(clientId);
    }
}