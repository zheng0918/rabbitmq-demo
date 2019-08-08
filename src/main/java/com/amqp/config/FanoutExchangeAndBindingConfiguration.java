package com.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @annotation: fanout路由策略(广播机制)的交换机注入、Queue与Exchange的绑定注入
 * @author: Zhengx
 * @create: 2019-05-07 10:59
 */
@Configuration
public class FanoutExchangeAndBindingConfiguration {

    /**
     * 注入fanout路由策略的Exchange交换实例
     * @return
     */
    @Bean(name = "myFanoutExchange")
    FanoutExchange getFanoutExchange(){
        return new FanoutExchange("fanout-exchange");
    }

    /**
     * 将myFirstQueue对应的队列，绑定到myFanoutExchange对应的交换机
     * @param firstQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    Binding bindingFirstQueueToFanoutExchange(@Qualifier("myFirstQueue") Queue firstQueue, @Qualifier("myFanoutExchange") FanoutExchange fanoutExchange){
        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
    }

//    @Bean
//    Binding bindingSecondQueueToFanoutExchange(@Qualifier("mySecondQueue") Queue firstQueue, @Qualifier("myFanoutExchange") FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
//    }
//
//    @Bean
//    Binding bindingThirdQueueToFanoutExchange(@Qualifier("myThirdQueue") Queue firstQueue, @Qualifier("myFanoutExchange") FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(firstQueue).to(fanoutExchange);
//    }


}
