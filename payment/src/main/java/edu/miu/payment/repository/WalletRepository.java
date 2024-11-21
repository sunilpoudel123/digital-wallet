package edu.miu.payment.repository;

import edu.miu.payment.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query("SELECT w FROM Wallet w WHERE w.username = :username")
    Optional<Wallet> findByUsername(@Param("username") String username);
}