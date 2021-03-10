package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository {
    Optional<User> findById(String id);
    public List<Book> findAll();
    public Optional<Book> findByName(String name);
}
