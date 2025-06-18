package com.ariana.notes.controller;

import com.ariana.notes.domain.dto.CreateNoteRequest;
import com.ariana.notes.domain.dto.NoteDTO;
import com.ariana.notes.domain.dto.UpdateNoteRequest;
import com.ariana.notes.domain.entities.Note;
import com.ariana.notes.mapper.NoteMapper;
import com.ariana.notes.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/notes")
@CrossOrigin( methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        List<NoteDTO> notesDto = notes.stream().map(noteMapper::toDto).toList();
        return ResponseEntity.ok(notesDto);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> createNote(@RequestBody CreateNoteRequest createNoteRequest) {
        Note note = noteService.createNote(createNoteRequest);
        NoteDTO noteDto = noteMapper.toDto(note);
        return new ResponseEntity<>(
                noteDto,
                HttpStatus.CREATED
        );
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<NoteDTO> editNote(@PathVariable UUID id,
                                            @RequestBody UpdateNoteRequest updateNoteRequest) {
        Note note = noteService.editNote(id, updateNoteRequest);
        NoteDTO  noteDTO = noteMapper.toDto(note);
        return ResponseEntity.ok(noteDTO);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID id){
        noteService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }

}
