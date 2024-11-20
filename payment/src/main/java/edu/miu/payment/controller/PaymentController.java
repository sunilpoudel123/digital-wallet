package edu.miu.payment.controller;

import edu.miu.payment.dto.MobileTopupRequest;
import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.entity.Transaction;
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
    public ResponseEntity<Transaction> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
        Transaction payment = paymentService.initiatePayment(paymentRequest);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<Transaction> getPaymentStatus(@PathVariable Long paymentId) {
        Transaction payment = paymentService.getPaymentStatus(paymentId);
        return ResponseEntity.ok(payment);
    }

    @PostMapping("/topup")
    public ResponseEntity<Transaction> initiateMobileTopup(@RequestBody MobileTopupRequest mobileTopupRequest) {
        Transaction payment = paymentService.initiateMobileTopup(mobileTopupRequest);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }
}
