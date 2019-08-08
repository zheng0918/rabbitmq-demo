package com.amqp.listener;

import com.amqp.config.RabbitConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-06-27 15:00
 */
@Component
public class DeadLetterListener {

    /**
     * 监听死信队列
     * 在生产消息时，将消息推送至normal-exchange交换机，交换机再推送至normal-queue队列，但是该队列并没有设置监听消费，
     * 所以会在5S之后过期变成死信队列，此时监听死信队列可以消费改消息
     * @param msg
     */
    @RabbitListener(queues = {RabbitConfig.DEAD_QUEUE})
    public void receiverFromDeadQueue(String msg){
        System.out.println("dead queue got the message : "+msg);
    }

    /**
     * 监听普通队列
     * 如果这里监听了普通队列，则消息会被改队列消费，不会推送到死信队列中
     * @param msg
     */
    @RabbitListener(queues = {RabbitConfig.NORMAL_QUEUE})
    public void receiverFromNormalQueue(String msg){
        System.out.println("normal queue got the message : "+ msg);
    }


}
