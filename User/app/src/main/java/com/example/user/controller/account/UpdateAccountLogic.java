package com.example.user.controller.account;

import com.example.user.model.Account;
import com.example.user.model.User;

import java.util.concurrent.CompletableFuture;

public class UpdateAccountLogic {
    private final UpdateAccountController updateAccountController;

    public UpdateAccountLogic(UpdateAccountController updateAccountController) {
        this.updateAccountController = updateAccountController;
    }

    public CompletableFuture<Boolean> checkExistEmail(String email) {
        return updateAccountController.checkExistEmail(email);
    }

    public CompletableFuture<Boolean> checkExistPhone(String phone) {
        return updateAccountController.checkExistPhone(phone);
    }

    public CompletableFuture<Integer> updateCurrentAccount(Account account) {
        if (account == null) {
            return CompletableFuture.completedFuture(UpdateAccountController.FAIL);
        }
        User user = (User) account;

        return checkExistEmail(user.getEmail()).thenCompose(emailExists -> {
            if (emailExists) {
                return CompletableFuture.completedFuture(UpdateAccountController.EMAIL_EXISTS);
            } else {
                return checkExistPhone(user.getPhone()).thenCompose(phoneExists -> {
                    if (phoneExists) {
                        return CompletableFuture.completedFuture(UpdateAccountController.PHONE_EXISTS);
                    } else {
                        // Convert the synchronous result to a CompletableFuture
                        return CompletableFuture.supplyAsync(() -> updateAccountController.updateCurrentAccount(user));
                    }
                });
            }
        });
    }
}


