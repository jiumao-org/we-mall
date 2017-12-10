package org.jiumao.wechatMall.entity;
public class Staff {
    private Object id;
    private String userName;
    private String password;
    private Integer roleId;//角色id，目前角色客服、配送员、管理员等
    public Staff() {
        super();
    }
    public Staff(Object id,String userName,String password,Integer roleId) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roleId = roleId;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
