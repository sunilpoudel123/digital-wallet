package edu.miu.report.repository;

import edu.miu.report.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.wallet.walletId = :walletId AND t.createdAt BETWEEN :startDate AND :endDate")
    List<Transaction> findByWalletIdAndDateRange(Long walletId, LocalDateTime startDate, LocalDateTime endDate);



}