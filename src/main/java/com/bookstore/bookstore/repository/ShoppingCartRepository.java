package com.bookstore.bookstore.repository;


import ch.qos.logback.core.status.Status;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository {
    Optional<ShoppingCart> findByUserAndStatus(User user, Status status);
}
