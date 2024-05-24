package com.luigivis.coreservice.controller;

import static com.luigivis.coreservice.dto.generic.GenericResponse.GenerateHttpResponse;

import com.luigivis.coreservice.dto.generic.GenericResponse;
import com.luigivis.coreservice.dto.request.UrlCreateRequestDto;
import com.luigivis.coreservice.dto.response.UrlCreateResponseDto;
import com.luigivis.coreservice.dto.response.UrlFindResponseDto;
import com.luigivis.coreservice.entity.ShortenUrlEntity;
import com.luigivis.coreservice.service.impl.ShortenUrlServiceImpl;
import java.net.URI;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/shorten/")
public class ShortenUrlController {

  private final ShortenUrlServiceImpl service;

  @Autowired
  public ShortenUrlController(ShortenUrlServiceImpl service) {
    this.service = service;
  }

  @PostMapping("create")
  public ResponseEntity<GenericResponse<UrlCreateResponseDto>> createShortenUrl(
      @RequestBody UrlCreateRequestDto requestDto) {
    var response = service.createShortenUrl(requestDto);
    return GenerateHttpResponse(response);
  }

  @GetMapping("get/{shortUrl}")
  public ResponseEntity<GenericResponse<UrlFindResponseDto>> findOriginalUrl(
      @PathVariable String shortUrl) {
    var response = service.findOriginalUrl(shortUrl);
    return GenerateHttpResponse(response);
  }

  @GetMapping("find/{uuid}")
  public ResponseEntity<GenericResponse<ShortenUrlEntity>> findOriginal(@PathVariable UUID uuid) {
    var response = service.findByUuid(uuid);
    return GenerateHttpResponse(response);
  }

  @GetMapping("/{shortUrl}")
  public ResponseEntity<GenericResponse<UrlFindResponseDto>> redirectOriginalUrl(
      @PathVariable String shortUrl) {
    var response = service.findOriginalUrl(shortUrl);
    if (response.getData() == null) return GenerateHttpResponse(response);
    return ResponseEntity.status(HttpStatus.FOUND)
        .location(URI.create(response.getData().originalUrl()))
        .build();
  }
}
