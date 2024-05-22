package com.luigivis.coreservice.filter;

import com.luigivis.coreservice.service.MetricsService;
import jakarta.servlet.*;
import java.io.IOException;
import org.springframework.stereotype.Service;

@Service
public class MetricsFilter implements Filter {

    private final MetricsService service;

    public MetricsFilter(MetricsService service) {
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        service.sendToKafka(request, response);
        chain.doFilter(request, response);
    }
}
