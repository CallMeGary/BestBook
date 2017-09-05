package io.gary.bestbook.notification.config.rabbitmq;

import lombok.Data;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserEventRabbitConfig {

    @Bean
    TopicExchange userEventExchange(UserEventRabbitProperties properties) {
        return new TopicExchange(properties.getExchangeName());
    }

    @Bean
    Queue userRegisteredQueue(UserEventRabbitProperties properties) {
        return QueueBuilder.durable(properties.getRegisteredQueueName()).build();
    }

    @Bean
    Binding userRegisteredBinding(Queue userRegisteredQueue, TopicExchange userEventExchange) {
        return BindingBuilder.bind(userRegisteredQueue).to(userEventExchange).with(userRegisteredQueue.getName());
    }

    @Data
    @Configuration
    @ConfigurationProperties("bestbook.rabbitmq.user")
    static class UserEventRabbitProperties {

        private String exchangeName;

        private String registeredQueueName;
    }
}
