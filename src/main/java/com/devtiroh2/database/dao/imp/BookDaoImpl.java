package com.devtiroh2.database.dao.imp;

import org.springframework.jdbc.core.JdbcTemplate;

import com.devtiroh2.database.dao.BookDao;
import com.devtiroh2.database.domain.Book;

public class BookDaoImpl implements BookDao {
    private final JdbcTemplate jdbcTemplate;

    public BookDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO books (isbn,title,author_id) VALUES (?, ?, ?)",
                book.getIsbn(),
                book.getTitle(),
                book.getAuthorId());
    }
}
