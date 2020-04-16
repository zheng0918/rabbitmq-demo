package com.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sun.management.Agent;

import java.util.HashMap;
import java.util.Map;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-07 10:58
 */
@Configuration
public class DirectExchangeAndBindingConfiguration {

    @Bean(name = "firstDirectExchange")
    DirectExchange getFirstDirectExchange(){
        return new DirectExchange("direct.exchange");
    }

    @Bean
    Binding bindingFirstQueueToDirectExchange(@Qualifier("myFirstQueue") Queue myFirstQueue,@Qualifier("firstDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(myFirstQueue).to(directExchange).with("routingKey.first");
    }

    @Bean
    Binding bindingSecondQueueToDirectExchange(@Qualifier("mySecondQueue") Queue mySecondQueue,@Qualifier("firstDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(mySecondQueue).to(directExchange).with("routingKey.second");
    }

    @Bean
    Binding bindingThirdQueueToDirectExchange(@Qualifier("myThirdQueue") Queue myThirdQueue,@Qualifier("firstDirectExchange") DirectExchange directExchange){
        return BindingBuilder.bind(myThirdQueue).to(directExchange).with("routingKey.third");
    }


    /**死信队列**/

    /**创建业务队列**/
    @Bean
    public Queue mailQueue(){
        Map<String,Object> args = new HashMap<>(2);
        //设置死信交换机
        args.put(RabbitConfig.X_DEAD_LETTER_EXCHANGE,"dead_letter_exchange");
        //设置死信routingKey
        args.put(RabbitConfig.X_DEAD_LETTER_ROUTING_KEY,"mail_queue_fail");
        return new Queue("mailQueue",true,false,false,args);
    }

    /**创建业务交换机**/
    @Bean
    public DirectExchange mailExchange(){
        return new DirectExchange("mailExchange",true,false);
    }

    /**绑定业务队列和交换机，指定routingKey**/
    @Bean
    public Binding mailBinding(){
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with("mailRoutingKey");
    }

    /***创建死信队列*/
    @Bean
    public Queue deadQueue(){
        return new Queue("dead",true);
    }

    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("dead_letter_exchange", true, false);
    }

    /**绑定死定队列和死信交换机**/
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange())
                .with("mail_queue_fail");
    }


}
