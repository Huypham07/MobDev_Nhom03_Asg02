package com.example.asg02.controller;

import com.example.asg02.model.Account;

import java.util.concurrent.CompletableFuture;

public interface AccountCreator {
    public static final int SUCCESS = 0;
    public static final int FAIL = -1;
    public static final int EMAIL_EXISTS = 1;
    public static final int PHONE_EXISTS = 2;

    CompletableFuture<Boolean> checkExistEmail(String email);
    CompletableFuture<Boolean> checkExistPhone(String phone);
    int createAccount(Account account);
}
