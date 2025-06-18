package com.ariana.notes.services.impl;

import com.ariana.notes.domain.dto.CreateNoteRequest;
import com.ariana.notes.domain.dto.CreateTagRequest;
import com.ariana.notes.domain.dto.UpdateNoteRequest;
import com.ariana.notes.domain.entities.Note;
import com.ariana.notes.domain.entities.Tag;
import com.ariana.notes.repositories.NoteRepository;
import com.ariana.notes.services.NoteService;
import com.ariana.notes.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final TagService tagService;

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNote(UUID id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + id));
    }


    @Override
    public Note createNote(CreateNoteRequest createNoteRequest) {
        String title = createNoteRequest.getTitle();
        String content = createNoteRequest.getContent();
        Set<UUID> tagIds = createNoteRequest.getTagIds();
        List<Tag> newTags = tagService.findTagsByIds(tagIds);
        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setContent(content);
        newNote.setTags(new HashSet<>(newTags));
        return noteRepository.save(newNote);
    }

    @Override
    public Note editNote(UUID id, UpdateNoteRequest updateNoteRequest) {
        Note note = getNote(id);
        note.setTitle(updateNoteRequest.getTitle());
        note.setContent(updateNoteRequest.getContent());
        if(!updateNoteRequest.getTagIds().isEmpty()) {
            List<Tag> updateTags = tagService.findTagsByIds(updateNoteRequest.getTagIds());
            Set<Tag> updatedTagsSet = updateTags.stream().collect(Collectors.toSet());
            note.setTags(updatedTagsSet);
        } else {
            note.setTags(new HashSet<>());
        }
        return noteRepository.save(note);
    }

    @Override
    public void deleteTag(UUID id) {
        noteRepository.deleteById(id);
    }
}
