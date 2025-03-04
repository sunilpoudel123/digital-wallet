package edu.miu.wallet.service;

import edu.miu.wallet.entity.Transaction;
import edu.miu.wallet.entity.Wallet;

import java.math.BigDecimal;
import java.util.Optional;

public interface TransactionService {

    Transaction doTransaction(Wallet wallet, BigDecimal amount, String description);

}
