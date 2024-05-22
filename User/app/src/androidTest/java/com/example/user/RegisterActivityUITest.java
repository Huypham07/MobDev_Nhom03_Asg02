package com.example.user;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import com.example.user.view.RegisterActivity;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityUITest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityRule =
            new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void testRegisterActivityUI() {
        Espresso.onView(withId(R.id.toolbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(withId(R.id.editName))
                .perform(scrollTo(), replaceText("Nguyen Van A"));

        Espresso.onView(withId(R.id.editPhone))
                .perform(scrollTo(), replaceText("0123456789"));

        Espresso.onView(withId(R.id.editEmail))
                .perform(scrollTo(), replaceText("email@example.com"));

        Espresso.onView(withId(R.id.edit_password))
                .perform(scrollTo(), replaceText("password123"));

        Espresso.onView(withId(R.id.editBirthDate))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.edit_sex))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.edit_region))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.edit_favorite))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.checkBox))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.register))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.progressBar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testValidEmail() {
        Espresso.onView(withId(R.id.editEmail))
                .perform(scrollTo(), replaceText("test@gmail.com"));

        Espresso.onView(withId(R.id.register))
                .perform(scrollTo(), click());
    }

    @Test
    public void testPasswordVisibilityToggle() {
        Espresso.onView(withId(R.id.edit_password))
                .perform(scrollTo(), replaceText("password123"));

        Espresso.onView(withId(R.id.hide_password_button))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.edit_password))
                .check(ViewAssertions.matches(ViewMatchers.withInputType(android.text.InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD)));
    }

    @Test
    public void testAgreeTermsCheckbox() {
        Espresso.onView(withId(R.id.checkBox))
                .perform(scrollTo(), click());

        Espresso.onView(withId(R.id.checkBox))
                .check(ViewAssertions.matches(ViewMatchers.isChecked()));
    }
}
