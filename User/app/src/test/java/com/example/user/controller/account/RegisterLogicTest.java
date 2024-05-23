package com.example.user.controller.account;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.user.model.User;

public class RegisterLogicTest {
    private RegisterLogic registerLogic;
    private RegisterController mockRegisterController;

    @Before
    public void setUp() {
        mockRegisterController = mock(RegisterController.class);
        registerLogic = new RegisterLogic(mockRegisterController);
    }

    @Test
    public void testCheckExistEmail() {
        String email = "test@example.com";
        when(mockRegisterController.checkExistEmail(email)).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Boolean> result = registerLogic.checkExistEmail(email);

        assertTrue(result.join());
        verify(mockRegisterController).checkExistEmail(email);
    }

    @Test
    public void testCheckExistPhone() {
        String phone = "1234567890";
        when(mockRegisterController.checkExistPhone(phone)).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Boolean> result = registerLogic.checkExistPhone(phone);

        assertTrue(result.join());
        verify(mockRegisterController).checkExistPhone(phone);
    }

    @Test
    public void testCreateAccount_NullAccount() {
        CompletableFuture<Integer> result = registerLogic.createAccount(null);

        assertEquals(RegisterController.FAIL, (int) result.join());
    }

    @Test
    public void testCreateAccount_EmailExists() {
        User user = new User("test@example.com", "password", "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockRegisterController.checkExistEmail(user.getEmail())).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Integer> result = registerLogic.createAccount(user);

        assertEquals(RegisterController.EMAIL_EXISTS, (int) result.join());
        verify(mockRegisterController).checkExistEmail(user.getEmail());
    }

    @Test
    public void testCreateAccount_PhoneExists() {
        User user = new User("test@example.com", "password", "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockRegisterController.checkExistEmail(user.getEmail())).thenReturn(CompletableFuture.completedFuture(false));
        when(mockRegisterController.checkExistPhone(user.getPhone())).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Integer> result = registerLogic.createAccount(user);

        assertEquals(RegisterController.PHONE_EXISTS, (int) result.join());
        verify(mockRegisterController).checkExistEmail(user.getEmail());
        verify(mockRegisterController).checkExistPhone(user.getPhone());
    }

    @Test
    public void testCreateAccount_Success() {
        User user = new User("test@example.com", "password", "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockRegisterController.checkExistEmail(user.getEmail())).thenReturn(CompletableFuture.completedFuture(false));
        when(mockRegisterController.checkExistPhone(user.getPhone())).thenReturn(CompletableFuture.completedFuture(false));
        when(mockRegisterController.createAccount(user)).thenReturn(CompletableFuture.completedFuture(RegisterController.SUCCESS));

        CompletableFuture<Integer> result = registerLogic.createAccount(user);

        assertEquals(RegisterController.SUCCESS, (int) result.join());
        verify(mockRegisterController).checkExistEmail(user.getEmail());
        verify(mockRegisterController).checkExistPhone(user.getPhone());
        verify(mockRegisterController).createAccount(user);
    }
}
