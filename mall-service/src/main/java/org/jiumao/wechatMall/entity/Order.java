package org.jiumao.wechatMall.entity;

import java.util.Collections;
import java.util.List;

import org.jiumao.common.utils.JsonSerializable;

/**
 * json to byte[] 
 * @author ppf@jiumao.org 
 * @date 2017/12/10
 */
public class Order extends JsonSerializable{
    private Long id;
    private Long userId;// 订单也是用户级别的
    private Long orderId;// 订单号，应当有程序生成唯一且不可变
    private Long creatTime;// 创建时间，用于报表统计，时间戳
    private Integer status;// 订单状态，取消，未付款，正在派单，送达，确认收货等
    private Double totalPrice;// 订单总价
    private Integer num;// 订单商品数
    private int deadLine = 3000;// 相对创建时间的过期时间，单位毫秒
    
    // 商品详情
    private List<OrderGoods> goods = Collections.emptyList();
    
    public Order() {
        super();
    }

    public Order(Long id, Long userId, Long orderId, Long creatTime, Integer status, Double totalPrice, Integer num) {
        super();
        this.id = id;
        this.userId = userId;
        this.orderId = orderId;
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


}
