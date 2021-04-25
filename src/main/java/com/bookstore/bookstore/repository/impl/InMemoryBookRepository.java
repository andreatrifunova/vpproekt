package com.bookstore.bookstore.repository.impl;

import com.bookstore.bookstore.model.Author;
import com.bookstore.bookstore.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryBookRepository {
    public List<Book> findAll() {
        return DataHolder.books;
    }

    public Optional<Book> findById(Long id) {
        return DataHolder.books.stream().filter(i -> i.getId().equals(id)).findFirst();
    }

    public Optional<Book> save(String name, Author author) {
        Book book = new Book(name, author);
        DataHolder.books.add(book);
        return Optional.of(book);
    }

    public boolean deleteById(Long id) {
        return DataHolder.books.removeIf(i -> i.getId().equals(id));
    }
}

