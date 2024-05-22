package com.luigivis.coreservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.springframework.scheduling.annotation.Async;

public interface MetricsService {
    @Async
    void sendToKafka(ServletRequest request, ServletResponse response) throws JsonProcessingException;
}
