package com.luigivis.coreservice.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class MetricsResponseDto {
    private String shortenUrl;
    private Date invocationDate = new Date();
    private Object params;
    private Object headers;
    private String ipAddress;
}
