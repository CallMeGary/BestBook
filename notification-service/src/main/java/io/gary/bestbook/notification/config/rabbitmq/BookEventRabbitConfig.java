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
public class BookEventRabbitConfig {

    @Bean
    TopicExchange bookEventExchange(BookEventRabbitProperties properties) {
        return new TopicExchange(properties.getExchangeName());
    }

    @Bean
    Queue bookCreatedQueue(BookEventRabbitProperties properties) {
        return QueueBuilder.durable(properties.getCreatedQueueName()).build();
    }

    @Bean
    Binding bookCreatedBinding(Queue bookCreatedQueue, TopicExchange bookEventExchange) {
        return BindingBuilder.bind(bookCreatedQueue).to(bookEventExchange).with(bookCreatedQueue.getName());
    }

    @Bean
    Queue bookUpdatedQueue(BookEventRabbitProperties properties) {
        return QueueBuilder.durable(properties.getUpdatedQueueName()).build();
    }

    @Bean
    Binding bookUpdatedBinding(Queue bookUpdatedQueue, TopicExchange bookEventExchange) {
        return BindingBuilder.bind(bookUpdatedQueue).to(bookEventExchange).with(bookUpdatedQueue.getName());
    }

    @Bean
    Queue bookDeletedQueue(BookEventRabbitProperties properties) {
        return QueueBuilder.durable(properties.getDeletedQueueName()).build();
    }

    @Bean
    Binding bookDeletedBinding(Queue bookDeletedQueue, TopicExchange bookEventExchange) {
        return BindingBuilder.bind(bookDeletedQueue).to(bookEventExchange).with(bookDeletedQueue.getName());
    }

    @Data
    @Configuration
    @ConfigurationProperties("bestbook.rabbitmq.book")
    static class BookEventRabbitProperties {

        private String exchangeName;

        private String createdQueueName;

        private String updatedQueueName;

        private String deletedQueueName;
    }

}
