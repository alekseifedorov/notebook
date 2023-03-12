package my.assignment.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import my.assignment.dto.TagCreateOrUpdateRequest;
import my.assignment.model.Tag;
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
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;

    @PostMapping
    @Operation(description = "Create a tag")
    public Tag create(@RequestBody TagCreateOrUpdateRequest request) {
        return tagService.createOrUpdate(
            Tag.builder()
                .name(request.getName())
                .color(request.getColor())
                .build());
    }

    @PutMapping(value = "/{name}")
    @Operation(description = "Update a tag")
    public Tag update(@RequestParam("name") Long name, @RequestBody TagCreateOrUpdateRequest request) {
        return tagService.createOrUpdate(
            Tag.builder().name(request.getName()).color(request.getColor()).build());
    }

    @GetMapping(value = "/{name}")
    @Operation(description = "Get a tag")
    public Tag get(@RequestParam("name") String name) {
        return tagService.findById(name);
    }

    @DeleteMapping(value = "/{name}")
    @Operation(description = "Delete a tag")
    public void delete(@RequestParam("id") String name) {
        tagService.delete(name);
    }

}
