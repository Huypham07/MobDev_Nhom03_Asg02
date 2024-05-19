package com.example.asg02.controller.account;

import com.example.asg02.model.Account;
import com.example.asg02.model.User;
import com.example.asg02.utils.FirebaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class LoginControllerTest {

    @Mock
    private FirebaseUtils firebaseUtils;

    @Mock
    private FirebaseDatabase firebaseDatabase;

    @Mock
    private DatabaseReference databaseReference;

    @Mock
    private Task<DataSnapshot> task;

    @Mock
    private Task<AuthResult> authTask;

    @Mock
    private DataSnapshot dataSnapshot;

    @Mock
    private AuthResult authResult;

    private LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        firebaseUtils = mock(FirebaseUtils.class);
        loginController = new LoginController();

        // Mock FirebaseUtils to return mock DatabaseReference
        when(firebaseUtils.getDatabaseReference("Users")).thenReturn(databaseReference);
    }

    @Test
    public void getAccountWithPhoneTest() throws ExecutionException, InterruptedException {
        // Given
        User user1 = new User("user1", "email1", "password1", "test1", "test1", "test1", "test1", "test1", "test1");

        // Mock DataSnapshot to simulate Firebase response
        when(databaseReference.get()).thenReturn(task);
        when(task.addOnSuccessListener(any())).thenAnswer(invocation -> {
            invocation.getArgument(0, OnSuccessListener.class).onSuccess(dataSnapshot);
            return task;
        });
        when(dataSnapshot.getChildren()).thenReturn(Arrays.asList(user1).stream().map(u -> {
            DataSnapshot child = mock(DataSnapshot.class);
            when(child.getValue(User.class)).thenReturn(u);
            return child;
        }).collect(Collectors.toList()));

        // Mock Firebase auth response
        when(firebaseUtils.getAuth().signInWithEmailAndPassword(user1.getEmail(), user1.getPassword()))
                .thenReturn(authTask);
        when(authTask.addOnCompleteListener(any())).thenAnswer(invocation -> {
            invocation.getArgument(0, OnCompleteListener.class).onComplete(authTask);
            return authTask;
        });
        when(authTask.isSuccessful()).thenReturn(true);

        // When
        CompletableFuture<Account> future = loginController.getAccountWithPhone(user1.getPhone(), user1.getPassword());
        User actualUser = (User) future.get();

        // Then
        assertEquals(user1, actualUser);
    }
}
