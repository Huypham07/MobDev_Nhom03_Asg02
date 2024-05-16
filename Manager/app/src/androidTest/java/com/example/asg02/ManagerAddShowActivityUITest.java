package com.example.asg02;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.anything;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

public class ManagerAddShowActivityUITest {

    @Rule
    public ActivityScenarioRule<ManagerAddShowActivity> activityScenarioRule = new ActivityScenarioRule<>(ManagerAddShowActivity.class);

    @Test
    public void testBackButton() {
        onView(withId(R.id.backShow)).perform(ViewActions.click());
        onView(withId(R.id.managerActivity)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testFormSubmission() {
        // Enter data into the form fields
        onView(withId(R.id.enterDate)).perform(ViewActions.typeText("2024-05-17"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enterStartTime)).perform(ViewActions.typeText("09:00"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.enterEndTime)).perform(ViewActions.typeText("11:00"), ViewActions.closeSoftKeyboard());

        // Click the finish button
//        Espresso.onView(ViewMatchers.withId(R.id.finishAddShow)).perform(ViewActions.scrollTo()).perform(ViewActions.click());

        // Add assertions here to verify the behavior after clicking the button
    }

    // Cần viết thêm ui test cho spinner
    @Test
    public void testChooseCinemaInShowSpinner() {
        Espresso.onView(withId(R.id.chooseCinemaInShow)).perform(ViewActions.click());
    }

    @Test
    public void testChooseHallInShowSpinner() {
        Espresso.onView(withId(R.id.chooseHallInShow)).perform(ViewActions.click());
    }

    @Test
    public void testChooseMovieInShowSpinner() {
        Espresso.onView(withId(R.id.chooseMovieInShow)).perform(ViewActions.click());
    }
}
