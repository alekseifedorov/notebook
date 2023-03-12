package my.assignment;

import lombok.extern.slf4j.Slf4j;
import my.assignment.exception.ValidationException;
import my.assignment.mapper.NoteMapper;
import my.assignment.model.Notebook;
import my.assignment.repository.NoteRepository;
import my.assignment.service.impl.NotebookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
@ExtendWith(MockitoExtension.class)
class NotebookTest {

    @Mock
    private NoteRepository noteRepository;

    @Mock
    private NoteMapper mapper;

    @InjectMocks
    private NotebookServiceImpl notebookService;

    @Test
    void shouldValidateBeforeCreation() {
        var notebook = Notebook.builder().title("ab").build();
        assertThatThrownBy(() -> notebookService.createOrUpdate(notebook))
            .isInstanceOf(ValidationException.class);
    }
}
