package edu.miu.report.dto;

import lombok.Data;

@Data
public class ReportRequest {
    private String walletUsername;
    private String reportType;
    private String startDate;
    private String endDate;
}
