package my.assignment.repository;

import java.util.List;
import java.util.Set;
import my.assignment.entity.NoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NoteRepository extends JpaRepository<NoteEntity, Long> {
    @Query("SELECT DISTINCT n FROM NoteEntity n " +
        "LEFT JOIN FETCH n.tags t " +
        "WHERE t.name IN :tags")
    List<NoteEntity> findNotesByTags(@Param("tags") Set<String> tags);

    @Query("SELECT DISTINCT n FROM NoteEntity n " +
        "LEFT JOIN FETCH n.tags t " +
        "WHERE n.content LIKE %:keyword% AND n.notebook.id = :notebook")
    List<NoteEntity> findNotebooksByKeywordInNotes(@Param("keyword") String keyword,
                                                       @Param("notebook") Long notebook);
}
