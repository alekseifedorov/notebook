package my.assignment.service;

import java.util.List;
import java.util.Set;
import my.assignment.model.Notebook;
import my.assignment.model.ShortNotebook;

public interface NotebookService {
    Notebook createOrUpdate(Notebook notebook);

    void delete(Long id);

    Notebook findById(Long id);

    ShortNotebook findShortById(Long id);

    List<Notebook> findByTags(Set<String> tags);
}
