package ru.java.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Entity
@Table(schema = "generate", name = "picture")
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PictureEntity {
    @Id
    private UUID id;
    private UUID messageId;
    private String contentType;
    private String name;
    private String filePath;
}
