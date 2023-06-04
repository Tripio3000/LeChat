package ru.java.api.domain;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record MessageMQ(
        UUID id,
        String message,
        Integer batch_size,
        Integer num_steps,
        Integer seed
) implements Serializable {
}
