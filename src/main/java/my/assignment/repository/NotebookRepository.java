package my.assignment.repository;

import java.util.List;
import java.util.Set;
import my.assignment.entity.NotebookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotebookRepository extends JpaRepository<NotebookEntity, Long> {
    @Query("SELECT DISTINCT nb FROM NotebookEntity nb " +
        "LEFT JOIN FETCH nb.tags t " +
        "LEFT JOIN FETCH nb.notes n " +
        "WHERE t.name IN :tags")
    List<NotebookEntity> findNotebooksByTags(@Param("tags") Set<String> tags);
}
