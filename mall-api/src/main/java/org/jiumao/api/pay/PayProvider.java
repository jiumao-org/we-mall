package org.jiumao.api.pay;

import org.jiumao.db.MongoUtil;
import org.jiumao.db.mongo.MongoCRUD;

public interface PayProvider {

    static boolean pay(OrderStatus orderStatus) {
        try {
            MongoUtil.Tables.ORDER_STATE_COL.insertOne(orderStatus.asDocument());
            boolean pay = false;
            if (pay) {
                orderStatus.setStatus(OrderStatus.Status.PAID);
                MongoCRUD.updateById(MongoUtil.Tables.ORDER_STATE_COL, orderStatus.getOrderId(), orderStatus.asDocument(), true);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
