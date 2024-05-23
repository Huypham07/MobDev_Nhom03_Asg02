package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.asg02.view.CinemaSystemActivity;

@LargeTest
public class CinemaSystemActivityUITest {

    @Rule
    public ActivityScenarioRule<CinemaSystemActivity> activityRule =
            new ActivityScenarioRule<>(CinemaSystemActivity.class);

    @Test
    public void testButtonClick() {
        // Click on the button with ID Hanoi
        Espresso.onView(ViewMatchers.withId(R.id.Hanoi)).perform(ViewActions.click());

        // Click on the button with ID TPHCM
        Espresso.onView(ViewMatchers.withId(R.id.TPHCM)).perform(ViewActions.click());

        // Click on the button with ID Hue
        Espresso.onView(ViewMatchers.withId(R.id.Hue)).perform(ViewActions.click());

        // Check if the ListView is displayed
        Espresso.onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }
}
