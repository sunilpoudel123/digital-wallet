package edu.miu.payment.service;

import edu.miu.payment.dto.MobileTopupRequest;
import edu.miu.payment.dto.PaymentMethod;
import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.dto.PaymentStatus;
import edu.miu.payment.entity.Transaction;
import edu.miu.payment.entity.TransactionType;
import edu.miu.payment.entity.Wallet;
import edu.miu.payment.repository.PaymentTransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentTransactionRepository paymentTransactionRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public PaymentServiceImpl(PaymentTransactionRepository paymentTransactionRepository, RestTemplate restTemplate) {
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public Transaction initiatePayment(PaymentRequest paymentRequest) {

        System.out.println("validation of payment request: ");

        Transaction payment = new Transaction();
        payment.setWallet(new Wallet(paymentRequest.getSourceWalletId()));
        payment.setAmount(paymentRequest.getAmount());
        payment.setStatus(TransactionType.TRANSFER.name());
        payment.setCreatedAt(LocalDateTime.now());

        return paymentTransactionRepository.save(payment);
    }

    @Override
    public Transaction initiateMobileTopup(MobileTopupRequest mobileTopupRequest) {
        Transaction payment = new Transaction();
        payment.setWallet(new Wallet(mobileTopupRequest.getWalletId()));
        payment.setAmount(mobileTopupRequest.getAmount());
        payment.setDescription(PaymentMethod.MOBILE_TOPUP.name());
        payment.setStatus(PaymentStatus.INITIATED.name());
        payment.setCreatedAt(LocalDateTime.now());
        payment.setTransactionType(TransactionType.TRANSFER);
        return paymentTransactionRepository.save(payment);
    }

    @Override
    public Transaction getPaymentStatus(Long paymentId) {
        return paymentTransactionRepository.findById(paymentId).orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }
}
