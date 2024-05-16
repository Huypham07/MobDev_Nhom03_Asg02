package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

@LargeTest
public class ManagerEditAndDeleteActivityUITest {

    @Rule
    public ActivityScenarioRule<ManagerEditAndDeleteActivity> activityRule =
            new ActivityScenarioRule<>(ManagerEditAndDeleteActivity.class);

    @Test
    public void testButtonAndTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.edit)).perform(ViewActions.click());

        Espresso.onView(withId(R.id.showInfoOfCinemaHallShow)).check(matches(isDisplayed()));

    }

    @Test
    public void testChooseShowInEditAnDeleteSpinner() {
        Espresso.onView(withId(R.id.chooseShowInEditAndDelete)).perform(ViewActions.click());
    }

    @Test
    public void testChooseHallInEditAndDeleteSpinner() {
        Espresso.onView(withId(R.id.chooseHallInEditAndDelete)).perform(ViewActions.click());
    }

    @Test
    public void testChooseCinemaInEditAndDeleteSpinner() {
        Espresso.onView(withId(R.id.chooseCinemaInEditAndDelete)).perform(ViewActions.click());
    }

    @Test
    public void testBackEditAndDelete() {
        onView(withId(R.id.backEditAndDelete)).perform(ViewActions.click());
        onView(withId(R.id.managerActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
