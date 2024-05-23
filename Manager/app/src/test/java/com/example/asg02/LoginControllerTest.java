package com.example.asg02;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.util.Log;

import com.example.asg02.controller.AccountReader;
import com.example.asg02.controller.LoginController;
import com.example.asg02.model.Account;

import java.util.concurrent.CompletableFuture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

public class LoginControllerTest {

    private AccountReader mockAccountReader;
    private LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockAccountReader = mock(AccountReader.class);
        loginController = mock(LoginController.class);
    }

    @Test
    public void testLogin_nullId_returnsNull() {
        CompletableFuture<Account> result = loginController.login(null, "password");
        assertNull(result);
    }

    @Test
    public void testLogin_nullPassword_returnsNull() {
        CompletableFuture<Account> result = loginController.login("id", null);
        assertNull(result);
    }
}

