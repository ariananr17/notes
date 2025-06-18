package com.ariana.notes.services;

import com.ariana.notes.domain.dto.CreateTagRequest;
import com.ariana.notes.domain.dto.UpdateTagRequest;
import com.ariana.notes.domain.entities.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TagService {
    List<Tag> getAllTags();

    Tag createTag(CreateTagRequest createTagRequest);

    Tag getTag(UUID id);

    void saveTag(Tag tagToEdit);
    void deleteTag(UUID id);

    Tag updateTag(UUID id, UpdateTagRequest updateTagRequest);

    List<Tag> findTagsByIds(Set<UUID> tagIds);
}
