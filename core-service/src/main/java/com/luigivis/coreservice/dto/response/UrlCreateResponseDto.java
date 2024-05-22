package com.luigivis.coreservice.dto.response;

import java.io.Serializable;
import java.util.UUID;

public record UrlCreateResponseDto(String shorten, UUID uuid) implements Serializable {}
