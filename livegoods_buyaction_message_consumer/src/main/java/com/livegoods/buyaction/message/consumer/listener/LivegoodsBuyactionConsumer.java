package com.livegoods.buyaction.message.consumer.listener;

import com.livegoods.buyaction.message.consumer.service.ConsumerService;
import com.livegoods.commons.message.LivegoodsBuyMessage;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LivegoodsBuyactionConsumer {

    @Autowired
    private ConsumerService consumerService;

    @RabbitListener(bindings = {@QueueBinding(
            value = @Queue(name = "${livegoods.rabbit.buy.queue}",autoDelete = "false"),
            exchange = @Exchange(name = "${livegoods.rabbit.buy.exchange}"),
            key = "${livegoods.rabbit.buy.routingKey}"
    )})
    public Object onMessage(LivegoodsBuyMessage message){
        String itemId = message.getItemId();
        String username = message.getUsername();
        boolean result = consumerService.buyaction(itemId, username);
        return result;
    }
}
