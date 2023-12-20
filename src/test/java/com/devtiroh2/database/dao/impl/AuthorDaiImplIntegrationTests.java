package com.devtiroh2.database.dao.impl;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devtiroh2.database.TestDataUtil;
import com.devtiroh2.database.dao.imp.AuthorDaoImpl;
import com.devtiroh2.database.domain.Author;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthorDaiImplIntegrationTests {

    private AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaiImplIntegrationTests(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor1();
        underTest.create(author);
        Optional<Author> result = underTest.find(author.getId());
        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public void testThatManyAuthorCanBeCreatedAndRecalled() {
        Author author1 = TestDataUtil.createTestAuthor1();
        Author author2 = TestDataUtil.createTestAuthor2();
        underTest.create(author1);
        underTest.create(author2);
        List<Author> result = underTest.findMany(List.of(author1.getId(), author2.getId()));
        assertThat(result).hasSize(2).contains(author1).contains(author2);
    }
}
