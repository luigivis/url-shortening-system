package com.luigivis.coreservice.dto.response;


import java.io.Serializable;
import java.util.UUID;

public record UrlFindResponseDto(String originalUrl, UUID uuid) implements Serializable {
}
