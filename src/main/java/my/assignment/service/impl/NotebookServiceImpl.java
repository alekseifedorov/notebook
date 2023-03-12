package my.assignment.service.impl;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.assignment.entity.NotebookEntity;
import my.assignment.exception.ValidationException;
import my.assignment.mapper.NotebookMapper;
import my.assignment.model.Notebook;
import my.assignment.model.ShortNotebook;
import my.assignment.repository.NotebookRepository;
import my.assignment.service.NotebookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class NotebookServiceImpl implements NotebookService {

    private final NotebookRepository notebookRepository;

    private final NotebookMapper mapper;

    @Transactional
    @Override
    public Notebook createOrUpdate(Notebook notebook) {
        validate(notebook);

        NotebookEntity entity;
        if (notebook.getId() != null) {
            entity = notebookRepository.getById(notebook.getId());
            mapper.updateEntity(notebook, entity);
        } else {
            entity = mapper.toEntity(notebook);
        }
        var savedEntity = notebookRepository.save(entity);
        return mapper.fromEntity(savedEntity);
    }

    @Override
    public void delete(Long id) {
        notebookRepository.deleteById(id);
    }

    @Override
    public Notebook findById(Long id) {
        var entity = notebookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Notebook [" + id + "] not found"));
        return mapper.fromEntity(entity);
    }

    @Override
    public ShortNotebook findShortById(Long id) {
        var entity = notebookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Notebook [" + id + "] not found"));
        return mapper.toShort(entity);
    }

    @Override
    public List<Notebook> findByTags(Set<String> tags) {
        var result = notebookRepository.findNotebooksByTags(tags);
        return mapper.fromEntities(result);
    }

    private void validate(Notebook notebook) {
        if (notebook.getTitle() == null || notebook.getTitle().length() < 3 || notebook.getTitle().length() > 128) {
            throw new ValidationException("Invalid title");
        }

        if (notebook.getDescription().length() > 1024) {
            throw new ValidationException("Invalid description");
        }
    }
}
