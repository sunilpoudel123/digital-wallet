package edu.miu.report.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long walletId;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String currency;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String username;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @JsonBackReference
    @JsonIgnore
    private List<Transaction> transactions;

}
