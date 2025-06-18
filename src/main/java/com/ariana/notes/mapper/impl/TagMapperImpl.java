package com.ariana.notes.mapper.impl;

import com.ariana.notes.domain.dto.TagDTO;
import com.ariana.notes.domain.entities.Tag;
import com.ariana.notes.mapper.TagMapper;
import org.springframework.stereotype.Component;

@Component
public class TagMapperImpl implements TagMapper {
    @Override
    public Tag fromDto(TagDTO tagDTO) {
        return Tag.builder()
                .id(tagDTO.id())
                .name(tagDTO.name())
                .build();
    }

    @Override
    public TagDTO toDto(Tag tag) {
        return new TagDTO(tag.getId(), tag.getName());
    }
}
