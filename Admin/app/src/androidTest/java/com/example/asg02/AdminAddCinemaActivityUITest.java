package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class AdminAddCinemaActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminAddCinemaActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AdminAddCinemaActivity.class);

    @Test
    public void testUIComponentsVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.backBtn2)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.textView)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.enterCinemaName)).check(matches(isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.continueBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testEnterCinemaName() {
        String cinemaName = "Cinema XYZ";

        Espresso.onView(ViewMatchers.withId(R.id.enterCinemaName))
                .perform(ViewActions.typeText(cinemaName), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.enterCinemaName)).check(matches(withText(cinemaName)));
    }

    @Test
    public void testContinueButtonEnabledAfterEnteringCinemaName() {
        String cinemaName = "CGV";
        Espresso.onView(ViewMatchers.withId(R.id.enterCinemaName))
                .perform(ViewActions.typeText(cinemaName), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.continueBtn)).check(matches(isEnabled()));
    }

    @Test
    public void testContinueButtonDisabledWithoutEnteringCinemaName() {
        Espresso.onView(ViewMatchers.withId(R.id.continueBtn)).check(matches(not(isEnabled())));
    }
}
