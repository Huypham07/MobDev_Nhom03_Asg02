package com.example.user;


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import com.example.user.view.chat.ChatActivity;

@RunWith(AndroidJUnit4.class)
public class ChatActivityUITest {

    @Rule
    public ActivityScenarioRule<ChatActivity> activityScenarioRule =
            new ActivityScenarioRule<>(ChatActivity.class);

    @Test
    public void testSendMessage() {
        // Check if the edit text is displayed
        onView(withId(R.id.editTextMessage)).check(matches(ViewMatchers.isDisplayed()));

        // Type a message in the EditText
        String message = "Hello, this is a test message!";
        onView(withId(R.id.editTextMessage)).perform(ViewActions.typeText(message));

        // Close the keyboard
        onView(withId(R.id.editTextMessage)).perform(ViewActions.closeSoftKeyboard());

        // Click the send button
        onView(withId(R.id.buttonSendMessage)).perform(ViewActions.click());
    }

    @Test
    public void testToolbarTitle() {
        // Check if the toolbar title is correct
        onView(withId(R.id.toolbar))
                .check(matches(ViewMatchers.withText(R.string.title_activity_chat)));
    }

    @Test
    public void testNavigationIcon() {
        // Check if the navigation icon is displayed
        onView(withId(R.id.toolbar))
                .check(matches(ViewMatchers.hasDescendant(withId(android.R.id.home))));
    }
}
