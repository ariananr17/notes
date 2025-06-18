package com.ariana.notes.mapper.impl;

import com.ariana.notes.domain.dto.NoteDTO;
import com.ariana.notes.domain.entities.Note;
import com.ariana.notes.mapper.NoteMapper;
import com.ariana.notes.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteMapperImpl implements NoteMapper {

    private final TagMapper tagMapper;

    @Override
    public Note fromDto(NoteDTO noteDTO) {
        return new Note(
                noteDTO.id(),
                noteDTO.title(),
                noteDTO.content(),
                noteDTO.tags().stream()
                        .map(tagMapper::fromDto)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    public NoteDTO toDto(Note note) {
        return new NoteDTO(
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getTags().stream().map(tagMapper::toDto).toList()
        );
    }
}
