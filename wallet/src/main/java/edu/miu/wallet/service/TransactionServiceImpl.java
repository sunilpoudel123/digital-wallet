package edu.miu.wallet.service;

import edu.miu.wallet.entity.Transaction;
import edu.miu.wallet.entity.TransactionStatus;
import edu.miu.wallet.entity.TransactionType;
import edu.miu.wallet.entity.Wallet;
import edu.miu.wallet.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction doTransaction(Wallet wallet, BigDecimal amount, String description) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCreatedAt(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        transaction.setStatus(TransactionStatus.SUCCESS.name());
        transaction.setWallet(wallet);
        transaction.setDescription(description);
        transaction.setTransactionType(TransactionType.CREDIT);
        transactionRepository.save(transaction);
        return transaction;
    }
}
