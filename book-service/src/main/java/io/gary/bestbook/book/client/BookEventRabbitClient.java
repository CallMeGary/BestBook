package io.gary.bestbook.book.client;

import io.gary.bestbook.book.model.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookEventRabbitClient {

    private RabbitTemplate rabbitTemplate;

    private BookEventRabbitProperties properties;

    public BookDto publishBookCreatedEvent(BookDto bookDto) {
        return publishUserEvent(bookDto, properties.getCreatedQueueName());
    }

    public BookDto publishBookUpdatedEvent(BookDto bookDto) {
        return publishUserEvent(bookDto, properties.getUpdatedQueueName());
    }

    public BookDto publishBookDeletedEvent(BookDto bookDto) {
        return publishUserEvent(bookDto, properties.getDeletedQueueName());
    }

    private BookDto publishUserEvent(BookDto bookDto, String queueName) {
        rabbitTemplate.convertAndSend(properties.getExchangeName(), queueName, bookDto);
        return bookDto;
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
