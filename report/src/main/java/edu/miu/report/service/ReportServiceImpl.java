package edu.miu.report.service;

import edu.miu.report.dto.ReportRequest;
import edu.miu.report.entity.Transaction;
import edu.miu.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<Transaction> generateReport(ReportRequest request) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(request.getStartDate(), formatter).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(request.getEndDate(), formatter).atTime(23, 59, 59);
        return reportRepository.findByWalletIdAndDateRange(request.getWalletUsername(), startDateTime, endDateTime);
    }

    @Override
    public List<Transaction> getTransactionHistory(String username, LocalDate startDate, LocalDate endDate) {
        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);
        return reportRepository.findByWalletIdAndDateRange(username, startDateTime, endDateTime);
    }

}
