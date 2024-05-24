package com.luigivis.coreservice.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MetricsResponseDto {
  private String shortenUrl;
  private String uri;
  private Object params;
  private Object headers;
  private String ipAddress;
  private String host;
  private Date invocationDate = new Date();

  @SneakyThrows
  public String toString() {
    return new ObjectMapper().writeValueAsString(this);
  }
}
