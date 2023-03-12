package my.assignment.mapper;

import java.util.List;
import my.assignment.entity.NotebookEntity;
import my.assignment.model.Notebook;
import my.assignment.model.ShortNotebook;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = NoteMapper.class, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface NotebookMapper {

    NotebookEntity toEntity(Notebook note);

    Notebook fromEntity(NotebookEntity entity);

    ShortNotebook toShort(NotebookEntity entity);

    List<Notebook> fromEntities(List<NotebookEntity> entity);

    void updateEntity(Notebook dto, @MappingTarget NotebookEntity entity);
}
