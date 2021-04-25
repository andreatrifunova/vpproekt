package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long id);
    List<Author> findAll();
    Optional<Author> save(String name, String surname);
    void deleteById(Long id);
}


