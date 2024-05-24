package com.luigivis.coreservice.service.impl;

import com.luigivis.coreservice.dto.response.MetricsResponseDto;
import com.luigivis.coreservice.service.MetricsService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MetricsServiceImpl implements MetricsService {

  @Value("${kafka.metrics.topic}")
  private String topic;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public MetricsServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @Async("processExecutor")
  @Override
  public void sendToKafka(ServletRequest request, ServletResponse response) throws IOException {
    log.info("Sending to kafka");
    var httpRequest = (HttpServletRequest) request;

    switch (HttpMethod.valueOf(httpRequest.getMethod()).name()) {
      case "POST", "DELETE", "PUT" -> {
        log.info("Canceling send to kafka POST invocation");
        return;
      }
    }

    var shortenUrl =
        httpRequest
            .getRequestURI()
            .replaceAll("/", "")
            .replaceAll("api", "")
            .replaceAll("v1", "")
            .replaceAll("shorten", "")
            .replaceAll("find", "")
            .replaceAll("get", "");

    var metricDto =
        MetricsResponseDto.builder()
            .shortenUrl(shortenUrl)
            .uri(httpRequest.getRequestURI())
            .ipAddress(httpRequest.getRemoteAddr())
            .host(httpRequest.getRemoteHost())
            .params(getParams(httpRequest))
            .headers(getHeaders(httpRequest))
            .invocationDate(new Date())
            .build();
    log.info("MetricsResponseDto {}", metricDto);

    kafkaTemplate.send(topic, UUID.randomUUID().toString(), metricDto.toString());
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
