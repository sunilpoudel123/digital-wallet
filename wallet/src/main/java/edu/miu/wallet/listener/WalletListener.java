package edu.miu.wallet.listener;

import edu.miu.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletListener {

    @Autowired
    private WalletService walletService;

    @KafkaListener(topics = "user_creation_topic", groupId = "notification_group")
    public void userListen(String message) {

        System.out.println("Received user create Event: " + message);

//        walletService.createWallet(toEmail, message);
    }
}
