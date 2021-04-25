package com.bookstore.bookstore.service.impl;

import com.bookstore.bookstore.model.Book;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.model.Status;
import com.bookstore.bookstore.model.User;
import com.bookstore.bookstore.model.exceptions.BookAlreadyInShoppingCartException;
import com.bookstore.bookstore.model.exceptions.BookNotFoundException;
import com.bookstore.bookstore.model.exceptions.ShoppingCartNotFoundException;
import com.bookstore.bookstore.model.exceptions.UserNotFoundException;
import com.bookstore.bookstore.repository.ShoppingCartRepository;
import com.bookstore.bookstore.repository.UserRepository;
import com.bookstore.bookstore.service.BookService;
import com.bookstore.bookstore.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final BookService bookService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   BookService bookService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.bookService = bookService;
    }

    @Override
    public List<Book> listAllBooksInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getBooks();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.shoppingCartRepository
                .findByUserAndStatus(user, Status.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addBookToShoppingCart(String username, Long bookId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Book book = this.bookService.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        if(shoppingCart.getBooks()
                .stream().filter(i -> i.getId().equals(bookId))
                .collect(Collectors.toList()).size() > 0)
            throw new BookAlreadyInShoppingCartException(bookId, username);
        shoppingCart.getBooks().add(book);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}

