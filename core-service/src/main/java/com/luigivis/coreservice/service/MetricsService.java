package com.luigivis.coreservice.service;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import org.springframework.scheduling.annotation.Async;

public interface MetricsService {
    @Async("processExecutor")
    void sendToKafka(ServletRequest request, ServletResponse response) throws IOException;
}
