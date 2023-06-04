package ru.java.api.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageResponseDto {

    private UUID id;
    private Integer userId;
    private String message;
    private String hash;
    private String progress;

}
