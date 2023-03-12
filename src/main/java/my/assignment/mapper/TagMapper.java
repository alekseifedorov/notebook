package my.assignment.mapper;

import my.assignment.entity.TagEntity;
import my.assignment.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagEntity toEntity(Tag tag);

    Tag fromEntity(TagEntity entity);

    void updateEntity(Tag dto, @MappingTarget TagEntity entity);
}
