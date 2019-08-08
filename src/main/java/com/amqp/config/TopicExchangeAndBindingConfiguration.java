package com.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-07 11:00
 */
@Configuration
public class TopicExchangeAndBindingConfiguration {

    @Bean(name = "firstTopicExchange")
    TopicExchange getFirstDirectExchange(){
        return new TopicExchange("topic.exchange");
    }

    @Bean
    Binding bindingFirstQueueToTopicExchange(@Qualifier("myFirstQueue") Queue myFirstQueue, @Qualifier("firstTopicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(myFirstQueue).to(topicExchange).with("routingKey.#");
    }

    @Bean
    Binding bindingSecondQueueToTopicExchange(@Qualifier("mySecondQueue") Queue mySecondQueue,@Qualifier("firstTopicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(mySecondQueue).to(topicExchange).with("#.topic");
    }

    @Bean
    Binding bindingThirdQueueToTopicExchange(@Qualifier("myThirdQueue") Queue myThirdQueue,@Qualifier("firstTopicExchange") TopicExchange topicExchange){
        return BindingBuilder.bind(myThirdQueue).to(topicExchange).with("routingKey.third.#");
    }

}
