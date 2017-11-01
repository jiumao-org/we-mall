package org.jiumao.wechatMall.common.auth.oAuth2;

import javax.annotation.Resource;

import org.jiumao.wechatMall.common.domain.RoleUserDetails;
import org.jiumao.wechatMall.entity.Role;
import org.jiumao.wechatMall.entity.User;
import org.jiumao.wechatMall.service.RoleService;
import org.jiumao.wechatMall.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 实现用户名、手机号、邮箱 登陆
 * 
 * @author ppf@jiumao.org
 * @date 2017年10月31日
 */
@Service("userAuthService")
public class UserAuthService implements AuthenticationProvider {

	@Resource
	UserService userService;
	@Resource
	RoleService roleService;

	public UserDetails login(String username, String password) {
		User user = new User();
		user.setPassword(password);
		String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		String ph = "^[1][3578]\\d{9}$";
		if (username.matches(em)) {// 邮箱登录
			user.setEmail(username);
		} else if (username.matches(ph)) {// 手机号登录
			user.setPhone(Long.valueOf(username));
		} else {// 就是用户名登录
			user.setUsername(username);
		}
		user = userService.selectUserByObj(user);
		if (null == user) { return new RoleUserDetails(); }
		Role role = roleService.selectRoleById(user.getRoleId());
		RoleUserDetails userDetails = new RoleUserDetails(user, role.getName());
		return userDetails;
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();
		UserDetails user = login(username, password);

		if (null == user.getAuthorities()) {
			return new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword());
		} else {
			return new UsernamePasswordAuthenticationToken(user.getUsername(),
					user.getPassword(), user.getAuthorities());
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	
    private SaltSource saltSource;

    private UserDetailsService userDetailsService;

	public SaltSource getSaltSource() {
		return saltSource;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
