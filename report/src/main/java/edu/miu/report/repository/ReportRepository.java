package edu.miu.report.repository;

import edu.miu.report.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByWalletId(Long walletId);
}