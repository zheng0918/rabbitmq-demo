package com.amqp.service.impl;

import com.amqp.service.TestService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-07-25 15:06
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String send(String msg) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("desc","测试当两个消费者同事监听一个队列时，是否都能消费这条消息！");
        Message message = new Message("test messages".getBytes(),messageProperties);
        rabbitTemplate.convertAndSend("fanout-exchange",null,message);
        return "success";
    }



}
