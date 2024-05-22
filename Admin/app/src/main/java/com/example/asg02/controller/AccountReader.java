package com.example.asg02.controller;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface AccountReader {
    CompletableFuture<Account> getAccountWithPhone(String phone, String password);
    CompletableFuture<Account> getAccountWithEmail(String email, String password);
    void getAllUser(Consumer<Map.Entry<String, User>> onUserAdded);
}
