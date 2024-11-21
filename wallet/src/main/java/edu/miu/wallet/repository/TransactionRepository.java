package edu.miu.wallet.repository;

import edu.miu.wallet.entity.Transaction;
import edu.miu.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}