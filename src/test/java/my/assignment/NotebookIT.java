package my.assignment;

import java.util.List;
import java.util.Set;
import my.assignment.entity.enumeration.Color;
import my.assignment.model.Note;
import my.assignment.model.Notebook;
import my.assignment.model.ShortNote;
import my.assignment.model.Tag;
import my.assignment.repository.NotebookRepository;
import my.assignment.repository.NoteRepository;
import my.assignment.repository.TagRepository;
import my.assignment.service.NoteService;
import my.assignment.service.NotebookService;
import my.assignment.service.TagService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class NotebookIT {

    public static final String FIRST_TAG_NAME = "ToDo item";
    @Autowired
    private NoteService noteService;

    @Autowired
    private NotebookService notebookService;

    @Autowired
    private TagService tagService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private NotebookRepository notebookRepository;

    @AfterEach
    void cleanUp() {
        notebookRepository.deleteAll();
        tagRepository.deleteAll();
        noteRepository.deleteAll();
    }

    @Test
    void shouldFindByTagsAndKeyword() {

        var tag = tagService.createOrUpdate(Tag.builder().color(Color.BLUE).name(FIRST_TAG_NAME).build());
        var note = noteService.createOrUpdate(Note.builder().title("First note").tags(List.of(tag))
            .content("Shop list").build());
        var notebook = notebookService.createOrUpdate(
            Notebook.builder().notes(Set.of(ShortNote.builder().id(note.getId()).build())).tags(Set.of(tag))
                .description("Notebook 1").title("Title 1").build());

        var tag2 = tagService.createOrUpdate(Tag.builder().color(Color.BLUE).name("ToDo item 2").build());
        var note2 = noteService.createOrUpdate(Note.builder().title("Second note").content("Thoughts").build());
        notebookService.createOrUpdate(
            Notebook.builder().notes(Set.of(ShortNote.builder().id(note2.getId()).build())).tags(Set.of(tag2))
                .description("Notebook 1").title("Title 1").build());

        var result = notebookService.findByTags(Set.of(FIRST_TAG_NAME));
        assertThat(result).hasSize(1);

        var second = noteService.findNotesByKeywordInNote("Shop", notebook.getId());
        assertThat(second).hasSize(1);

        var tags = noteService.findByTags(Set.of(FIRST_TAG_NAME));
        assertThat(tags).hasSize(1);
    }
}
