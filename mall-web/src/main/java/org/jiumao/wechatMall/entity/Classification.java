package org.jiumao.wechatMall.entity;
public class Classification {
    private Object id;
    private Integer classificationId;//商品分类编号
    private String name;//分类名
    private Integer parentId;//上级分类，如为顶级则空
    private String other;//其它拓展信息
    public Classification() {
        super();
    }
    public Classification(Object id,Integer classificationId,String name,Integer parentId,String other) {
        super();
        this.id = id;
        this.classificationId = classificationId;
        this.name = name;
        this.parentId = parentId;
        this.other = other;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getClassificationId() {
        return this.classificationId;
    }

    public void setClassificationId(Integer classificationId) {
        this.classificationId = classificationId;
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

    public String getOther() {
        return this.other;
    }

    public void setOther(String other) {
        this.other = other;
    }

}
