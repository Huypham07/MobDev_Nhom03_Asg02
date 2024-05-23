package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.asg02.view.LoginActivity;

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
    }

    @Test
    public void testValidLogin() {
        Espresso.onView(ViewMatchers.withId(R.id.enterEmail)).perform(ViewActions.typeText("example@example.com"));
        Espresso.onView(ViewMatchers.withId(R.id.enterPassword)).perform(ViewActions.typeText("password"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.login)).perform(ViewActions.click());
    }
}
