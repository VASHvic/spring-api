package com.devtiroh2.database.dao.impl;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import com.devtiroh2.database.TestDataUtil;
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
        Author author = TestDataUtil.createTestAuthor1();
        underTest.create(author);
        verify(jdbcTemplate).update(
                "INSERT INTO authors (id,name,age) VALUES (?, ?, ?)",
                1L, "Abigail Rose", 38);
    }

    @Test
    public void testThatFindOneGeneratesTheCorrectSql() {
        underTest.find(1L);
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id = ? LIMIT 1"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(1L));
    }

    @Test
    public void testThatFindManyGeneratesTheCorrectSql() {
        underTest.findMany(List.of(1L, 2L));
        verify(jdbcTemplate).query(
                eq("SELECT id, name, age FROM authors WHERE id IN ?"),
                ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),
                eq(List.of(1L, 2L)));
    }

}
