package my.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import my.assignment.dto.NotebookCreateOrUpdateRequest;
import my.assignment.model.Notebook;
import my.assignment.service.NotebookService;
import my.assignment.service.TagService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notebooks")
public class NotebookController {

    private final NotebookService notebookService;
    private final TagService tagService;

    @PostMapping()
    @Operation(description = "Create a notebook")
    public Notebook create(@RequestBody NotebookCreateOrUpdateRequest request) {
        var tags = request.getTags().stream()
            .map(tagService::findOrCreate)
            .collect(Collectors.toSet());

        return notebookService.createOrUpdate(Notebook.builder().description(request.getDescription())
            .title(request.getTitle()).tags(tags).build());
    }

    @PutMapping(value = "/{id}")
    @Operation(description = "Update a notebook")
    public Notebook update(@RequestBody NotebookCreateOrUpdateRequest request, @RequestParam("id") Long id) {
        var tags = request.getTags().stream()
            .map(tagService::findOrCreate)
            .collect(Collectors.toSet());

        return notebookService.createOrUpdate(Notebook.builder().description(request.getDescription())
            .title(request.getTitle()).id(id).tags(tags).build());
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Get a notebook")
    public Notebook get(@RequestParam("id") Long id) {
        return notebookService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Delete a notebook")
    public void delete(@RequestParam("id") Long id) {
        notebookService.delete(id);
    }

    @GetMapping
    @Operation(description = "Find by tags")
    public List<Notebook> findByTags(@RequestParam("tags") Set<String> tags) {
        return notebookService.findByTags(tags);
    }
}
