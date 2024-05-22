package com.luigivis.coreservice.service;

import com.luigivis.coreservice.dto.generic.GenericResponse;
import com.luigivis.coreservice.dto.request.UrlCreateRequestDto;
import com.luigivis.coreservice.dto.response.UrlCreateResponseDto;
import com.luigivis.coreservice.dto.response.UrlFindResponseDto;
import com.luigivis.coreservice.entity.ShortenUrlEntity;

import java.util.UUID;

public interface ShortenUrlService {

  GenericResponse<UrlCreateResponseDto> createShortenUrl(UrlCreateRequestDto requestDto);

  GenericResponse<UrlFindResponseDto> findOriginalUrl(String shortenUrl);

  GenericResponse<ShortenUrlEntity> findByUuid(UUID uuid);
}
