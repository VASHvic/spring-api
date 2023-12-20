package com.devtiroh2.database;

import com.devtiroh2.database.domain.Author;
import com.devtiroh2.database.domain.Book;

public class TestDataUtil {

    private TestDataUtil() {
    }

    public static Author createTestAuthor1() {
        return Author.builder().id(1L).name("Abigail Rose").age(38).build();
    }

    public static Author createTestAuthor2() {
        return Author.builder().id(2L).name("Miguel de Unamuno").age(51).build();
    }

    public static Book createTestBook() {
        return Book.builder().isbn("123").title("The Hobbit").authorId(1L).build();
    }

}
