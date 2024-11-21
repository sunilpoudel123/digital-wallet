package edu.miu.payment.repository;

import edu.miu.payment.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository extends JpaRepository<Transaction, Long> {

}