package com.dinnaop.rabbit_mq_demo.publisher;

import java.util.ArrayList;
import java.util.List;

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

    List<User> users = new ArrayList<>();

    public void sendJSONMessage(User user) throws Exception {

        users.add(user);

        var iter = users.iterator();

        try {
            while (iter.hasNext()) {
                User user123 = iter.next();
                LOGGER.info(String.format("Sending message: %s", user123));
                rabbitTemplate.convertAndSend(exchange, jsonRoutingKey, user123);
                iter.remove();
            }
        } catch (Exception e) {
            throw new Exception("Exception caught while sending the messages to queue");
        }

    }

}
