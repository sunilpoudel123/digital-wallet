package edu.miu.payment.controller;

import edu.miu.payment.dto.MobileTopupRequest;
import edu.miu.payment.dto.PaymentRequest;
import edu.miu.payment.entity.Transaction;
import edu.miu.payment.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
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

    //todo add amazon cognito based auth system during payment
    @GetMapping("/{paymentId}")
    public ResponseEntity<Transaction> getPaymentStatus(@PathVariable Long paymentId) {
        Transaction payment = paymentService.getPaymentStatus(paymentId);
        return ResponseEntity.ok(payment);
    }

    @Operation(summary = "Returns a Hello World message")
    @PostMapping("/topup")
    public ResponseEntity<Object> initiateMobileTopup(@RequestBody MobileTopupRequest mobileTopupRequest) {
        Transaction transaction = paymentService.initiateMobileTopup(mobileTopupRequest);
        if (transaction == null) {
            return new ResponseEntity<>("Invalid request: Wallet not found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }
}
