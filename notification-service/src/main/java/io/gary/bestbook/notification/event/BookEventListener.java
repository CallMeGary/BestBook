package io.gary.bestbook.notification.event;

import io.gary.bestbook.notification.model.BookDto;
import io.gary.bestbook.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BookEventListener {

    private NotificationService notificationService;

    @RabbitListener(queues = "${bestbook.rabbitmq.book.created-queue-name}")
    public void handleBookCreated(@Payload BookDto bookDto) {
        log.info("New book created={}", bookDto);

        notificationService.sendEmailNotification("New Book Created", bookDto.toString());
    }

    @RabbitListener(queues = "${bestbook.rabbitmq.book.updated-queue-name}")
    public void handleBookUpdated(@Payload BookDto bookDto) {
        log.info("Book updated={}", bookDto);

        notificationService.sendEmailNotification("Book Updated", bookDto.toString());
    }

    @RabbitListener(queues = "${bestbook.rabbitmq.book.deleted-queue-name}")
    public void handleBookDeleted(@Payload BookDto bookDto) {
        log.info("Book deleted={}", bookDto);

        notificationService.sendEmailNotification("Book Deleted", bookDto.toString());
    }
}
