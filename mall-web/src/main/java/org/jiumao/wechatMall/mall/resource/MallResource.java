package org.jiumao.wechatMall.mall.resource;

import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.jiumao.common.domain.Msg;
import org.jiumao.common.utils.IdUtil;
import org.jiumao.mall.auth.AuthAnnotation;
import org.jiumao.service.RPCServices;
import org.jiumao.service.order.OrderProducer;
import org.jiumao.wechatMall.entity.Order;
import org.jiumao.wechatMall.entity.User;
import org.springframework.stereotype.Component;


@Component
@Path("/mall")
@Produces(MediaType.APPLICATION_JSON)
public class MallResource {


    @POST
    @Path("order/commit")
    @Consumes(MediaType.APPLICATION_JSON)
    @AuthAnnotation
    public Msg orderCommit(Order order) {
        Objects.requireNonNull(order);

        order.setCreatTime(System.currentTimeMillis());
        Long key = IdUtil.getOrderId();
        order.setId(key);
        OrderProducer producer = RPCServices.getOrderService();
        producer.send(key, order.encode());
        
        return new Msg(key, "订单号");
    }


}
