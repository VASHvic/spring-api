package com.devtiroh2.database.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.devtiroh2.database.dao.imp.AuthorDaoImpl;
import com.devtiroh2.database.domain.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorDaoImpTests {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private AuthorDaoImpl underTest;

    @Test
    public void testThatCreateAuthorGenetaresCorrectSql() {
        Author author = Author.builder().id(1L).name("Abigail Rose").age(38).build();
        underTest.create(author);
        verify(jdbcTemplate).update(
                "INSERT INTO authors (id,name,age) VALUES (?, ?, ?)",
                1L, "Abigail Rose", 38);
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.findOne(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }

}
