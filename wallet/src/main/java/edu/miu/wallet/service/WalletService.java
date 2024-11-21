package edu.miu.wallet.service;

import edu.miu.wallet.entity.Wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface WalletService {
    Wallet createWallet(Wallet wallet);

    Optional<Wallet> getWalletById(Long walletId);

    Optional<List<Wallet>> getAllWallet();

    Wallet addFunds(Long walletId, BigDecimal amount);

    Wallet withdrawFunds(Long walletId, BigDecimal amount);

    boolean transferFunds(Long senderWalletId, Long recipientWalletId, BigDecimal amount);
}
