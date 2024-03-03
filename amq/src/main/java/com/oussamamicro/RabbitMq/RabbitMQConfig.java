package com.oussamamicro.RabbitMq;

import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@EnableAutoConfiguration
@Configuration
@AllArgsConstructor
public class RabbitMQConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    @Primary
    public AmqpTemplate amqpTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter());
        return rabbitTemplate;
    }
//    this method configures a RabbitTemplate bean with the necessary
//    settings for message conversion and RabbitMQ connection,
//    making it ready to use for message publishing within the Spring application.




    @Bean
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory =
                new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jacksonConverter());
        return factory;
    }
//    this method configures a SimpleRabbitListenerContainerFactory bean with the necessary
//        settings for message conversion and RabbitMQ connection, making
//    it ready to use for creating listener containers to consume messages from
//    RabbitMQ queues within the Spring application.

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }
//    this method configures a Jackson2JsonMessageConverter bean,
//    allowing messages to be automatically converted to and from JSON
//    format when interacting with RabbitMQ in the Spring application.
//    This ensures seamless integration with JSON-based messaging in the application,
//    making it easier to work with JSON data in message payloads.

}