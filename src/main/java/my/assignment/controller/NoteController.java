package my.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import my.assignment.dto.NoteCreateOrUpdateRequest;
import my.assignment.model.Note;
import my.assignment.service.NoteService;
import my.assignment.service.NotebookService;
import my.assignment.service.TagService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notebooks")
public class NoteController {

    private final NoteService noteService;
    private final NotebookService notebookService;
    private final TagService tagService;

    @PostMapping(value = "/{notebookId}/notes")
    @Operation(description = "Create a note")
    public Note createNote(@PathVariable("notebookId") Long notebookId,
                           @RequestBody NoteCreateOrUpdateRequest request) {
        var tags = request.getTags().stream()
            .map(tagService::findOrCreate)
            .collect(Collectors.toList());

        var notebook = notebookService.findShortById(notebookId);
        return noteService.createOrUpdate(Note.builder().content(request.getContent())
            .notebook(notebook).title(request.getTitle()).tags(tags).build());
    }

    @PutMapping(value = "/{notebookId}/notes/{id}")
    @Operation(description = "Create a note")
    public Note updateNote(@PathVariable("notebookId") Long notebookId, @RequestParam("id") Long noteId,
                           @RequestBody NoteCreateOrUpdateRequest request) {
        var tags = request.getTags().stream()
            .map(tagService::findOrCreate)
            .collect(Collectors.toList());
        var notebook = notebookService.findShortById(notebookId);
        return noteService.createOrUpdate(Note.builder().content(request.getContent())
            .notebook(notebook).title(request.getTitle()).id(noteId).tags(tags).build());
    }

    @GetMapping(value = "/{notebookId}/notes/{id}")
    @Operation(description = "Get a note")
    public Note get(@PathVariable("id") Long id) {
        return noteService.findById(id);
    }

    @DeleteMapping(value = "/{notebookId}/notes/{id}")
    @Operation(description = "Delete a note")
    public void delete(@PathVariable("id") Long id) {
        noteService.delete(id);
    }

    @GetMapping(value = "/notes")
    @Operation(description = "Find by tags")
    public List<Note> findByTags(@RequestParam("tags") Set<String> tags) {
        return noteService.findByTags(tags);
    }

    @GetMapping(value = "/{notebookId}/notes")
    @Operation(description = "Find by keyword in note's content")
    public List<Note> findByKeyword(@RequestParam("contains") String keyword,
                                    @RequestParam("notebookId") Long notebookId) {
        return noteService.findNotesByKeywordInNote(keyword, notebookId);
    }
}
