package edu.miu.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "wallet_creation_topic", groupId = "notification_group")
    public void walletListen(String toEmail, String message) {

        System.out.println("Received Wallet Event: " + message);
        emailService.sendPaymentNotification(toEmail, message);
    }
}
