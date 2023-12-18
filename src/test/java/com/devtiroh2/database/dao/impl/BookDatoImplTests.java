package com.devtiroh2.database.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.devtiroh2.database.dao.imp.BookDaoImpl;
import com.devtiroh2.database.domain.Book;

@ExtendWith(MockitoExtension.class)
public class BookDatoImplTests {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private BookDaoImpl underTest;

    @Test
    public void testThatCreateBookGenetaresCorrectSql() {
        Book book = Book.builder().isbn("123").title("caca").authorId(1L).build();
        underTest.create(book);
        verify(jdbcTemplate).update(
                eq("INSERT INTO books (isbn,title,author_id) VALUES (?, ?, ?)"),
                eq("123"), eq("caca"), eq(1L));
    }
}