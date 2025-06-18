package com.ariana.notes.services.impl;

import com.ariana.notes.domain.dto.CreateTagRequest;
import com.ariana.notes.domain.dto.UpdateTagRequest;
import com.ariana.notes.domain.entities.Tag;
import com.ariana.notes.repositories.TagRepository;
import com.ariana.notes.services.TagService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createTag(CreateTagRequest createTagRequest) {
        String tagName = createTagRequest.getName();
        if(tagRepository.existsByNameIgnoreCase(tagName)) {
            throw new IllegalArgumentException("Tag already exists");
        }
        Tag tosaveTag = Tag.builder()
                        .name(createTagRequest.getName())
                                .notes(new HashSet<>())
                                        .build();
        return tagRepository.save(tosaveTag);
    }

    @Override
    public Tag getTag(UUID id) {
        return tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());

    }

    @Override
    public void saveTag(Tag tagToEdit) {
        tagRepository.save(tagToEdit);
    }

    @Override
    public void deleteTag(UUID id) {
        tagRepository.findById(id).ifPresent(tag -> {
            if(!tag.getNotes().isEmpty()) {
                throw new IllegalStateException("Cannot delete tag with notes");
            }
        });
        tagRepository.deleteById(id);
    }

    @Override
    public Tag updateTag(UUID id, UpdateTagRequest updateTagRequest) {
        Tag tagToUpdate = getTag(id);
        tagToUpdate.setName(updateTagRequest.getName());
        tagRepository.save(tagToUpdate);
        return tagToUpdate;
    }

    @Override
    public List<Tag> findTagsByIds(Set<UUID> tagIds) {
        List<Tag> tagList = tagRepository.findAllById(tagIds);
        if(tagList.size() != tagIds.size()) {
            throw new EntityNotFoundException("Not all specified Tag Ids exist");
        }
        return tagList;
    }
}
