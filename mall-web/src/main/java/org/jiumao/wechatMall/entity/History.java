package org.jiumao.wechatMall.entity;
public class History {
    private Object id;
    private java.util.Date beginTime;//记录该时间历史订单信息，用于分析
    private java.util.Date endTime;//时间段内，商品销售情况，结束时间
    private Long goodId;
    private Integer saleNum;//销量
    private Integer classification;//商品分类
    private Double totalPrice;//总销售额
    public History() {
        super();
    }
    public History(Object id,java.util.Date beginTime,java.util.Date endTime,Long goodId,Integer saleNum,Integer classification,Double totalPrice) {
        super();
        this.id = id;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.goodId = goodId;
        this.saleNum = saleNum;
        this.classification = classification;
        this.totalPrice = totalPrice;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public java.util.Date getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(java.util.Date beginTime) {
        this.beginTime = beginTime;
    }

    public java.util.Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    public Long getGoodId() {
        return this.goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Integer getSaleNum() {
        return this.saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getClassification() {
        return this.classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

}
