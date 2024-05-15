package com.example.asg02;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AdminMessagingActivityTest {

    @Rule
    public ActivityScenarioRule<AdminMessagingActivity> activityRule =
            new ActivityScenarioRule<>(AdminMessagingActivity.class);

    @Test
    public void testSendMessage() {
        // Kiểm tra nút back
        Espresso.onView(withId(R.id.backBtn8)).perform(ViewActions.click());

        // Kiểm tra hiển thị tên người dùng
        Espresso.onView(withId(R.id.userNameTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        // Kiểm tra nút thông tin người dùng
        Espresso.onView(withId(R.id.infoUserBtn)).perform(ViewActions.click());

        // Kiểm tra nút gửi tin nhắn
        Espresso.onView(withId(R.id.sendMessageBtn)).perform(ViewActions.click());

        // Nhập tin nhắn vào EditText
        Espresso.onView(withId(R.id.messagingEditText)).perform(ViewActions.typeText("Tin nhắn test"), ViewActions.closeSoftKeyboard());

        // Kiểm tra nút gửi tin nhắn
        Espresso.onView(withId(R.id.sendMessageBtn)).perform(ViewActions.click());
    }
}
