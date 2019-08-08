package com.amqp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.jws.HandlerChain;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-07 11:00
 */
@Component
public class DemoMessageListener {

    /**
     * 监听来自第一个队列的消息
     * @param message
     */
    @RabbitHandler
    @RabbitListener(queues = "my.first.queue")
    public void firstConsumer(String message){
        System.out.println("this is ur first queues :"+message);
    }


    @RabbitHandler
    @RabbitListener(queues = "my.first.queue")
    public void firstConsumerListener(String message){
        System.out.println("this is ur first queue :"+message);
    }


    @RabbitHandler
    @RabbitListener(queues = "my.second.queue")
    public void SecondConsumer(String message){
        System.out.println("this is ur second queue :"+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "my.third.queue")
    public void thirdConsumer(String message){
        System.out.println("this is ur third queue :"+message);
    }

}
