package edu.miu.payment.service;

import edu.miu.payment.dto.MobileTopupRequest;
import edu.miu.payment.dto.PaymentMethod;
import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.dto.PaymentStatus;
import edu.miu.payment.entity.Transaction;
import edu.miu.payment.entity.TransactionType;
import edu.miu.payment.entity.Wallet;
import edu.miu.payment.repository.PaymentTransactionRepository;
import edu.miu.payment.repository.WalletRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final WalletRepository walletRepository;

    @Autowired
    public PaymentServiceImpl(PaymentTransactionRepository paymentTransactionRepository, WalletRepository walletRepository) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    @CircuitBreaker(name= "payment-service", fallbackMethod = "fallback")
    @Retry(name = "payment-service")
    public Transaction initiateMobileTopup(MobileTopupRequest mobileTopupRequest) {
        System.out.println("validation of payment request: ");

        Optional<Wallet> wallet = walletRepository.findByUsername(mobileTopupRequest.getWalletUsername());
        if (!wallet.isPresent()) {

            System.out.println("invalid request");
            return null;
        }

        Transaction payment = new Transaction();
        payment.setWallet(wallet.get());
        payment.setAmount(mobileTopupRequest.getAmount());
        payment.setDescription(PaymentMethod.MOBILE_TOPUP.name());
        payment.setStatus(PaymentStatus.INITIATED.name());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setTransactionType(TransactionType.TRANSFER);
        return paymentTransactionRepository.save(payment);
    }

    public String fallback(Throwable t){
        return "fall back due to" + t.getMessage();
    }

    @Override
    public Transaction getPaymentStatus(Long paymentId) {
        return paymentTransactionRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
}
