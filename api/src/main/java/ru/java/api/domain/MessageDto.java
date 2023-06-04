package ru.java.api.domain;

import lombok.Builder;

@Builder
public record MessageDto(String message, Integer batchSize, Integer numSteps, Integer seed) {
}
