package com.example.user;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.assertion.ViewAssertions;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import com.example.user.view.LoginActivity;

@RunWith(AndroidJUnit4.class)
public class LoginActivityUITest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityScenarioRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testInitialViewState() {
        // Check if all views are displayed
        onView(withId(R.id.toolbar)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.edit_id)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.edit_password)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.hide_password_button)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.forgot_password)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.register_button)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.progressBar)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.error_login)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void testErrorMessageVisibility() {
        // Simulate invalid login
        onView(withId(R.id.edit_id)).perform(ViewActions.typeText("invalid@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edit_password)).perform(ViewActions.typeText("wrongpassword"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(ViewActions.click());

        // Check if error message is displayed
        onView(withId(R.id.error_login)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Test
    public void testLoginButtonEnabled() {
        // Check if login button is initially disabled
        onView(withId(R.id.login_button)).check(matches(ViewMatchers.isNotEnabled()));

        // Enter valid credentials
        onView(withId(R.id.edit_id)).perform(ViewActions.typeText("valid@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edit_password)).perform(ViewActions.typeText("correctpassword"), ViewActions.closeSoftKeyboard());

        // Check if login button is enabled
        onView(withId(R.id.login_button)).check(matches(ViewMatchers.isEnabled()));
    }

    @Test
    public void testPasswordVisibilityToggle() {
        // Enter password
        onView(withId(R.id.edit_password)).perform(ViewActions.typeText("mypassword"), ViewActions.closeSoftKeyboard());

        onView(withId(R.id.hide_password_button)).perform(ViewActions.click());
    }
}
