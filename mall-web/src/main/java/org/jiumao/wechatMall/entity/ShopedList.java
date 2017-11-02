package org.jiumao.wechatMall.entity;
public class ShopedList {
    private Object id;
    private Long userId;//每个用户都有未完全交易列表，已经完成订单但没有确认收货
    private Long orderId;//订单编号
    private Integer status;//订单状态
    public ShopedList() {
        super();
    }
    public ShopedList(Object id,Long userId,Long orderId,Integer status) {
        super();
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
        this.status = status;
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

    public Long getOrderId() {
        return this.orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
