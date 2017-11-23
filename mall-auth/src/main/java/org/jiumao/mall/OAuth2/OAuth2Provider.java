package org.jiumao.mall.OAuth2;

import org.jiumao.mall.OAuth2.client.ClientToken;
import org.jiumao.mall.OAuth2.code.ReturnCode;
import org.jiumao.mall.OAuth2.code.CodeToken;
import org.jiumao.mall.OAuth2.implicit.ImplicitToken;
import org.jiumao.mall.OAuth2.password.PasswdToken;


public interface OAuth2Provider {

    ReturnCode giveCode(String clientId);


    CodeToken hasCode(String code);


    ImplicitToken giveToken(String client_id);


    PasswdToken login(String username, String password);


    PasswdToken refresh(String client_id, String client_secret, String refresh_token);


    ClientToken client(String client_id, String client_secret);

}
