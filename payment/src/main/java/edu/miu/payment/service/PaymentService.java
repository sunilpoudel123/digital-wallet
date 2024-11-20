package edu.miu.payment.service;


import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.entity.Payment;

public interface PaymentService {
    Payment initiatePayment(PaymentRequest paymentRequest);
    Payment getPaymentStatus(Long paymentId);
}
