package org.jiumao.api.pay;

import org.bson.Document;
import org.jiumao.common.utils.JsonSerializable;

/**
 * 订单状态
 * @author ppf@jiumao.org 
 * @date 2017/12/11
 */
public class OrderStatus extends JsonSerializable{
    
    private Long OrderId;// 应当有程序生成唯一且不可变
    private Long userId;// 订单也是用户级别的
    private Long creatTime;// 创建时间，用于报表统计，时间戳
    private Status status;
    
    public OrderStatus(Long orderId, Long userId, Long creatTime) {
        super();
        OrderId = orderId;
        this.userId = userId;
        this.creatTime = creatTime;
    }

    public static  enum Status{
        /** 取消 */
        CANCELED,
        /** 未付款 */
        UNPAID,
        /** 已付款 */
        PAID,
        /** 正在派单 */
        SENDING,
        /** 送达 */
        SENT,
        /** 确认收货 */
        CHECKED
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Long creatTime) {
        this.creatTime = creatTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
 
    
    public Document asDocument() {
       return  new Document("_id", OrderId).append("creatTime", creatTime)//
        .append("userId", userId).append("status", status);
    }

}
