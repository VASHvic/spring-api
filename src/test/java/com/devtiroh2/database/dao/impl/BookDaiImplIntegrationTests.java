package com.devtiroh2.database.dao.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.devtiroh2.database.TestDataUtil;
import com.devtiroh2.database.dao.AuthorDao;
import com.devtiroh2.database.dao.imp.BookDaoImpl;
import com.devtiroh2.database.domain.Author;
import com.devtiroh2.database.domain.Book;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BookDaiImplIntegrationTests {

    private BookDaoImpl underTest;
    private AuthorDao authorDao;

    @Autowired
    public BookDaiImplIntegrationTests(BookDaoImpl underTest, AuthorDao authorDao) {
        this.authorDao = authorDao;
        this.underTest = underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.createTestAuthor1();
        Book book = TestDataUtil.createTestBook();
        authorDao.create(author);
        underTest.create(book);
        Optional<Book> result = underTest.find(book.getIsbn());
        assertThat(result).isPresent();
    }
}
