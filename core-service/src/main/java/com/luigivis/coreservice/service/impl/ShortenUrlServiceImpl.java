package com.luigivis.coreservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luigivis.coreservice.dto.generic.GenericResponse;
import com.luigivis.coreservice.dto.request.UrlCreateRequestDto;
import com.luigivis.coreservice.dto.response.UrlCreateResponseDto;
import com.luigivis.coreservice.dto.response.UrlFindResponseDto;
import com.luigivis.coreservice.entity.ShortenUrlEntity;
import com.luigivis.coreservice.repository.ShortenUrlRepository;
import com.luigivis.coreservice.service.ShortenUrlService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShortenUrlServiceImpl implements ShortenUrlService {

  private final ObjectMapper objectMapper;

  private final ShortenUrlRepository repository;

  @Autowired
  public ShortenUrlServiceImpl(ShortenUrlRepository repository, ObjectMapper objectMapper) {
    this.repository = repository;
    this.objectMapper = objectMapper;
  }

  @Override
  public GenericResponse<UrlCreateResponseDto> createShortenUrl(UrlCreateRequestDto requestDto) {
    var entity = objectMapper.convertValue(requestDto, ShortenUrlEntity.class);
    var save = repository.save(entity);
    var result = objectMapper.convertValue(save, UrlCreateResponseDto.class);

    return new GenericResponse<>(HttpStatus.CREATED, result);
  }

  @Override
  @Cacheable(cacheNames = "short:get:original", key = "#shortenUrl")
  public GenericResponse<UrlFindResponseDto> findOriginalUrl(String shortenUrl) {
    var result = repository.findShortenUrlEntityByShorten(shortenUrl);
    if (ObjectUtils.isEmpty(result)) {
      return new GenericResponse<>(HttpStatus.NOT_FOUND);
    }
    return new GenericResponse<>(HttpStatus.OK, new UrlFindResponseDto(result.getOriginalUrl(), result.getUuid()));
  }

  @Override
  @Cacheable(cacheNames = "short:get:uuid", key = "#uuid")
  public GenericResponse<ShortenUrlEntity> findByUuid(UUID uuid) {
    var result = repository.findById(uuid);
    return result
        .map(shortenUrlEntity -> new GenericResponse<>(HttpStatus.OK, shortenUrlEntity))
        .orElseGet(() -> new GenericResponse<>(HttpStatus.NOT_FOUND));
  }
}
