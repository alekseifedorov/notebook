package my.assignment.mapper;

import java.util.List;
import my.assignment.entity.NoteEntity;
import my.assignment.entity.NotebookEntity;
import my.assignment.model.Note;
import my.assignment.model.ShortNote;
import my.assignment.model.ShortNotebook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NoteMapper {

    @Mapping(target = "notes", ignore = true)
    @Mapping(target = "tags", ignore = true)
    NotebookEntity toNotebookEntity(ShortNotebook note);

    @Mapping(target = "notebook", ignore = true)
    @Mapping(target = "tags", ignore = true)
    NoteEntity toShortEntity(ShortNote note);

    NoteEntity toEntity(Note note);

    Note fromEntity(NoteEntity entity);

    void updateEntity(Note dto, @MappingTarget NoteEntity entity);

    List<Note> fromEntities(List<NoteEntity> result);
}
