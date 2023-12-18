package com.devtiroh2.database.dao;

import java.util.Optional;

import com.devtiroh2.database.domain.Author;

public interface AuthorDao {
    void create(Author author);

    Optional<Author> findOne(Long id);
}
