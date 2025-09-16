package org.walletuser.walletuser.Service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final String TOPIC = "user_creation_topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendWalletCreationMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
        System.out.println("Sent message: " + message);
    }
}