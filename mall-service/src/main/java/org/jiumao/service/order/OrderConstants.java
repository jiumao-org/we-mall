package org.jiumao.service.order;

public interface OrderConstants {

    String ORDER_COMMITED_TOPIC = "OrderCommit";
    /** 已经处理的订单topic名称 **/
    String ORDER_HANDLED_TOPIC = ORDER_COMMITED_TOPIC + "handled-order-topic";
    
    String ORDER_GROUP="order-group";

}
