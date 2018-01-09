package org.jiumao.wechatMall.entity;
public class Station {
    private Integer id;
    private String name;
    private Integer parentId;//上级区域的id
    private String detail;//区域描述
    private String workTime;//工作时间段，由前端和程序共同控制格式
    private Integer maximum;//上架最大数量
    public Station() {
        super();
    }
    public Station(Integer id,String name,Integer parentId,String detail,String workTime,Integer maximum) {
        super();
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.detail = detail;
        this.workTime = workTime;
        this.maximum = maximum;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
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

    public String getDetail() {
        return this.detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getWorkTime() {
        return this.workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public Integer getMaximum() {
        return this.maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }

}
