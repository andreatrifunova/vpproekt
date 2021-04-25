package com.bookstore.bookstore.service;

import com.bookstore.bookstore.model.User;

public interface AuthService {
    User login(String username, String password);
}


