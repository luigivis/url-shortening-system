package com.luigivis.coreservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luigivis.coreservice.service.MetricsService;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpMethod.POST;

@Slf4j
@Service
public class MetricsServiceImpl implements MetricsService {

  private final ObjectMapper mapper;

  public MetricsServiceImpl(ObjectMapper mapper) {
    this.mapper = mapper;
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

    var shortenUrl = httpRequest.getRequestURI().replaceAll("/api/v1/shorten/", "");

    // log.info("Body {}", new JSONParser(httpRequest.getReader()).parse());
    log.info("shortenUrl {}", shortenUrl);
    log.info("Ip {}", httpRequest.getRemoteAddr());
    log.info("Host {}", httpRequest.getRemoteHost());
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
