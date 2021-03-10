package com.bookstore.bookstore.repository;

import com.bookstore.bookstore.model.Author;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository {
    public Optional<Author> findById(Long id);
    public List<Author> findAll();
    public void deleteById(Long id);

}
