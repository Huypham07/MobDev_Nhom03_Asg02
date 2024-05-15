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
public class AdminMainActivityTest {

    @Rule
    public ActivityScenarioRule<AdminMainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AdminMainActivity.class);

    @Test
    public void testCinemaButtonIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.cinemaBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testEventButtonIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.eventBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testMessageButtonIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.messageBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testMovieButtonIsDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.movieBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickCinemaButton() {
        Espresso.onView(ViewMatchers.withId(R.id.cinemaBtn)).perform(ViewActions.click());
        // Add assertions or actions after clicking the cinema button
    }

    @Test
    public void testClickEventButton() {
        Espresso.onView(ViewMatchers.withId(R.id.eventBtn)).perform(ViewActions.click());
        // Add assertions or actions after clicking the event button
    }

    @Test
    public void testClickMessageButton() {
        Espresso.onView(ViewMatchers.withId(R.id.messageBtn)).perform(ViewActions.click());
        // Add assertions or actions after clicking the message button
    }

//    @Test
//    public void testClickMovieButton() {
//        // Thực hiện hành động cuộn ScrollView đến vị trí của nút "Phim"
//        Espresso.onView(ViewMatchers.withId(R.id.scrollView))
//                .perform(ViewActions.scrollTo())
//                .check(matches(isDisplayed()));
//
//        // Sau khi cuộn đến vị trí của nút "Phim", thực hiện hành động nhấp chuột vào nó
//        Espresso.onView(ViewMatchers.withId(R.id.movieBtn)).perform(ViewActions.click());
//    }
}
