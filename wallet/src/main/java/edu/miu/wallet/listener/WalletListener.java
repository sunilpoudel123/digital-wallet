package edu.miu.wallet.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.wallet.entity.User;
import edu.miu.wallet.entity.Wallet;
import edu.miu.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletListener {

    @Autowired
    private WalletService walletService;

    @KafkaListener(topics = "user_creation_topic", groupId = "notification_group")
    public void userListen(String message) {
        System.out.println("Received user create Event: " + message);
        try {
            String username = extractUsername(message);

            Wallet wallet = createWallet(username);
            walletService.createWallet(wallet);
        } catch (Exception e) {
            System.err.println("Failed to process user creation event: " + e.getMessage());
        }
    }

    private String extractUsername(String message) {
        String prefix = "username=";
        int startIndex = message.indexOf(prefix) + prefix.length();
        int endIndex = message.indexOf(",", startIndex);
        return message.substring(startIndex, endIndex).trim();
    }

    private Wallet createWallet(String username) {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.ZERO);
        wallet.setUsername(username);
        wallet.setCurrency("USD");
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        return wallet;
    }
}
