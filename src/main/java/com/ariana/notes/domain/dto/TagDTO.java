package com.ariana.notes.domain.dto;

import java.util.UUID;

public record TagDTO(
        UUID id,
        String name
) {
}
