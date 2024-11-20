package edu.miu.payment.service;

import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.entity.Payment;
import edu.miu.payment.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Payment initiatePayment(PaymentRequest paymentRequest) {
        // Step 1: Validate Wallet Balance (assuming wallet microservice endpoint /api/wallets/{walletId}/balance)
        String walletServiceUrl = "http://WALLET-SERVICE/api/wallets/" + paymentRequest.getSourceWalletId() + "/balance";
        BigDecimal currentBalance = restTemplate.getForObject(walletServiceUrl, BigDecimal.class);

        if (currentBalance.compareTo(paymentRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Insufficient balance in source wallet.");
        }

        // Step 2: Deduct Balance from Source Wallet
        // Assuming a PUT /api/wallets/{walletId}/withdraw endpoint exists in the Wallet Service
        String withdrawUrl = "http://WALLET-SERVICE/api/wallets/" + paymentRequest.getSourceWalletId() + "/withdraw";
        restTemplate.put(withdrawUrl, paymentRequest.getAmount());

        // Step 3: Add Balance to Destination Wallet
        String depositUrl = "http://WALLET-SERVICE/api/wallets/" + paymentRequest.getDestinationWalletId() + "/deposit";
        restTemplate.put(depositUrl, paymentRequest.getAmount());

        // Step 4: Save Payment Record
        Payment payment = new Payment();
        payment.setSourceWalletId(paymentRequest.getSourceWalletId());
        payment.setDestinationWalletId(paymentRequest.getDestinationWalletId());
        payment.setAmount(paymentRequest.getAmount());
        payment.setStatus("SUCCESS");
        payment.setCreatedAt(LocalDateTime.now());

        return paymentRepository.save(payment);
    }

    @Override
    public Payment getPaymentStatus(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
}
