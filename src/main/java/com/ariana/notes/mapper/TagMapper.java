package com.ariana.notes.mapper;


import com.ariana.notes.domain.dto.TagDTO;
import com.ariana.notes.domain.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public interface TagMapper {
    Tag fromDto(TagDTO tagDTO);
    TagDTO toDto(Tag tag);
}
