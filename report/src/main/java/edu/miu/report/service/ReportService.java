package edu.miu.report.service;

import edu.miu.report.dto.ReportRequest;
import edu.miu.report.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<Transaction> generateReport(ReportRequest request);

    List<Transaction> getTransactionHistory(String username,  LocalDate startDate, LocalDate endDate);
}
