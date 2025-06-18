package com.ariana.notes.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;


public record NoteDTO(
        UUID id,
        String title,
        String content,
        List<TagDTO> tags
) {
}
