package io.gary.bestbook.notification.event;

import io.gary.bestbook.notification.model.UserDto;
import io.gary.bestbook.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class UserEventListener {

    private NotificationService notificationService;

    @RabbitListener(queues = "${bestbook.rabbitmq.user.registered-queue-name}")
    public void handleUserRegistered(@Payload UserDto userDto) {
        log.info("New user registered={}", userDto);

        notificationService.sendEmailNotification("New User Registered", userDto.toString());
    }
}
