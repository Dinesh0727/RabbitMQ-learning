package com.dinnaop.rabbit_mq_demo.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dinnaop.rabbit_mq_demo.dto.User;

@Service
public class RabbitMQJSONProducer {

    @Value("${rabbitmq.demo.exchangeName}")
    private String exchange;

    @Value("${rabbitmq.demo.json.routingKeyName}")
    private String jsonRoutingKey;

    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJSONProducer.class);

    public RabbitMQJSONProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendJSONMessage(User user) {
        LOGGER.info(String.format("JSON Message sent : %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user);
    }

}
