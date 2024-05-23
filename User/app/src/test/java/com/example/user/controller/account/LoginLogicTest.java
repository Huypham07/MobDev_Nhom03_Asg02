package com.example.user.controller.account;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.example.user.model.Account;
import com.example.user.model.User;

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
    public void testLogin_EmailLogin_Failure_1() {
        String email = "test.example.com";
        String password = "password";
        CompletableFuture<Account> result = loginLogic.login(email, password);

        assertNull(result.join());
    }
    @Test
    public void testLogin_PhoneLogin_Success() {
        String phone = "0234567890";
        String password = "password";
        User user = new User("test@example.com", password, "name", "birthDate", "sex", phone, "region", "favouriteCinema", "avatar");
        when(mockLoginController.getAccountWithPhone(phone, password)).thenReturn(CompletableFuture.completedFuture(user));

        CompletableFuture<Account> result = loginLogic.login(phone, password);

        assertEquals(user, result.join());
        verify(mockLoginController).getAccountWithPhone(phone, password);
    }

    @Test
    public void testLogin_PhoneLogin_Failure() {
        String phone = "0234567890";
        String password = "password";
        when(mockLoginController.getAccountWithPhone(phone, password)).thenReturn(CompletableFuture.completedFuture(null));
        CompletableFuture<Account> result = loginLogic.login(phone, password);

        assertNull(result.join());
        verify(mockLoginController).getAccountWithPhone(phone, password);
    }

    @Test
    public void testLogin_PhoneLogin_Failure_1() {
        String phone = "02345678900";
        String password = "password";
        CompletableFuture<Account> result = loginLogic.login(phone, password);

        assertNull(result.join());
    }

    @Test
    public void testValidEmail() {
        assertTrue(LoginController.isValidEmail("test@example.com"));
        assertTrue(LoginController.isValidEmail("user.name_tag@example.co.uk"));
        assertTrue(LoginController.isValidEmail("user.name.tag@example.co.uk"));
        assertFalse(LoginController.isValidEmail("test@.com"));
        assertFalse(LoginController.isValidEmail("testexample.com"));
        assertFalse(LoginController.isValidEmail("test@com"));
        assertFalse(LoginController.isValidEmail("test+@example"));
    }

    @Test
    public void testValidPhoneNumber() {
        assertFalse(LoginController.isValidPhoneNumber("0123456789a"));
        assertFalse(LoginController.isValidPhoneNumber("1023456789"));
        assertFalse(LoginController.isValidPhoneNumber("012345678"));
        assertFalse(LoginController.isValidPhoneNumber("01234567890a"));
        assertFalse(LoginController.isValidPhoneNumber("01234567890"));
        assertFalse(LoginController.isValidPhoneNumber("012345+7890"));
    }
}

