package my.assignment.service.impl;

import javax.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.assignment.mapper.TagMapper;
import my.assignment.model.Tag;
import my.assignment.repository.TagRepository;
import my.assignment.service.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@AllArgsConstructor
@Service
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    private final TagMapper mapper;

    @Transactional
    @Override
    public Tag createOrUpdate(Tag tag) {
        var entity = tagRepository.findById(tag.getName())
            .map(e -> {
                mapper.updateEntity(tag, e);
                return e;
            }).orElseGet(() -> mapper.toEntity(tag));

        var savedEntity = tagRepository.save(entity);
        return mapper.fromEntity(savedEntity);
    }

    @Override
    public void delete(String name) {
        tagRepository.deleteById(name);
    }

    @Override
    public Tag findById(String name) {
        var entity = tagRepository.findById(name)
            .orElseThrow(() -> new EntityNotFoundException("Notebook [" + name + "] not found"));
        return mapper.fromEntity(entity);
    }

    @Transactional
    @Override
    public Tag findOrCreate(String name) {
        return tagRepository.findById(name)
            .map(mapper::fromEntity)
            .orElseGet(() ->
                createOrUpdate(Tag.builder().name(name).build())
            );
    }
}
