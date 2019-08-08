package com.amqp.producer;

import com.amqp.config.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-06-27 15:03
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeadLetterProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void produceMsg(){
        String msg = " dead letter ";
        /**
         * 发送给普通对队列,设置五秒之后过期
         * 如果没有实现消费的监听，该消息将在五秒之后过期
         */
        rabbitTemplate.convertAndSend(RabbitConfig.NORMAL_EXCHANGE,RabbitConfig.NORMAL_ROUTING_KEY,msg,message -> {
            message.getMessageProperties().setExpiration("5000");
            return message;
        });

    }




}
