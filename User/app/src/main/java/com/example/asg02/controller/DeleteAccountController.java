package com.example.asg02.controller;

import com.example.asg02.model.Account;

public class DeleteAccountController implements AccountDatabase {
    public boolean deleteAccount(Account account) {
        return false;
    }

    @Override
    public boolean createAccount(Account account) {
        return false;
    }

    @Override
    public Account getAccountWithPhone(String phone, String password) {
        return null;
    }

    @Override
    public Account getAccountWithEmail(String email, String password) {
        return null;
    }

    @Override
    public boolean updateAccount(Account account) {
        return false;
    }
}
