package org.jiumao.wechatMall.entity;
public class User {
    private Object id;
    private Long phone;//登陆用手机号
    private String password;
    private String email;
    private String gender;
    private String addr;
    private String nick;//昵称可选
    private String username;
    private Integer roleId;//用户角色，默认是普通用户
    private Long userId;//用户无关的用户id，程序生成唯一不可变，用于与用户相关的业务。防止架构升级用户数据丢失
    public User() {
        super();
    }
    public User(Object id,Long phone,String password,String email,String gender,String addr,String nick,String username,Integer roleId,Long userId) {
        super();
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.addr = addr;
        this.nick = nick;
        this.username = username;
        this.roleId = roleId;
        this.userId = userId;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getPhone() {
        return this.phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getNick() {
        return this.nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
