package com.bookstore.bookstore.service.impl;

import com.bookstore.bookstore.model.Author;
import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.model.exceptions.AuthorNotFoundException;
import com.bookstore.bookstore.model.exceptions.BookNotFoundException;
import com.bookstore.bookstore.repository.AuthorRepository;
import com.bookstore.bookstore.repository.BookRepository;
import com.bookstore.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository,
                              AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Book> save(String name, Long authorId) {

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name, author)));
    }


    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Long authorId) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);

        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}

