package com.example.asg02.controller;

import com.example.asg02.model.Account;

public interface AccountDatabase {
    boolean createAccount(Account account);
    Account getAccountWithPhone(String phone, String password);
    Account getAccountWithEmail(String email, String password);
    boolean updateAccount(Account account);
    boolean deleteAccount(Account account);

}
