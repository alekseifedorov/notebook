package my.assignment.service;

import my.assignment.model.Tag;

public interface TagService {
    Tag createOrUpdate(Tag tag);

    void delete(String name);

    Tag findById(String name);

    Tag findOrCreate(String name);
}
