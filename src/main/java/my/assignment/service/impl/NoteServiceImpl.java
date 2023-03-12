package my.assignment.service.impl;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.assignment.entity.NoteEntity;
import my.assignment.mapper.NoteMapper;
import my.assignment.model.Note;
import my.assignment.repository.NoteRepository;
import my.assignment.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper mapper;

    @Transactional
    @Override
    public Note createOrUpdate(Note note) {
        NoteEntity entity;
        if (note.getId() != null) {
            entity = noteRepository.getById(note.getId());
            mapper.updateEntity(note, entity);
        } else {
            entity = mapper.toEntity(note);
        }

        var savedEntity = noteRepository.save(entity);
        return mapper.fromEntity(savedEntity);
    }

    @Override
    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note findById(Long id) {
        var entity = noteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Notebook [" + id + "] not found"));
        return mapper.fromEntity(entity);
    }

    @Override
    public List<Note> findByTags(Set<String> tags) {
        var result = noteRepository.findNotesByTags(tags);
        return mapper.fromEntities(result);
    }

    @Override
    public List<Note> findNotesByKeywordInNote(String keyword, Long notebookId) {
        var result = noteRepository.findNotebooksByKeywordInNotes(keyword, notebookId);
        return mapper.fromEntities(result);
    }
}
