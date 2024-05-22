package com.luigivis.coreservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.luigivis.coreservice.utils.ShortUUID.shortUUID;

@Data
@Entity(name = "shorten_url")
@Table(indexes = @Index(name = "shorting_index", columnList = "shorten"))
public class ShortenUrlEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(name = "shorten", nullable = false, unique = true)
    private String shorten = shortUUID();

    @Column(nullable = false)
    private String originalUrl;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean status = true;

    @Column(nullable = false)
    private LocalDateTime expirationDate = LocalDateTime.now().plusDays(3);

    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT now()")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(columnDefinition = "DATETIME ON UPDATE now()")
    private LocalDateTime updatedAt;
}
