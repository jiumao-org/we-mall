package org.jiumao.wechatMall.entity;
public class Role {
    private Object id;
    private String name;//角色名
    private Integer parentId;//上级id
    private String description;//角色描述
    public Role() {
        super();
    }
    public Role(Object id,String name,Integer parentId,String description) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.description = description;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
