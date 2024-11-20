package edu.miu.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "payment_topic", groupId = "notification_group")
    public void paymentListen(String toEmail, String message) {

        System.out.println("Received Payment Event: " + message);
        emailService.sendPaymentNotification(toEmail, message);
    }
}
