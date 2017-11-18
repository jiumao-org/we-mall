package org.jiumao.wechatMall.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

@Document(collection = "OauthCode")
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 2775272095671208572L;

    @CreatedDate
    private Date createTime;
    @Id
    private String code;
    @Version
    private Long version;

    private byte[] authenticationBytes;

    public OauthCode() {
        super();
    }

    public String code() {
        return code;
    }

    public OauthCode code(String code) {
        this.code = code;
        return this;
    }
    public Date createTime() {
        return createTime;
    }

    public Long version() {
        return version;
    }
    public OAuth2Authentication authentication() {
        return SerializationUtils.deserialize(authenticationBytes);
    }

    public OauthCode authentication(OAuth2Authentication authentication) {
        this.authenticationBytes = SerializationUtils.serialize(authentication);
        return this;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
                "createTime=" + createTime +
                ", code='" + code + '\'' +
                ", version=" + version +
                ", authenticationBytes=" + Arrays.toString(authenticationBytes) +
                '}';
    }
}
