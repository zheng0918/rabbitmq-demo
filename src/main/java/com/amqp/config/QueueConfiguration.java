package com.amqp.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-07 10:59
 */
@Configuration
public class QueueConfiguration {




    /**
     * 注入第一个Queue队列
     * @return
     */
    @Bean(name = "myFirstQueue")
    public Queue getFirstQueue(){
        return new Queue("my.first.queue",true);
    }


    @Bean(name = "mySecondQueue")
    public Queue getSecondQueue(){
        return new Queue("my.second.queue");
    }

    @Bean(name = "myThirdQueue")
    public Queue getThirdQueue(){
        return new Queue("my.third.queue");
    }

}
