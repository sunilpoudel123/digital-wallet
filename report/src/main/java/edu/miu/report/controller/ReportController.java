package edu.miu.report.controller;

import edu.miu.report.dto.ReportRequest;
import edu.miu.report.entity.Transaction;
import edu.miu.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public ResponseEntity<List<Transaction>> generateReport(@RequestBody ReportRequest reportRequest) {
        List<Transaction> report = reportService.generateReport(reportRequest);
        return ResponseEntity.ok(report);
    }

    @GetMapping("/wallet/{walletId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(
            @PathVariable Long walletId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<Transaction> transactions = reportService.getTransactionHistory(walletId, startDate, endDate);
        return ResponseEntity.ok(transactions);
    }
}
