package com.devtiroh2.database.dao;

import java.util.Optional;

import com.devtiroh2.database.domain.Book;

public interface BookDao {
    void create(Book book);

    Optional<Book> find(String isbn);

}
