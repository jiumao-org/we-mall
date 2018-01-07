package org.jiumao.wechatMall.entity;

import java.util.Collections;
import java.util.List;

import org.bson.Document;
import org.jiumao.api.pay.OrderStatus;
import org.jiumao.common.utils.JsonSerializable;


/**
 * json to byte[]
 * 
 * @author ppf@jiumao.org
 * @date 2017/12/10
 */
public class Order extends JsonSerializable {
    private Long id;// 应当有程序生成唯一且不可变
    private Long userId;// 订单也是用户级别的
    private Object orderSerial;// 订单流水号，内部使用
    private Long creatTime;// 创建时间，用于报表统计，时间戳
    private Double totalPrice;// 订单总价
    private OrderStatus.Status status;
    private Integer num;// 订单商品数
    private int deadLine = 3000;// 相对创建时间的过期时间，单位毫秒

    public OrderStatus asOrderStatus() {
        return new OrderStatus(id, userId, creatTime);
    }

    // 商品详情
    private List<OrderGoods> goods ;

    public Order() {
        super();
    }

    public Order(Long id, Long userId, Long orderId, Long creatTime, OrderStatus.Status status, Double totalPrice, Integer num) {
        super();
        this.id = id;
        this.userId = userId;
        this.orderSerial = orderId;
        this.creatTime = creatTime;
        this.status = status;
        this.totalPrice = totalPrice;
        this.num = num;
    }


    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderGoods> getGoods() {
        return goods;
    }

    public void setGoods(List<OrderGoods> goods) {
        this.goods = goods;
    }

    public Object getOrderSerial() {
        return orderSerial;
    }

    public void setOrderSerial(Object orderId) {
        this.orderSerial = orderId;
    }

    public OrderStatus.Status getStatus() {
        return status;
    }

    public void setStatus(OrderStatus.Status status) {
        this.status = status;
    }



}
