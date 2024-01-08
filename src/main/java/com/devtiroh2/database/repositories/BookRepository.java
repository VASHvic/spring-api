package com.devtiroh2.database.repositories;

import com.devtiroh2.database.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity, String> {

}
