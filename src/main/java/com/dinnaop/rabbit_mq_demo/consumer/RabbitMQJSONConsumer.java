package com.dinnaop.rabbit_mq_demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.dinnaop.rabbit_mq_demo.dto.User;

@Service
public class RabbitMQJSONConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONConsumer.class);

    @RabbitListener(queues = { "${rabbitmq.demo.queueName.json}" })
    public void consumeJSONMessage(User user) throws Exception {
        try {
            Thread.sleep(3000);
            LOGGER.info(String.format("Received JSON Message -> %s", user.toString()));
        } catch (Exception e) {
            throw new Exception("Threw exception when consuming the messages");
        }
    }
}
