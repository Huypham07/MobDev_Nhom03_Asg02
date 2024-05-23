package com.example.user;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import com.example.user.view.MainActivity;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testToolbarIsDisplayed() {
        Espresso.onView(withId(R.id.toolbar))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void testTicketButtonClick() {
        Espresso.onView(withId(R.id.ticket))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(click());
    }

    @Test
    public void testNavigationToggleClick() {
        Espresso.onView(withId(R.id.navigationToggle))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(click());
    }

    @Test
    public void testNavHostFragmentIsDisplayed() {
        Espresso.onView(withId(R.id.nav_host_fragment_content_main))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
