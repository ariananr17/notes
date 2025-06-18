package com.ariana.notes.services;

import com.ariana.notes.domain.dto.CreateNoteRequest;
import com.ariana.notes.domain.dto.UpdateNoteRequest;
import com.ariana.notes.domain.entities.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {
    List<Note> getAllNotes();
    Note getNote(UUID id);
    Note createNote(CreateNoteRequest createNoteRequest);

    Note editNote(UUID id, UpdateNoteRequest updateNoteRequest);

    void deleteTag(UUID id);
}
