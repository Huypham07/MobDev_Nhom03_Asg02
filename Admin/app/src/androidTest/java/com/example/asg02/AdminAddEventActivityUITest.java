package com.example.asg02;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class AdminAddEventActivityUITest {

    @Rule
    public ActivityScenarioRule<AdminAddEventActivity> activityScenarioRule =
            new ActivityScenarioRule<>(AdminAddEventActivity.class);

    @Test
    public void testUIComponentsVisible() {
        Espresso.onView(withId(R.id.textView))
                .check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.textView2))
                .check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.textView3))
                .check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.textView4))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testEnterEventName() {
        Espresso.onView(withId(R.id.enterEventName)).perform(ViewActions.scrollTo());
        String eventName = "Event Name";
        Espresso.onView(withId(R.id.enterEventName))
                .perform(ViewActions.typeText(eventName), ViewActions.closeSoftKeyboard());

        Espresso.onView(withId(R.id.enterEventName))
                .check(matches(ViewMatchers.withText(eventName)));
    }

    @Test
    public void testImageView() {
        Espresso.onView(withId(R.id.selectPoster)).perform(ViewActions.scrollTo());
        Espresso.onView(withId(R.id.selectPoster))
                .check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.selectPoster))
                .perform(ViewActions.click());
    }

    @Test
    public void testEditText() {
        Espresso.onView(withId(R.id.enterSDate)).perform(ViewActions.scrollTo());

        Espresso.onView(withId(R.id.enterSDate))
                .check(ViewAssertions.matches(isDisplayed()));

        Espresso.onView(withId(R.id.enterEDate)).perform(ViewActions.scrollTo());

        Espresso.onView(withId(R.id.enterEDate))
                .check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void testEnterEventInfo() {
        Espresso.onView(withId(R.id.enterEventInfo))
                .perform(ViewActions.scrollTo(), ViewActions.click());

        String textToEnter = "Detailed information about the event...";
        Espresso.onView(withId(R.id.enterEventInfo))
                .perform(ViewActions.typeText(textToEnter), ViewActions.closeSoftKeyboard());
    }
}
