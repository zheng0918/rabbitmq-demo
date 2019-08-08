package com.amqp.test;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-07 11:01
 */
public class MessageProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void fanoutExchangeTest(){
    }



}
