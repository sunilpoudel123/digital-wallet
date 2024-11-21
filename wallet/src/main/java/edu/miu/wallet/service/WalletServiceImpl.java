package edu.miu.wallet.service;

import edu.miu.wallet.entity.Wallet;
import edu.miu.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, KafkaProducerService kafkaProducerService) {
        this.walletRepository = walletRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    @Transactional
    public Wallet createWallet(Wallet wallet) {
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setUsername(wallet.getUsername());
        Wallet savedWallet = walletRepository.save(wallet);
        kafkaProducerService.sendWalletCreationMessage("New Wallet Created: " + savedWallet);
        return wallet;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Wallet> getWalletById(Long walletId) {
        return walletRepository.findById(walletId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Wallet>> getAllWallet() {
        return Optional.of(walletRepository.findAll());
    }

    @Override
    @Transactional
    public Wallet addFunds(Long walletId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }

        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found."));

        wallet.setBalance(wallet.getBalance().add(amount));
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet withdrawFunds(Long walletId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new IllegalArgumentException("Wallet not found."));

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance.");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public boolean transferFunds(Long senderWalletId, Long recipientWalletId, BigDecimal amount) {
        if (senderWalletId.equals(recipientWalletId)) {
            throw new IllegalArgumentException("Cannot transfer funds to the same wallet.");
        }
        withdrawFunds(senderWalletId, amount);
        addFunds(recipientWalletId, amount);
        return true;
    }
}
