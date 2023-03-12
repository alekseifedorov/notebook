package my.assignment.mapper;

import java.util.List;
import my.assignment.entity.NoteEntity;
import my.assignment.model.Note;
import my.assignment.model.ShortNote;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    NoteEntity toEntity(Note note);

    Note fromEntity(NoteEntity entity);

    void updateEntity(Note dto, @MappingTarget NoteEntity entity);

    ShortNote fromEntityToShort(NoteEntity entity);

    List<Note> fromEntities(List<NoteEntity> result);
}
