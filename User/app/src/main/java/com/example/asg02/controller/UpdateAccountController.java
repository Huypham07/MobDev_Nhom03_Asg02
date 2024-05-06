package com.example.asg02.controller;

import com.example.asg02.model.Account;

public class UpdateAccountController implements AccountDatabase {
    public String sendCodeToEmail(String email) {
        return null;
    }

    public String sendCodeToPhone(String phone) {
        return null;
    }

    public boolean verifyCode(String code) {
        return false;
    }

    public boolean updateAccount(Account account) {
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
    public boolean deleteAccount(Account account) {
        return false;
    }
}
