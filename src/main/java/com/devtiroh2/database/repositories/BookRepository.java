package com.devtiroh2.database.repositories;

import com.devtiroh2.database.domain.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, String> {

}
