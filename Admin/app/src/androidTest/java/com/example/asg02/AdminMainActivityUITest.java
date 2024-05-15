package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import android.widget.ScrollView;

@LargeTest
public class AdminMainActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminMainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AdminMainActivity.class);

    @Test
    public void testClickCinemaButton() {
        Espresso.onView(ViewMatchers.withId(R.id.cinemaBtn)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.cinemaBtn))
                .perform(ViewActions.scrollTo(), ViewActions.click());
    }

    @Test
    public void testClickEventButton() {
        Espresso.onView(ViewMatchers.withId(R.id.eventBtn)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.eventBtn))
                .perform(ViewActions.scrollTo(), ViewActions.click());
    }

    @Test
    public void testClickMessageButton() {
        Espresso.onView(ViewMatchers.withId(R.id.messageBtn)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.messageBtn))
                .perform(ViewActions.scrollTo(), ViewActions.click());
    }

    @Test
    public void testClickMovieButton() {
        Espresso.onView(ViewMatchers.withId(R.id.movieBtn)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.movieBtn))
                .perform(ViewActions.scrollTo(), ViewActions.click());
    }
}
