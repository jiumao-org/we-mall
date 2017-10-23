package org.jiumao.wechatMall.admin.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jiumao.wechatMall.common.Authentication;
import org.jiumao.wechatMall.common.User;
import org.jiumao.wechatMall.util.JsonUtil;
import org.springframework.stereotype.Component;

@Component
@Path("/admin/index")
public class IndexResource {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String helloWorld(
			@QueryParam("msg") @DefaultValue("hello world") String msg) {
		return msg;
	}

	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@Context Authentication auth,
			@FormParam("userName") String userName,
			@FormParam("password") String password,
			@FormParam("verificationCode") String verificationCode) {
		// check not null
		User u = new User();//dao 应该查询
		
		auth.setId(""+u.getId());
		return JsonUtil.bean2Json(u);
	}

	@GET
	@Path("info/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String info(@PathParam("id") String name,
			@Context Authentication auth) {
		return "{}";
	}

}
