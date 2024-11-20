package edu.miu.payment.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MobileTopupRequest {
    private Long walletId;
    private BigDecimal amount;
    private String phoneNumber;
    private String carrier;
}
