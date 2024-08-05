package com.dinnaop.rabbit_mq_demo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private final static Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    // Helps listen and read to rabbitmq consumers
    @RabbitListener(queues = { "${rabbitmq.demo.queueName}" })
    public void consume(String message) {
        LOGGER.info(String.format("String consumed from queue is : %s", message));
    }
}
