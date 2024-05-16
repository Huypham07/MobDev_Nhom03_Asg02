package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class ManagerChangePasswordUITest {

    @Rule
    public ActivityScenarioRule<ManagerChangePassword> activityRule =
            new ActivityScenarioRule<>(ManagerChangePassword.class);

    @Test
    public void testChangePassword() {
        Espresso.onView(ViewMatchers.withId(R.id.enterOldPassword))
                .perform(ViewActions.typeText("oldpassword"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enterNewPassword))
                .perform(ViewActions.typeText("newpassword"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enterNewPasswordAgain))
                .perform(ViewActions.typeText("newpassword"), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.confirmChangePassword)).perform(ViewActions.click());
    }

    @Test
    public void testBackChangePassword() {
        onView(withId(R.id.backChangePassword)).perform(ViewActions.click());
        onView(withId(R.id.managerActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
