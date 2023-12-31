package com.devtiroh2.database.services.impl;

import com.devtiroh2.database.domain.entities.AuthorEntity;
import com.devtiroh2.database.repositories.AuthorRepository;
import com.devtiroh2.database.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
