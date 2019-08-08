package com.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AbcRabbitMqConsumerDemoApplication.class)
public class AbcRabbitMqConsumerDemoApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void fanoutExchangeTest(){
    }

    @Test
    public void contextLoads() {
        //发送广播信息
        rabbitTemplate.convertAndSend("fanout-exchange","","come on ,be magnanimous");

        //发送消息至绑定direct.exchange的key为routingKey.second的队列
        rabbitTemplate.convertAndSend("direct.exchange","routingKey.second","come on ,be magnanimous!!!");
    }

}
