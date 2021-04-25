package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Long author);


    Optional<Book> edit(Long id, String name, Long author);

    void deleteById(Long id);
}

