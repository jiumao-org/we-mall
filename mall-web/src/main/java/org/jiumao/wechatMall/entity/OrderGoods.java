package org.jiumao.wechatMall.entity;
public class OrderGoods {
    private Object id;
    private Long orderId;//商品订单号
    private Long goodId;//商品号，用于回查商品详情
    private Integer num;//购买数量
    private Double price;//原价
    private Double salePrice;//购买价格
    private String name;//商品名，方便简单查看，不用回查商品
    private Integer classification;//商品分类，用于订单报表统计
    private Double totalPrice;//总价
    public OrderGoods() {
        super();
    }
    public OrderGoods(Object id,Long orderId,Long goodId,Integer num,Double price,Double salePrice,String name,Integer classification,Double totalPrice) {
        super();
        this.id = id;
        this.orderId = orderId;
        this.goodId = goodId;
        this.num = num;
        this.price = price;
        this.salePrice = salePrice;
        this.name = name;
        this.classification = classification;
        this.totalPrice = totalPrice;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodId() {
        return this.goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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
