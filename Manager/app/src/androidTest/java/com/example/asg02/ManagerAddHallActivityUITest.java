package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ManagerAddHallActivityUITest {

    @Rule
    public ActivityScenarioRule<ManagerAddHallActivity> activityScenarioRule =
            new ActivityScenarioRule<>(ManagerAddHallActivity.class);

    @Test
    public void testEnterHallName() {
        Espresso.onView(withId(R.id.enterHallName))
                .perform(ViewActions.replaceText("Phòng 1"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.enterHallName))
                .check(ViewAssertions.matches(withText("Phòng 1")));
    }

    @Test
    public void testEnterSeatsPerRow() {
        Espresso.onView(withId(R.id.enterSeatsPerRow))
                .perform(ViewActions.typeText("10"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.enterSeatsPerRow))
                .check(ViewAssertions.matches(withText("10")));
    }

    @Test
    public void testEnterSeatsPerColumn() {
        Espresso.onView(withId(R.id.enterSeatsPerColumn))
                .perform(ViewActions.typeText("8"), ViewActions.closeSoftKeyboard());
        Espresso.onView(withId(R.id.enterSeatsPerColumn))
                .check(ViewAssertions.matches(withText("8")));
    }

    // Viết ui test cho spinner và addButton
}
