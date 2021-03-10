package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.Role;
import com.bookstore.bookstore.model.ShoppingCart;
import com.bookstore.bookstore.model.User;

public interface UserService {

    User register(String username, String password, String repeatPassword, String name, String surname, Role role, ShoppingCart cart);
}

