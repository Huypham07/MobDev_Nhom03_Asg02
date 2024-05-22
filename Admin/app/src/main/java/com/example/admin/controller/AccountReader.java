package com.example.admin.controller;

import com.example.admin.model.Account;
import com.example.admin.model.User;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public interface AccountReader {
    CompletableFuture<Account> getAccountWithPhone(String phone, String password);
    CompletableFuture<Account> getAccountWithEmail(String email, String password);
    void getAllUser(Consumer<Map.Entry<String, User>> onUserAdded);
}
