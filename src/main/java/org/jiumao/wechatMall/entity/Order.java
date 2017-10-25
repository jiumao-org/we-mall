package org.jiumao.wechatMall.entity;
public class Order {
    private Object id;
    private Integer userId;//订单也是用户级别的
    private Long orderId;//订单号，应当有程序生成唯一且不可变
    private java.util.Date creatTime;//创建时间，用于报表统计
    private Integer status;//订单状态，取消，未付款，正在派单，送达，确认收货等
    private Integer orderGoodId;//订单详情id，每个订单由多个商品组成
    private Double totalPrice;//订单总价
    private Integer num;//订单商品数
    public Order() {
        super();
    }
    public Order(Object id,Integer userId,Long orderId,java.util.Date creatTime,Integer status,Integer orderGoodId,Double totalPrice,Integer num) {
        super();
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.creatTime = creatTime;
        this.status = status;
        this.orderGoodId = orderGoodId;
        this.totalPrice = totalPrice;
        this.num = num;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public java.util.Date getCreatTime() {
        return this.creatTime;
    }

    public void setCreatTime(java.util.Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderGoodId() {
        return this.orderGoodId;
    }

    public void setOrderGoodId(Integer orderGoodId) {
        this.orderGoodId = orderGoodId;
    }

    public Double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNum() {
        return this.num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

}
