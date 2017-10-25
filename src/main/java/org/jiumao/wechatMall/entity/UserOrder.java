package org.jiumao.wechatMall.entity;
public class UserOrder {
    private Object id;
    private Long userid;//用户编号
    private Long orderid;//每个用户有多个订单
    public UserOrder() {
        super();
    }
    public UserOrder(Object id,Long userid,Long orderid) {
        super();
        this.id = id;
        this.userid = userid;
        this.orderid = orderid;
    }
    public Object getId() {
        return this.id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Long getUserid() {
        return this.userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getOrderid() {
        return this.orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

}
