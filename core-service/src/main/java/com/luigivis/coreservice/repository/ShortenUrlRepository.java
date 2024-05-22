package com.luigivis.coreservice.repository;

import com.luigivis.coreservice.entity.ShortenUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShortenUrlRepository extends JpaRepository<ShortenUrlEntity, UUID> {

    ShortenUrlEntity findShortenUrlEntityByShorten(String shortenUrl);

}
