package io.gary.bestbook.auth.client;

import io.gary.bestbook.auth.domain.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserEventRabbitClient {

    private RabbitTemplate rabbitTemplate;

    private UserEventRabbitProperties properties;

    public UserDto publishUserRegisteredEvent(UserDto userDto) {
        return publishUserEvent(userDto, properties.getRegisteredQueueName());
    }

    private UserDto publishUserEvent(UserDto userDto, String queueName) {
        rabbitTemplate.convertAndSend(properties.getExchangeName(), queueName, userDto);
        return userDto;
    }

    @Data
    @Configuration
    @ConfigurationProperties("bestbook.rabbitmq.user")
    static class UserEventRabbitProperties {

        private String exchangeName;

        private String registeredQueueName;
    }
}
