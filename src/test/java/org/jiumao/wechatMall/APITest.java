package org.jiumao.wechatMall;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.jiumao.wechatMall.common.domain.Gender;
import org.jiumao.wechatMall.dao.UserDao;
import org.jiumao.wechatMall.entity.User;
import org.jiumao.wechatMall.service.UserService;
import org.jiumao.wechatMall.service.Impl.UserServiceImpl;
import org.jiumao.wechatMall.util.IdGenerator;
import org.jiumao.wechatMall.util.JsonUtil;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class APITest {

	String url = "http://localhost:8080/wechat-mall/admin/index";
	User u = new User(2, IdGenerator.getUserId(), 18067677912L,
			"18010065474", "ppf@jiumao.org", Gender.MAN.toString(),
			"China,Beijing,Chaoyang", "Pengpeng", "q294881866");
	@Test
	public void postTest() throws Exception {

		StringBuilder sb = new StringBuilder();
		sb.append("userName").append("=").append(u.getUserName()).append("&")
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
	
	

}
