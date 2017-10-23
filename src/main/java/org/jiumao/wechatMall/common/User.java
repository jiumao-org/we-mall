package org.jiumao.wechatMall.common;
/**
 * 商城普通用户
 * @author ppf@jiumao.org
 * @date 2017年10月23日
 */
public class User {

	private Long id;// 
	private Long userId;//用户编号唯一并且不变，用于关联其它业务
	private Long updateTime;// 时间戳
	private Long createTime;
	private String userName;
	private String password;
	
	private Integer phone;
	private String email;
	private String contact;// QQ，WeChat，Facebook or Google
	private String content;
	private String value;//补充信息，如：VIP 等
	
	public User() {
		// do nothing for reflect
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
	
}
