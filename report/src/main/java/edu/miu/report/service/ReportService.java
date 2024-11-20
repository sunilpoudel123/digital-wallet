package edu.miu.report.service;

import edu.miu.report.dto.ReportRequest;
import edu.miu.report.entity.Transaction;

import java.util.List;

public interface ReportService {
    List<Transaction> generateReport(ReportRequest request);

    List<Transaction> getTransactionHistory(Long walletId);
}
