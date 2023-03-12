package my.assignment.service;

import java.util.List;
import java.util.Set;
import my.assignment.model.Note;

public interface NoteService {
    Note createOrUpdate(Note notebook);

    void delete(Long id);

    Note findById(Long id);

    List<Note> findByTags(Set<String> tags);

    List<Note> findNotesByKeywordInNote(String keyword, Long notebookId);
}
