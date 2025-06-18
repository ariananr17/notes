package com.ariana.notes.mapper;

import com.ariana.notes.domain.dto.NoteDTO;
import com.ariana.notes.domain.entities.Note;
import org.springframework.stereotype.Component;

@Component
public interface NoteMapper {
    Note fromDto(NoteDTO noteDTO);
    NoteDTO toDto(Note note);
}
