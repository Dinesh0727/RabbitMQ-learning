package com.dinnaop.rabbit_mq_demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dinnaop.rabbit_mq_demo.dto.User;
import com.dinnaop.rabbit_mq_demo.publisher.RabbitMQJSONProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1")
public class JSONMessageController {

    private RabbitMQJSONProducer rabbitMQJSONProducer;

    public JSONMessageController(RabbitMQJSONProducer rabbitMQJSONProducer) {
        this.rabbitMQJSONProducer = rabbitMQJSONProducer;
    }

    @PostMapping("/publish-JSON")
    public ResponseEntity<String> sendJSONMessage(@RequestBody User user) throws Exception {
        rabbitMQJSONProducer.sendJSONMessage(user);

        return ResponseEntity.ok("Send JSON RabbitMQ Message - User : " + user.toString());
    }

}
