package org.jiumao.wechatMall.entity;
public class User {
    private Object id;
    private Long userId;//用户编号，程序生成且唯一不变
    private Integer phone;//登陆用手机号
    private String password;
    private String email;
    private String gender;
    private String addr;
    private String nick;//昵称可选
    private String userName;
    public User() {
        super();
    }
    public User(Object id,Long userId,Integer phone,String password,String email,String gender,String addr,String nick,String userName) {
        super();
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.addr = addr;
        this.nick = nick;
        this.userName = userName;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPhone() {
        return this.phone;
    }

    public void setPhone(Integer phone) {
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

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
