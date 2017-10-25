package org.jiumao.wechatMall.entity;
public class Privilege {
    private Object id;
    private Integer roleId;//角色标号
    private String privilege;//角色拥有的权限之一
    private String detail;//权限描述
    public Privilege() {
        super();
    }
    public Privilege(Object id,Integer roleId,String privilege,String detail) {
        super();
        this.id = id;
        this.roleId = roleId;
        this.privilege = privilege;
        this.detail = detail;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
