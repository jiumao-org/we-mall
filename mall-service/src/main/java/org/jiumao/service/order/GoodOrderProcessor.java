package org.jiumao.service.order;

import java.util.ArrayList;
import java.util.List;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.processor.PunctuationType;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.KeyValueStore;
import org.jiumao.common.utils.JsonSerializable;
import org.jiumao.wechatMall.entity.Order;
import org.jiumao.wechatMall.entity.OrderGoods;

/**
 * 以用户为维度划分订单情况
 * 
 * @author ppf@jiumao.org
 * @date 2018/01/06
 */
public class GoodOrderProcessor implements Processor<Long, byte[]> {

    private ProcessorContext context;
    private KeyValueStore<Long, List<OrderGoods>> kvStore;

    @Override
    @SuppressWarnings("unchecked")
    public void init(ProcessorContext context) {
        this.context = context;
        long interval = 24 * 60 * 60 * 1000;// 24 小时
        this.context.schedule(interval, PunctuationType.STREAM_TIME, (long timestamp)->{
            this.punctuate(timestamp);
        });
        
        this.kvStore = (KeyValueStore<Long, List<OrderGoods>>) context.getStateStore(OrderConstants.GOOD_ORDER_TOPIC);
    }

    @Override
    public void process(Long key, byte[] value) {
        Order o = JsonSerializable.decode(value, Order.class);
        List<OrderGoods> goods = o.getGoods();
        for (OrderGoods og : goods) {

            List<OrderGoods> exist = this.kvStore.get(og.getGoodId());

            if (exist == null) {
                List<OrderGoods> list = new ArrayList<>();
                list.add(og);
                this.kvStore.put(og.getGoodId(), list);
            } else {
                exist.add(og);
                this.kvStore.put(og.getGoodId(), exist);
            }
        }
    }

    @Override
    public void punctuate(long timestamp) {
        KeyValueIterator<Long, List<OrderGoods>> iter = this.kvStore.all();

        while (iter.hasNext()) {
            KeyValue<Long, List<OrderGoods>> entry = (KeyValue<Long, List<OrderGoods>>) iter.next();
            context.forward(entry.key, entry.value.toString());
        }

        iter.close();
        context.commit();
    }

    @Override
    public void close() {
        this.kvStore.close();
    }


};
