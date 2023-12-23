package com.devtiroh2.database.dao;

import java.util.List;
import java.util.Optional;

import com.devtiroh2.database.domain.Author;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(long l);

    List<Author> find();

    void update(long id, Author author);

    void delete(long id);

}
