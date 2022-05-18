package com.amqp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-06-27 14:55
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String vHost;


    /**死信交换机**/
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    /**死信路由**/
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /**死信队列**/
    public static final String DEAD_QUEUE = "dead-queue";

    /**普通交换机**/
    public static final String NORMAL_EXCHANGE = "normal-exchange";

    /**普通路由**/
    public static final String NORMAL_ROUTING_KEY = "normal-routing-key";

    /**普通队列**/
    public static final String NORMAL_QUEUE = "normal-queue";

    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(vHost);
        return connectionFactory;
    }

    /**
     * 创建死信队列,只需要一个交换机即可，两个队列绑定到上面
     * 此处应该监听队列名为dead-queue的死信队列而不是普通队列normal-queue
     */
    @Bean
    public Queue getDeadQueue(){
        return new Queue(DEAD_QUEUE);
    }

    /**创建普通队列**/
    @Bean
    public Queue getNormalQueue(){
        Map<String,Object> args = new HashMap<>(2);
        //当消息发送异常的时候，消息需要路由到的交换机和routing-key，这里配的直接是发送至死信队列
        args.put("x-dead-letter-exchange",X_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key",X_DEAD_LETTER_ROUTING_KEY);
        //创建队列的时候，将死信绑定到队列中
        return QueueBuilder.durable(NORMAL_QUEUE).withArguments(args).build();
    }

    /**创建死信交换机**/
    @Bean
    public Exchange getDeadExchange(){
        return ExchangeBuilder.directExchange(X_DEAD_LETTER_EXCHANGE).durable(true).build();
    }

    /**队列与延时交换机进行绑定**/
    @Bean
    public Binding bindDead(){
        return BindingBuilder.bind(getDeadQueue()).to(getDeadExchange()).with(X_DEAD_LETTER_ROUTING_KEY).noargs();
    }

    /**普通队列与普通交换机进行绑定**/
    @Bean
    public Binding bindNormal(){
        return BindingBuilder.bind(getNormalQueue()).to(getDeadExchange()).with(NORMAL_ROUTING_KEY).noargs();
    }
}

