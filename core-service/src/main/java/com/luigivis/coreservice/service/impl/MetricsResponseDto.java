package com.luigivis.coreservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luigivis.coreservice.service.MetricsService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MetricsResponseDto implements MetricsService {

  @Autowired private ObjectMapper mapper;

  @Async
  @Override
  public void sendToKafka(ServletRequest request, ServletResponse response) throws JsonProcessingException {
    log.info("Sending to kafka");
    var httpRequest = (HttpServletRequest) request;

    log.info("params {}", mapper.writeValueAsString(getParams(httpRequest)));
    log.info("headers {}", mapper.writeValueAsString(getHeaders(httpRequest)));
  }

  private Map<String, String> getParams(HttpServletRequest request) {
    var map = new HashMap<String, String>();
    request
        .getParameterNames()
        .asIterator()
        .forEachRemaining(i -> map.put(i, request.getParameter(i)));
    return map;
  }

  private Map<String, String> getHeaders(HttpServletRequest request) {
    var map = new HashMap<String, String>();
    request.getHeaderNames().asIterator().forEachRemaining(i -> map.put(i, request.getHeader(i)));
    return map;
  }
}
