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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_id)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_password)).check(matches(isDisplayed()));
        onView(withId(R.id.hide_password_button)).check(matches(isDisplayed()));
        onView(withId(R.id.login_button)).check(matches(isDisplayed()));
        onView(withId(R.id.forgot_password)).check(matches(isDisplayed()));
        onView(withId(R.id.register_button)).check(matches(isDisplayed()));
        onView(withId(R.id.progressBar)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withId(R.id.error_login)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void testErrorMessageVisibility() {
        // Simulate invalid login
        onView(withId(R.id.edit_id)).perform(ViewActions.typeText("invalid@example.com"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.edit_password)).perform(ViewActions.typeText("wrongpassword"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.login_button)).perform(click());

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

        onView(withId(R.id.hide_password_button)).perform(click());
    }

    @Test
    public void testAvatarIsDisplayed() {
        onView(withId(R.id.avatar))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testTotalExpenseIsDisplayed() {
        onView(withId(R.id.textView34))
                .check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.expense))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testRewardPointsIsDisplayed() {
        onView(withId(R.id.textView36))
                .check(ViewAssertions.matches(isDisplayed()));

        onView(withId(R.id.point))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testProgressBarIsDisplayed() {
        onView(withId(R.id.progressBar))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testUserDetailsAreDisplayed() {
        onView(withId(R.id.name))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.phone))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.email))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.birthdate))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.sex))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.textView43))
                .check(ViewAssertions.matches(isDisplayed()));
        onView(withId(R.id.textView44))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testEditingButtonClick() {
        onView(withId(R.id.editing_button))
                .check(ViewAssertions.matches(isDisplayed()))
                .perform(click());
    }

    @Test
    public void testUIElements() {
        // Check if avatar ImageView is displayed
        onView(withId(R.id.avatar)).check(matches(isDisplayed()));

        // Check if "Thay ảnh đại diện" button is displayed and clickable
        onView(withId(R.id.change_avt)).check(matches(isDisplayed())).perform(click());

        // Check if editName EditText is displayed and enter text
        onView(withId(R.id.editName)).check(matches(isDisplayed())).perform(replaceText("Lê Thành Doanh"));

        // Check if editPhone EditText is displayed (disabled)
        onView(withId(R.id.editPhone)).check(matches(isDisplayed()));

        // Check if editEmail EditText is displayed (disabled)
        onView(withId(R.id.editEmail)).check(matches(isDisplayed()));

        // Check if edit_password EditText and hide_password_button are displayed
        onView(withId(R.id.edit_password)).check(matches(isDisplayed()));
        onView(withId(R.id.hide_password_button)).check(matches(isDisplayed())).perform(click());

        // Check if editBirthDate AutoCompleteTextView is displayed
        onView(withId(R.id.editBirthDate)).check(matches(isDisplayed())).perform(click());

        // Check if edit_sex AutoCompleteTextView is displayed
        onView(withId(R.id.edit_sex)).check(matches(isDisplayed())).perform(click());

        // Check if edit_region AutoCompleteTextView is displayed
        onView(withId(R.id.edit_region)).check(matches(isDisplayed())).perform(click());

        // Check if edit_favorite AutoCompleteTextView is displayed
        onView(withId(R.id.edit_favorite)).check(matches(isDisplayed())).perform(click());

        // Scroll to and check if save button is displayed and clickable
        onView(withId(R.id.save)).perform(scrollTo()).check(matches(isDisplayed())).perform(click());

        // Scroll to and check if delete_account button is displayed and clickable
        onView(withId(R.id.delete_account)).perform(scrollTo()).check(matches(isDisplayed())).perform(click());
    }
}
