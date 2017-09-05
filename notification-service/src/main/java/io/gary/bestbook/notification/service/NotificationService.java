package io.gary.bestbook.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationService {

    public void sendEmailNotification(String title, String message) {
        log.info("Sending email notification, title={}, message={}", title, message);
    }
}
