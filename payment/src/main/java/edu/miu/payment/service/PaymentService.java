package edu.miu.payment.service;


import edu.miu.payment.dto.MobileTopupRequest;
import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.entity.Transaction;

public interface PaymentService {
    Transaction initiatePayment(PaymentRequest paymentRequest);

    Transaction getPaymentStatus(Long paymentId);

    Transaction initiateMobileTopup(MobileTopupRequest mobileTopupRequest);
}
