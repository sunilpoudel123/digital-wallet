package edu.miu.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private Long sourceWalletId;
    private Long destinationWalletId;
    private BigDecimal amount;
}
