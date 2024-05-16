package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityUITest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testForgotPasswordLink() {
        Espresso.onView(ViewMatchers.withId(R.id.forgot_password)).perform(ViewActions.click());

        // Kiểm tra xem đã chuyển sang màn hình quên mật khẩu hay không (dùng id của view trên màn hình mới để kiểm tra)
//        Espresso.onView(ViewMatchers.withId(R.id.forgot_password_screen)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testValidLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.enterEmail)).perform(ViewActions.typeText("example@example.com"));
        Espresso.onView(ViewMatchers.withId(R.id.enterPassword)).perform(ViewActions.typeText("password"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.login)).perform(ViewActions.click());

        // Kiểm tra xem đã chuyển sang màn hình chính của ứng dụng hay không (dùng id của view trên màn hình mới để kiểm tra)
//        Espresso.(ViewMatchers.withId(R.id.main_screen)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
