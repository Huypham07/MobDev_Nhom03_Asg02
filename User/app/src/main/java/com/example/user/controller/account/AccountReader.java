package com.example.user.controller.account;

import com.example.user.model.Account;

import java.util.concurrent.CompletableFuture;

public interface AccountReader {
    CompletableFuture<Account> getAccountWithPhone(String phone, String password);
    CompletableFuture<Account> getAccountWithEmail(String email, String password);
}
