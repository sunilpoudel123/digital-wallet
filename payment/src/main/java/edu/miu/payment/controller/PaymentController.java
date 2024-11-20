package edu.miu.payment.controller;

import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.entity.Payment;
import edu.miu.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = paymentService.initiatePayment(paymentRequest);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Payment> getPaymentStatus(@PathVariable Long paymentId) {
        Payment payment = paymentService.getPaymentStatus(paymentId);
        return ResponseEntity.ok(payment);
    }
}
