package org.jiumao.wechatMall.entity;

import org.apache.commons.lang3.StringUtils;
import org.jiumao.mall.db.DateUtils;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "OauthClientDetails")
public class OauthClientDetails implements Serializable {
    @Id
    private String clientId;
    private String resourceIds;
    private String clientSecret;

    /**
     * Available values: read,write
     */
    private String scope;

    /**
     * grant types include
     * "authorization_code", "password", "assertion", and "refresh_token".
     * Default value is "authorization_code,refresh_token".
     */
    private String authorizedGrantTypes = "authorization_code,refresh_token";

    /**
     * The re-direct URI(s) established during registration (optional, comma separated).
     */
    private String webServerRedirectUri;

    /**
     * Authorities that are granted to the client (comma-separated). Distinct from the authorities
     * granted to the user on behalf of whom the client is acting.
     * <p/>
     * For example: ROLE_USER
     */
    private String authorities;

    /**
     * The access token validity period in seconds (optional).
     * If unspecified a global default will be applied by the token services.
     * Unit: second
     */
    private Integer accessTokenValidity;

    @Version
    private Long version;
    /**
     * The refresh token validity period in seconds (optional).
     * If unspecified a global default will  be applied by the token services.
     * Unit: second
     */
    private Integer        refreshTokenValidity;
    private String         additionalInformation;
    @CreatedDate
    private Date createTime = DateUtils.now();
    private Integer        archived;
    /**
     * The client is trusted or not. If it is trust, will skip approve step
     * default false.
     */
    private Integer        trusted;
    private String         autoapprove;
    public OauthClientDetails() {
        super();
    }

    public String clientId() {
        return clientId;
    }

    public String resourceIds() {
        return resourceIds;
    }


    public String clientSecret() {
        return clientSecret;
    }


    public String scope() {
        return scope;
    }


    public String authorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String authorities() {
        return authorities;
    }


    public Integer accessTokenValidity() {
        return accessTokenValidity;
    }

    public Integer refreshTokenValidity() {
        return refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return this.additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public Date createTime() {
        return createTime;
    }

    public Integer archived() {
        return archived;
    }


    public Integer trusted() {
        return trusted;
    }

    public String autoapprove() {
        return autoapprove;
    }


    public ClientDetails toClientDetails() {
        BaseClientDetails clientDetails = new BaseClientDetails(clientId, resourceIds, scope, authorizedGrantTypes, authorities, webServerRedirectUri);
        clientDetails.setClientSecret(clientSecret);

        if (StringUtils.isNotEmpty(additionalInformation)) {
            clientDetails.addAdditionalInformation("information", additionalInformation);
        }
        clientDetails.setAccessTokenValiditySeconds(accessTokenValidity);
        clientDetails.setRefreshTokenValiditySeconds(refreshTokenValidity);

        return clientDetails;
    }

    public OauthClientDetails clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public OauthClientDetails clientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
        return this;
    }

    public OauthClientDetails resourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
        return this;
    }

    public OauthClientDetails authorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
        return this;
    }

    public OauthClientDetails scope(String scope) {
        this.scope = scope;
        return this;
    }

    public OauthClientDetails webServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
        return this;
    }

    public OauthClientDetails authorities(String authorities) {
        this.authorities = authorities;
        return this;
    }

    public OauthClientDetails accessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
        return this;
    }

    public OauthClientDetails refreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
        return this;
    }

    public OauthClientDetails trusted(Integer trusted) {
        this.trusted = trusted;
        return this;
    }

    public OauthClientDetails additionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
        return this;
    }

    public OauthClientDetails autoapprove(String autoapprove) {
        this.autoapprove = autoapprove;
        return  this;
    }

    @Override
    public String toString() {
        return "OauthClientDetails{" +
                "clientId='" + clientId + '\'' +
                ", resourceIds='" + resourceIds + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", scope='" + scope + '\'' +
                ", authorizedGrantTypes='" + authorizedGrantTypes + '\'' +
                ", webServerRedirectUri='" + webServerRedirectUri + '\'' +
                ", authorities='" + authorities + '\'' +
                ", accessTokenValidity=" + accessTokenValidity +
                ", version=" + version +
                ", refreshTokenValidity=" + refreshTokenValidity +
                ", additionalInformation='" + additionalInformation + '\'' +
                ", createTime=" + createTime +
                ", archived=" + archived +
                ", trusted=" + trusted +
                ", autoapprove='" + autoapprove + '\'' +
                '}';
    }
}
