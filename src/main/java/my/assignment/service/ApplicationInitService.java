package my.assignment.service;

import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.assignment.entity.enumeration.Color;
import my.assignment.model.Note;
import my.assignment.model.Notebook;
import my.assignment.model.ShortNotebook;
import my.assignment.model.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ApplicationInitService implements ApplicationListener<ApplicationReadyEvent> {
    private final NotebookService notebookService;
    private final TagService tagService;
    private final NoteService noteService;

    @Value("${init-environment:false}")
    private boolean initEnvironment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initData();
    }

    public void initData() {
        if (initEnvironment) {
            log.info("Data initialization starting...");
            doInitData();
            log.info("Data initialization has completed successfully!");
        }
    }

    private void doInitData() {
        var tag = tagService.createOrUpdate(Tag.builder().color(Color.BLUE).name("ToDo1").build());

        var notebook = notebookService.createOrUpdate(
            Notebook.builder().tags(Set.of(tag))
                .description("Notebook 1").title("Title 1").build());

        noteService.createOrUpdate(Note.builder().title("First note")
            .notebook(ShortNotebook.builder().id(notebook.getId()).build())
            .tags(List.of(tag)).content("Shop list").build());
    }
}
