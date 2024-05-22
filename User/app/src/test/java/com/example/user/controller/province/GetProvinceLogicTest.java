package com.example.user.controller.province;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetProvinceLogicTest {

    @Mock
    private DatabaseReference mockDatabaseReference;

    @Mock
    private DatabaseReference mockPlaceReference;

    @Mock
    private DatabaseReference mockProvinceReference;

    @Mock
    private Task<DataSnapshot> mockTask;

    @Mock
    private DataSnapshot mockDataSnapshot;

    private GetProvinceLogic getProvinceLogic;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockDatabaseReference.child("place")).thenReturn(mockPlaceReference);
        when(mockPlaceReference.child("province")).thenReturn(mockProvinceReference);
        when(mockProvinceReference.get()).thenReturn(mockTask);

        getProvinceLogic = new GetProvinceLogic(mockDatabaseReference);
    }

    @Test
    public void testGetAllProvincesHandlesFailure() throws Exception {
        when(mockTask.addOnSuccessListener(any(OnSuccessListener.class)))
                .thenReturn(mockTask);
        when(mockTask.addOnFailureListener(any(OnFailureListener.class)))
                .thenAnswer(new Answer<Task<DataSnapshot>>() {
                    @Override
                    public Task<DataSnapshot> answer(InvocationOnMock invocation) {
                        OnFailureListener listener = invocation.getArgument(0);
                        listener.onFailure(new Exception("Failed to get data"));
                        return mockTask;
                    }
                });

        CompletableFuture<List<String>> future = getProvinceLogic.getAllProvinces();

        try {
            future.get();
            fail("Expected an exception to be thrown");
        } catch (Exception e) {
            assertTrue(e.getCause().getMessage().contains("Failed to get data"));
        }
    }
}