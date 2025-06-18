package com.ariana.notes.controller;

import com.ariana.notes.domain.dto.CreateTagRequest;
import com.ariana.notes.domain.dto.DeleteTagRequest;
import com.ariana.notes.domain.dto.TagDTO;
import com.ariana.notes.domain.dto.UpdateTagRequest;
import com.ariana.notes.domain.entities.Tag;
import com.ariana.notes.mapper.TagMapper;
import com.ariana.notes.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<Tag> existingTags = tagService.getAllTags();
        List<TagDTO> foundTags = existingTags.stream().map(tagMapper::toDto).toList();
        return ResponseEntity.ok(foundTags);
    }

    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody CreateTagRequest createTagRequest) {
            Tag savedTag = tagService.createTag(createTagRequest);
            TagDTO createdTag = tagMapper.toDto(savedTag);
            return new ResponseEntity<>(
                    createdTag,
                    HttpStatus.CREATED
            );
    }

    @PutMapping
    public ResponseEntity<TagDTO> editTag(@RequestBody UpdateTagRequest updateTagRequest) {
        Tag updatedTag = tagService.updateTag(updateTagRequest.getId(), updateTagRequest);
        TagDTO updatedTagDTO = tagMapper.toDto(updatedTag);
        return ResponseEntity.ok(updatedTagDTO);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TagDTO> getTag(@PathVariable UUID id) {
        Tag existingTag = tagService.getTag(id);
        TagDTO foundTag = tagMapper.toDto(existingTag);
        return ResponseEntity.ok(foundTag);
    }


    @DeleteMapping
    public ResponseEntity<Void> deleteTag(@RequestBody DeleteTagRequest deleteTagRequest) {
        tagService.deleteTag(deleteTagRequest.getId());
        return ResponseEntity.noContent().build();
    }
}
