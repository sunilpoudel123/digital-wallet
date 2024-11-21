package edu.miu.wallet.controller;

import edu.miu.wallet.entity.Wallet;
import edu.miu.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody Wallet wallet) {
        Wallet createdWallet = walletService.createWallet(wallet);
        return new ResponseEntity<>(createdWallet, HttpStatus.CREATED);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long walletId) {
        Optional<Wallet> wallet = walletService.getWalletById(walletId);
        return wallet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @GetMapping()
    public ResponseEntity<List<Wallet>> getAllWallet() {
        Optional<List<Wallet>> wallet = walletService.getAllWallet();
        return wallet.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/{walletId}/add-funds")
    public ResponseEntity<Wallet> addFunds(@PathVariable Long walletId, @RequestParam BigDecimal amount) {
        try {
            Wallet updatedWallet = walletService.addFunds(walletId, amount);
            return ResponseEntity.ok(updatedWallet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @PostMapping("/{walletId}/withdraw")
    public ResponseEntity<Wallet> withdrawFunds(@PathVariable Long walletId, @RequestParam BigDecimal amount) {
        try {
            Wallet updatedWallet = walletService.withdrawFunds(walletId, amount);
            return ResponseEntity.ok(updatedWallet);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(
            @RequestParam Long senderWalletId,
            @RequestParam Long recipientWalletId,
            @RequestParam BigDecimal amount) {
        try {
            walletService.transferFunds(senderWalletId, recipientWalletId, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
