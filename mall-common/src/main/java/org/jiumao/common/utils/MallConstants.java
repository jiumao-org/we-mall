package org.jiumao.common.utils;

public interface MallConstants {

    String ORDER_COMMITED_TOPIC = "OrderCommit";
    /** 已经处理的订单topic名称 **/
    String ORDER_HANDLED_TOPIC = ORDER_COMMITED_TOPIC + "handled-order-topic";

    String ORDER_GROUP = "order-group";

    String ORDER_TABLE_NAME = "orderStore";
    String ORDER_STATE_TABLE_NAME = "orderStateStore";

    String GOOD_TABLE_NAME = "goodStore";

    String USER_TABLE_NAME = "userStore";

    String USER_ORDERS_TABLE_NAME = "userOrdersStore";



}
