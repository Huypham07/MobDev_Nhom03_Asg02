package com.example.asg02.controller.account;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.asg02.model.User;

public class UpdateAccountLogicTest {
    private UpdateAccountLogic updateAccountLogic;
    private UpdateAccountController mockUpdateAccountController;

    @Before
    public void setUp() {
        mockUpdateAccountController = mock(UpdateAccountController.class);
        updateAccountLogic = new UpdateAccountLogic(mockUpdateAccountController);
    }

    @Test
    public void testCheckExistEmail() {
        String email = "test@example.com";
        when(mockUpdateAccountController.checkExistEmail(email)).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Boolean> result = updateAccountLogic.checkExistEmail(email);

        assertTrue(result.join());
        verify(mockUpdateAccountController).checkExistEmail(email);
    }

    @Test
    public void testCheckExistPhone() {
        String phone = "1234567890";
        when(mockUpdateAccountController.checkExistPhone(phone)).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Boolean> result = updateAccountLogic.checkExistPhone(phone);

        assertTrue(result.join());
        verify(mockUpdateAccountController).checkExistPhone(phone);
    }

    @Test
    public void testUpdateCurrentAccount_NullAccount() {
        CompletableFuture<Integer> result = updateAccountLogic.updateCurrentAccount(null);

        assertEquals(UpdateAccountController.FAIL, (int) result.join());
    }

    @Test
    public void testUpdateCurrentAccount_EmailExists() {
        User user = new User("test@example.com", "password", "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockUpdateAccountController.checkExistEmail(user.getEmail())).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Integer> result = updateAccountLogic.updateCurrentAccount(user);

        assertEquals(UpdateAccountController.EMAIL_EXISTS, (int) result.join());
        verify(mockUpdateAccountController).checkExistEmail(user.getEmail());
    }

    @Test
    public void testUpdateCurrentAccount_PhoneExists() {
        User user = new User("test@example.com", "password", "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockUpdateAccountController.checkExistEmail(user.getEmail())).thenReturn(CompletableFuture.completedFuture(false));
        when(mockUpdateAccountController.checkExistPhone(user.getPhone())).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Integer> result = updateAccountLogic.updateCurrentAccount(user);

        assertEquals(UpdateAccountController.PHONE_EXISTS, (int) result.join());
        verify(mockUpdateAccountController).checkExistEmail(user.getEmail());
        verify(mockUpdateAccountController).checkExistPhone(user.getPhone());
    }

    @Test
    public void testUpdateCurrentAccount_Success() {
        User user = new User("test@example.com", "password", "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockUpdateAccountController.checkExistEmail(user.getEmail())).thenReturn(CompletableFuture.completedFuture(false));
        when(mockUpdateAccountController.checkExistPhone(user.getPhone())).thenReturn(CompletableFuture.completedFuture(false));
        when(mockUpdateAccountController.updateCurrentAccount(user)).thenReturn(CompletableFuture.completedFuture(UpdateAccountController.SUCCESS).getNumberOfDependents());

        CompletableFuture<Integer> result = updateAccountLogic.updateCurrentAccount(user);

        assertEquals(UpdateAccountController.SUCCESS, (int) result.join());
        verify(mockUpdateAccountController).checkExistEmail(user.getEmail());
        verify(mockUpdateAccountController).checkExistPhone(user.getPhone());
        verify(mockUpdateAccountController).updateCurrentAccount(user);
    }
}
