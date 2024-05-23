package com.example.admin;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.matcher.ViewMatchers.*;

public class AdminAddCinemaActivity2UITest {

    @Rule
    public ActivityScenarioRule<AdminAddCinemaActivity2> activityScenarioRule =
            new ActivityScenarioRule<>(AdminAddCinemaActivity2.class);

    @Test
    public void testEmailEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.emailName))
                .check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.emailName))
                .check(ViewAssertions.matches(withText("cinemaname@star3.cineplex.com")));
    }

    @Test
    public void testPasswordEditText() {
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.password))
                .check(ViewAssertions.matches(withText("#star3cineplex#cinemaname")));
    }

    @Test
    public void testFinishButton() {
        Espresso.onView(ViewMatchers.withId(R.id.finishBtn))
                .check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.finishBtn))
                .perform(ViewActions.click());

    }
}
