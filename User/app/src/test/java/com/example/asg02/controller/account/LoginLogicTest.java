package com.example.asg02.controller.account;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;

public class LoginLogicTest {

    private LoginLogic loginLogic;
    private LoginController mockLoginController;

    @Before
    public void setUp() {
        mockLoginController = mock(LoginController.class);
        loginLogic = new LoginLogic(mockLoginController);
    }

    @Test
    public void testLogin_NullId() {
        CompletableFuture<Account> result = loginLogic.login(null, "password");

        assertNull(result.join());
    }

    @Test
    public void testLogin_NullPassword() {
        CompletableFuture<Account> result = loginLogic.login("id", null);

        assertNull(result.join());
    }

    @Test
    public void testLogin_EmailLogin_Success() {
        String email = "test@example.com";
        String password = "password";
        User user = new User(email, password, "name", "birthDate", "sex", "1234567890", "region", "favouriteCinema", "avatar");
        when(mockLoginController.getAccountWithEmail(email, password)).thenReturn(CompletableFuture.completedFuture(user));

        CompletableFuture<Account> result = loginLogic.login(email, password);

        assertEquals(user, result.join());
        verify(mockLoginController).getAccountWithEmail(email, password);
    }

    @Test
    public void testLogin_EmailLogin_Failure() {
        String email = "test@example.com";
        String password = "password";
        when(mockLoginController.getAccountWithEmail(email, password)).thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<Account> result = loginLogic.login(email, password);

        assertNull(result.join());
        verify(mockLoginController).getAccountWithEmail(email, password);
    }

    @Test
    public void testLogin_PhoneLogin_Success() {
        String phone = "1234567890";
        String password = "password";
        User user = new User("test@example.com", password, "name", "birthDate", "sex", phone, "region", "favouriteCinema", "avatar");
        when(mockLoginController.getAccountWithPhone(phone, password)).thenReturn(CompletableFuture.completedFuture(user));

        CompletableFuture<Account> result = loginLogic.login(phone, password);

        assertEquals(user, result.join());
        verify(mockLoginController).getAccountWithPhone(phone, password);
    }

    @Test
    public void testLogin_PhoneLogin_Failure() {
        String phone = "1234567890";
        String password = "password";
        when(mockLoginController.getAccountWithPhone(phone, password)).thenReturn(CompletableFuture.completedFuture(null));

        CompletableFuture<Account> result = loginLogic.login(phone, password);

        assertNull(result.join());
        verify(mockLoginController).getAccountWithPhone(phone, password);
    }
}

