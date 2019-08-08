package com.amqp.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @annotation:
 * @author: Zhengx
 * @create: 2019-05-13 17:29
 */
public class Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        String exchangeName = "dev-exchange";
        channel.exchangeDeclare(exchangeName,"direct",true);

        String routingKey = "halo";

        byte[] messageBodyBytes = "i love u three thousand times".getBytes();

        channel.basicPublish(exchangeName,routingKey,null,messageBodyBytes);

        channel.close();
        connection.close();

    }

}
