package org.jiumao.wechatMall.common.domain;

import org.codehaus.jackson.map.ObjectMapper;
import org.jiumao.wechatMall.entity.User;
import org.jiumao.wechatMall.util.JsonUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 为每个用户分配权限，默认用户没有特别权限，未来可以实现。
 * <p> 不同角色的用户在数据库中已经分开
 * @author ppf@jiumao.org
 * @date 2017年10月31日
 */
public class RoleUserDetails implements UserDetails {

	private static final long serialVersionUID = 3957586021470480642L;

	/**
	 * 角色权限 前缀
	 * @see org.springframework.security.access.vote.RoleVoter
	 */
	protected static final String ROLE_PREFIX = "ROLE_";
	 /**
     * 用户的授权集合
     */
    protected List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    
	protected User user;


	public RoleUserDetails() {
		this.user = new User();
	}

	public RoleUserDetails(User user,String role) {
		this.user = user;
		this.grantedAuthorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.toUpperCase()));
	}
	
	

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	/* 账户是否未过期 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/* 账户是否未锁定 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/* 密码是否未过期 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/* 账户是否启用,默认true (启用) */
	@Override
	public boolean isEnabled() {
		return true;
	}

	public User user() {
		return user;
	}

	@Override
	public String toString() {
		return JsonUtil.bean2Json(new ObjectMapper(), this);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}
}