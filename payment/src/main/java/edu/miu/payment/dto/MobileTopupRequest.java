package edu.miu.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MobileTopupRequest {
    private String walletUsername;
    @Positive
    private BigDecimal amount;
    @NotBlank
    private String phoneNumber;
    private String carrier;
}
