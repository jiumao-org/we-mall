package org.jiumao.wechatMall;

import static org.junit.Assert.*;

import org.jiumao.wechatMall.common.domain.Gender;
import org.jiumao.wechatMall.entity.User;
import org.junit.Test;

public class APITest {

	String url = "http://localhost:8080/wechat-mall/admin/index";
	User u = new User(2, 18067677912L, "18010065474", "ppf@jiumao.org",
			Gender.MAN.toString(), "China,Beijing,Chaoyang", "Pengpeng",
			"q294881866", null, null);

	@Test
	public void postTest() throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append("userName").append("=").append(u.getUsername()).append("&")
				.append("password").append("=").append(u.getPassword())
				.append("&").append("phone").append("=").append(u.getPhone())
				.append("&").append("nick").append("=").append(u.getNick())
				.append("&").append("gender").append("=").append(u.getGender())
				.append("&").append("email").append("=").append(u.getEmail())
				.append("&").append("addr").append("=").append(u.getAddr());
		String res = HttpUtil.sendPost(url + "/register", sb.toString());
		res = HttpUtil.sendPost(url + "/login", sb.toString());

		System.out.println(res);
	}

	@Test
	public void oAuth2Test() throws Exception {
		url = "http://localhost:8080/wechat-mall/oauth/token";
		String params = "client_id=mobile-client&client_secret=mobile&grant_type=password&username=q294881866&password=294881866";
		String res = HttpUtil.sendPost(url, params);
		System.out.println(res);
	}
	
	@Test
    public void authTest() throws Exception {
        url = "http://localhost:8080/spring-oauth-server/oauth/token";
        String params="client_id=6361b08fdea6400f93b2eccda8936b32&client_secret=i4KXewMI0u6i8CFEZo10mB2rGzQRXrIv&grant_type=password&scope=read&username=mobile&password=mobile";
        String res = HttpUtil.sendPost(url, params);
        System.out.println(res);
    }

}
