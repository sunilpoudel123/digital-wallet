package edu.miu.report.dto;

import lombok.Data;

@Data
public class ReportRequest {
    private Long walletId;
    private String reportType;
    private String startDate;
    private String endDate;
}
